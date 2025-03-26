package com.example.demo;

import com.example.demo.entity.Shipment;
import com.example.demo.entity.Delivery;
import com.example.demo.service.ShipmentService;
import com.example.demo.service.DeliveryService;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ShipmentService shipmentService;

	@MockBean
	private DeliveryService deliveryService;

	@MockBean
	private PaymentService paymentService;

	@MockBean
	private UserService userService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;

	private ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper for JSON conversion


	@Test
    @Order(1)
	void contextLoads() {
	}

	@Test
    @Order(2)
	void Annotation_testShipmentHasLombokAnnotations() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/entity/Shipment.java");
		String entityFileContent = Files.readString(entityFilePath);

		assertTrue(entityFileContent.contains("@Data"), "Author entity should contain @Data annotation");
		assertTrue(entityFileContent.contains("@NoArgsConstructor"),
				"Author entity should contain @NoArgsConstructor annotation");
	}

	@Test
    @Order(3)
	void Annotation_testDeliveryHasLombokAnnotations() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/entity/Delivery.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@Data"), "Book entity should contain @Data annotation");
	}


	@Test
    @Order(4)
	void Annotation_testShipmentHasJSONIgnoreAnnotations() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/entity/Shipment.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@JsonIgnore"), "Book entity should contain @JsonIgnore annotation");
	}

	@Test
    @Order(5)
	void Annotation_testUserHasJSONIgnoreAnnotations() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/entity/User.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@JsonIgnore"), "Delivery entity should contain @JsonIgnore annotation");
	}

    @Test
    @Order(6)
	void Repository_testShipmentRepository() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/repository/ShipmentRepository.java");
		assertTrue(Files.exists(entityFilePath), "ShipmentRepository file should exist");
	}

	@Test
    @Order(7)
	void Repository_testDeliveryRepository() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/repository/DeliveryRepository.java");
		assertTrue(Files.exists(entityFilePath), "Delivery Repository file should exist");
	}

	@Test
    @Order(8)
	void Repository_testPaymentRepository() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/repository/PaymentRepository.java");
		assertTrue(Files.exists(entityFilePath), "Payment Repository file should exist");
	}

	@Test
    @Order(9)
	void Repository_testUserRepository() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/repository/UserRepository.java");
		assertTrue(Files.exists(entityFilePath), "User Repository file should exist");
	}

    @Test
    @Order(10)
    void CRUD_testGetAllShipments() throws Exception {
    List<Shipment> shipments = Arrays.asList(
            new Shipment(1L, "New York", "Los Angeles", "2025-01-01", "2025-01-05", "In Transit", 50.0, 3.0, 200.0),
            new Shipment(2L, "San Francisco", "Chicago", "2025-01-02", "2025-01-06", "Delivered", 30.0, 2.5, 150.0)
    );

    when(shipmentService.getAllShipments()).thenReturn(shipments);

    mockMvc.perform(get("/api/shipments"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(shipments.size()))
            .andExpect(jsonPath("$[0].origin").value("New York"))
            .andExpect(jsonPath("$[1].origin").value("San Francisco"));

    verify(shipmentService, times(1)).getAllShipments();
    }

    @Test
    @Order(11)
    void CRUD_testGetShipmentById() throws Exception {
        Long shipmentId = 1L;
        Shipment shipment = new Shipment(shipmentId, "New York", "Los Angeles", "2025-01-01", "2025-01-05", "In Transit", 50.0, 3.0, 200.0);

        when(shipmentService.getShipmentById(shipmentId)).thenReturn(shipment);

        mockMvc.perform(get("/api/shipments/{id}", shipmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin").value("New York"))
                .andExpect(jsonPath("$.destination").value("Los Angeles"));

        verify(shipmentService, times(1)).getShipmentById(shipmentId);
    }


    @Test
    @Order(12)
    void CRUD_testCreateShipment() throws Exception {
        Shipment shipment = new Shipment(null, "New York", "Los Angeles", "2025-01-01", "2025-01-05", "In Transit", 50.0, 3.0, 200.0);
        Shipment savedShipment = new Shipment(1L, "New York", "Los Angeles", "2025-01-01", "2025-01-05", "In Transit", 50.0, 3.0, 200.0);

        when(shipmentService.createShipment(Mockito.any(Shipment.class))).thenReturn(savedShipment);

        mockMvc.perform(post("/api/shipments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(shipment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedShipment.getId()))
                .andExpect(jsonPath("$.origin").value(savedShipment.getOrigin()));

        verify(shipmentService, times(1)).createShipment(Mockito.any(Shipment.class));
    }


    @Test
    @Order(13)
    void CRUD_testDeleteShipment() throws Exception {
        Long shipmentId = 1L;

        doNothing().when(shipmentService).deleteShipment(shipmentId);

        mockMvc.perform(delete("/api/shipments/{id}", shipmentId))
                .andExpect(status().isOk());

        verify(shipmentService, times(1)).deleteShipment(shipmentId);
    }


    @Test
    @Order(14)
    void CRUD_testGetAllDeliveries() throws Exception {
        List<Delivery> deliveries = new ArrayList<>();
        Delivery delivery1 = new Delivery();
        delivery1.setId(1L);
        delivery1.setRecipient("Alice");
        delivery1.setOrigin("New York");
        delivery1.setDestination("Los Angeles");
        delivery1.setDeliveryDate("2023-01-01");
        delivery1.setStatus("Pending");

        Delivery delivery2 = new Delivery();
        delivery2.setId(2L);
        delivery2.setRecipient("Bob");
        delivery2.setOrigin("Chicago");
        delivery2.setDestination("Houston");
        delivery2.setDeliveryDate("2023-01-02");
        delivery2.setStatus("Delivered");

        deliveries.add(delivery1);
        deliveries.add(delivery2);

        when(deliveryService.getAllDeliveries()).thenReturn(deliveries);

        mockMvc.perform(get("/api/deliveries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(deliveries.size()))
                .andExpect(jsonPath("$[0].recipient").value("Alice"))
                .andExpect(jsonPath("$[1].recipient").value("Bob"));

        verify(deliveryService, times(1)).getAllDeliveries();
    }

   
    @Test
    @Order(15)
    void CRUD_testCreateDelivery() throws Exception {
        Delivery delivery = new Delivery();
        delivery.setRecipient("Alice");
        delivery.setOrigin("New York");
        delivery.setDestination("Los Angeles");
        delivery.setDeliveryDate("2023-01-01");
        delivery.setStatus("Pending");

        Delivery savedDelivery = new Delivery();
        savedDelivery.setId(1L);
        savedDelivery.setRecipient("Alice");
        savedDelivery.setOrigin("New York");
        savedDelivery.setDestination("Los Angeles");
        savedDelivery.setDeliveryDate("2023-01-01");
        savedDelivery.setStatus("Pending");

        when(deliveryService.createDelivery(Mockito.any(Delivery.class))).thenReturn(savedDelivery);

        mockMvc.perform(post("/api/deliveries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(delivery)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedDelivery.getId()))
                .andExpect(jsonPath("$.recipient").value(savedDelivery.getRecipient()));

        verify(deliveryService, times(1)).createDelivery(Mockito.any(Delivery.class));
    }

   
    @Test
    @Order(16)
    void CRUD_testDeleteDelivery() throws Exception {
        Long deliveryId = 1L;

        doNothing().when(deliveryService).deleteDelivery(deliveryId);

        mockMvc.perform(delete("/api/deliveries/{id}", deliveryId))
                .andExpect(status().isOk());

        verify(deliveryService, times(1)).deleteDelivery(deliveryId);
    }

    
    @Test
    @Order(17)
    void CRUD_testGetSortedDeliveries() throws Exception {
        List<Delivery> deliveries = new ArrayList<>();
        Delivery delivery = new Delivery();
        delivery.setRecipient("Alice");
        delivery.setOrigin("New York");
        delivery.setDestination("Los Angeles");
        delivery.setDeliveryDate("2023-01-01");
        delivery.setStatus("Pending");
        deliveries.add(delivery);

        when(deliveryService.getSortedDeliveries("deliveryDate")).thenReturn(deliveries);

        mockMvc.perform(get("/api/deliveries/sorted")
                .param("sortBy", "deliveryDate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].recipient").value("Alice"));

        verify(deliveryService, times(1)).getSortedDeliveries("deliveryDate");
    }


    @Test
    @Order(18)
    public void CRUD_testCreatePayment() throws Exception {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setMemberId(101L);
        payment.setAmount(500.0);
        payment.setPaymentMethod("Credit Card");

        Mockito.when(paymentService.createPayment(any(Payment.class))).thenReturn(payment);

        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.memberId").value(101))
                .andExpect(jsonPath("$.amount").value(500.0))
                .andExpect(jsonPath("$.paymentMethod").value("Credit Card"));
    }

    @Test
    @Order(19)
    public void CRUD_testGetAllPayments() throws Exception {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setMemberId(101L);
        payment.setAmount(500.0);
        payment.setPaymentMethod("Credit Card");

        Mockito.when(paymentService.getAllPayments()).thenReturn(Collections.singletonList(payment));

        mockMvc.perform(get("/api/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].memberId").value(101))
                .andExpect(jsonPath("$[0].amount").value(500.0))
                .andExpect(jsonPath("$[0].paymentMethod").value("Credit Card"));
    }

    @Test
    @Order(20)
    public void CRUD_testGetPaymentById() throws Exception {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setMemberId(101L);
        payment.setAmount(500.0);
        payment.setPaymentMethod("Credit Card");

        Mockito.when(paymentService.getPaymentById(anyLong())).thenReturn(payment);

        mockMvc.perform(get("/api/payments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.memberId").value(101))
                .andExpect(jsonPath("$.amount").value(500.0))
                .andExpect(jsonPath("$.paymentMethod").value("Credit Card"));
    }

    @Test
    @Order(21)
    public void CRUD_testDeletePayment() throws Exception {
        Mockito.doNothing().when(paymentService).deletePayment(anyLong());

        mockMvc.perform(delete("/api/payments/1"))
                .andExpect(status().isNoContent());
    }

	//CRUD User
    @Test
    @Order(22)
    public void CRUD_testCreateUser() throws Exception {
        User user = new User(1L, "username", "user@example.com");
        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);
 
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.email").value("user@example.com"));
    }
 
    @Test
    @Order(23)
    public void CRUD_testGetUserById() throws Exception {
        User user = new User(1L, "username", "user@example.com");
        Mockito.when(userService.getUserById(anyLong())).thenReturn(user);
 
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("username"))
                .andExpect(jsonPath("$.email").value("user@example.com"));
    }
 
    @Test
    @Order(24)
    public void CRUD_testGetAllUsers() throws Exception {
        User user = new User(1L, "username", "user@example.com");
        Mockito.when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));
 
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("username"))
                .andExpect(jsonPath("$[0].email").value("user@example.com"));
    }
 
    @Test
    @Order(25)
    public void CRUD_testUpdateUser() throws Exception {
        User user = new User(1L, "updatedUser", "updated@example.com");
        Mockito.when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);
 
        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("updatedUser"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }
 
    @Test
    @Order(26)
    public void CRUD_testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(anyLong());
 
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(27)
	void CRUD_testPathVariableAnnotations() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/controller/ShipmentController.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@PathVariable"), "Delivery entity should contain @PathVariable annotation");
	}

	@Test
    @Order(28)
	void CRUD_testRequestBodyAnnotations() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/controller/ShipmentController.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@RequestBody"), "Delivery entity should contain @RequestBody annotation");
	}

    @Test
    @Order(29)
    public void JPQL_testFindByEmail() {
        User user1 = new User();
        user1.setUsername("John Doe");
        user1.setEmail("john.doe@example.com");
        userRepository.save(user1);

        List<User> result = userRepository.findByEmail("john.doe@example.com");

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getUsername()).isEqualTo("John Doe");
    }

    @Test
    @Order(30)
    public void JPQL_testFindByEmailEmpty() {
        User user1 = new User();
        user1.setUsername("John Doe");
        user1.setEmail("john.doe@example.com");
        userRepository.save(user1);

        List<User> result = userRepository.findByEmail("nonexistent@example.com");

        assertThat(result).isEmpty();
    }

    @Test
    @Order(31)
	void PaginateSorting_testPaginateShipmentControllers() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/controller/ShipmentController.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("Page<Shipment>"), "Book entity should contain @RequestBody annotation");
	}

	@Test
    @Order(32)
	void PaginateSorting_testPaginateShipmentService() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/service/ShipmentService.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("Pageable"), "Book entity should contain @RequestBody annotation");
	}

    @Test
    @Order(33)
    void PaginateSorting_testGetSortedDelivery() throws Exception {
        Delivery delivery1 = new Delivery();
        delivery1.setRecipient("John Doe");
        delivery1.setOrigin("New York");
        delivery1.setDestination("Los Angeles");
        delivery1.setDeliveryDate("2025-01-10");
        delivery1.setStatus("Pending");
    
        Delivery delivery2 = new Delivery();
        delivery2.setRecipient("Jane Smith");
        delivery2.setOrigin("San Francisco");
        delivery2.setDestination("Chicago");
        delivery2.setDeliveryDate("2025-01-08");
        delivery2.setStatus("Delivered");
    
        Delivery delivery3 = new Delivery();
        delivery3.setRecipient("Sam Wilson");
        delivery3.setOrigin("Houston");
        delivery3.setDestination("Miami");
        delivery3.setDeliveryDate("2025-01-05");
        delivery3.setStatus("Pending");
    
        List<Delivery> deliveries = Arrays.asList(delivery1, delivery2, delivery3);
    
        deliveries.sort(Comparator.comparing(Delivery::getDeliveryDate));
    
        when(deliveryService.getSortedDeliveries("deliveryDate")).thenReturn(deliveries);
    
        mockMvc.perform(get("/api/deliveries/sorted?sortBy=deliveryDate"))
                .andExpect(status().isOk()) // Verify the HTTP status is OK
                .andExpect(jsonPath("$.length()").value(3)) // Verify the length of the array
                .andExpect(jsonPath("$[0].deliveryDate").value("2025-01-05")) // Verify the first delivery's date after sorting
                .andExpect(jsonPath("$[1].deliveryDate").value("2025-01-08")) // Verify the second delivery's date
                .andExpect(jsonPath("$[2].deliveryDate").value("2025-01-10")); // Verify the third delivery's date
    
        verify(deliveryService, times(1)).getSortedDeliveries("deliveryDate");
    }
    

	@Test
    @Order(34)
	void Mapping_testEntityHasOneToManyRelation() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/entity/Shipment.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@OneToMany"), "Entity should contain @OneToMany annotation");
	}


	@Test
    @Order(35)
	void Mapping_testUserHasOneToMany() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/entity/User.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@OneToMany"), "Member entity should contain @OneToMany annotation");
	}

	@Test
    @Order(36)
	void Mapping_testDeliveryHasColumn() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/entity/Delivery.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@Column"), "Book entity should contain @Column annotation");
	}


	@Test
    @Order(37)
	void Mapping_testShipmentHasOneToMany() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/entity/Shipment.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("@OneToMany"), "Author entity should contain @OneToMany annotation");
	}	

	@Test
    @Order(38)
	public void SwaggerUI_testConfigurationFolder() {
		String directoryPath = "src/main/java/com/example/demo/config"; // Replace with the path to your directory
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}


	@Test
    @Order(39)
	void SwaggerUI_testSwaggerConfigFile() {
		String filePath = "src/main/java/com/example/demo/config/SwaggerConfig.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
    @Order(40)
	public void LOG_testLogFolderAndFileCreation() {
		String LOG_FOLDER_PATH = "logs";
		String LOG_FILE_PATH = "logs/application.log";
		File logFolder = new File(LOG_FOLDER_PATH);
		assertTrue(logFolder.exists(), "Log folder should be created");
		File logFile = new File(LOG_FILE_PATH);
		assertTrue(logFile.exists(), "Log file should be created inside 'logs' folder");
	}

	@Test
    @Order(41)
	void AOP_testAOPConfigFile() {
		String filePath = "src/main/java/com/example/demo/aspect/LoggingAspect.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
    @Order(42)
	void AOP_testAOPConfigFileAspect() throws Exception {
		Path entityFilePath = Paths.get("src/main/java/com/example/demo/aspect/LoggingAspect.java");
		String entityFileContent = Files.readString(entityFilePath);
		assertTrue(entityFileContent.contains("Aspect"), "Book entity should contain @RequestBody annotation");
	}
}
