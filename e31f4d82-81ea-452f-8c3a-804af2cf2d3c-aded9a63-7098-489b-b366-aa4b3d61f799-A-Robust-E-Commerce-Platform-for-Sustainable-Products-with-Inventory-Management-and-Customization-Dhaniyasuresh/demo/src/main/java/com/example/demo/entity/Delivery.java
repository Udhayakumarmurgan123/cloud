package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String recipient;

    private String origin;

    private String destination;

    private String deliveryDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
    
    public Long getId() {
        return id;
        }

        public void setId(Long id) {
            this.id = id;
            }

            public String getRecipient() {
                return recipient;
                }

                public void setRecipient(String recipient) {
                    this.recipient = recipient;
                    }

                    public String getOrigin() {
                        return origin;
                        }

                        public void setOrigin(String origin) {
                            this.origin = origin;
                            }

                            public String getDestination() {
                            return destination;
                            }

                            public void setDestination(String destination) {
                                this.destination = destination;
                                }

                                public String getDeliveryDate() {
                                    return deliveryDate;
                                    }

                                    public void setDeliveryDate(String deliveryDate) {
                                        this.deliveryDate = deliveryDate;
                                        }

                                        public String getStatus() {
                                        return status;
                                        }

                                        public void setStatus(String status) {
                                            this.status = status;
                                            }

                                            public Shipment getShipment() {
                                                return shipment;
                                                }

                                                public void setShipment(Shipment shipment) {
                                                    this.shipment = shipment;
                                                    }

                                                public void setAddress(Class<? extends Delivery> class1) {
                                                    // TODO Auto-generated method stub
                                                    throw new UnsupportedOperationException("Unimplemented method 'setAddress'");
                                                }
                                                    }