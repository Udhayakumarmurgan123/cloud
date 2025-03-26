package com.example.demo.service;

import com.example.demo.entity.Delivery;
import com.example.demo.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery updateDelivery(Long id, Delivery delivery) {
        Optional<Delivery> existingDelivery = deliveryRepository.findById(id);
        if (existingDelivery.isPresent()) {
            Delivery updatedDelivery = existingDelivery.get();
            updatedDelivery.setRecipient(delivery.getRecipient());
            updatedDelivery.setOrigin(delivery.getOrigin());
            updatedDelivery.setDestination(delivery.getDestination());
            updatedDelivery.setDeliveryDate(delivery.getDeliveryDate());
            updatedDelivery.setStatus(delivery.getStatus());
            updatedDelivery.setShipment(delivery.getShipment());
            return deliveryRepository.save(updatedDelivery);
        } else {
            throw new RuntimeException("Delivery not found with id: " + id);
        }
    }

    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }

    public List<Delivery> getSortedDeliveries(String sortBy) {
        return deliveryRepository.findAll(Sort.by(sortBy));
    }
}
