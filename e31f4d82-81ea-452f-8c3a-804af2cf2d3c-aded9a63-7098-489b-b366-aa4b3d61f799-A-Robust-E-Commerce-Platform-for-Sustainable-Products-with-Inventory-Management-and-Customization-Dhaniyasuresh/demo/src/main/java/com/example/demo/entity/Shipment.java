package com.example.demo.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Shipment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String origin;

private String destination;

private String shipmentDate;

private String estimatedDeliveryDate;

private String status;

private Double weight;

private Double volume;

private Double cost;

@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
@JsonIgnore
private List<Delivery> deliveries;

public Shipment(Long id, String origin, String destination, String shipmentDate, String estimatedDeliveryDate, String status, Double weight, Double volume, Double cost) {
    this.id = id;
    this.origin = origin;
    this.destination = destination;
    this.shipmentDate = shipmentDate;
    this.estimatedDeliveryDate = estimatedDeliveryDate;
    this.status = status;
    this.weight = weight;
    this.volume = volume;
    this.cost = cost;
    }
    }