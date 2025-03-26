package com.example.demo.service;

import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Page<Shipment> getAllShipments(Pageable pageable) {
        return shipmentRepository.findAll(pageable);
    }

    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id " + id));
    }

    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipment(Long id, Shipment updatedShipment)
    {
        Shipment existingShipment = shipmentRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Shipment not found with id: "+id));

        existingShipment.setStatus(updatedShipment.getStatus());
        existingShipment.setShipmentDate(updatedShipment.getShipmentDate());

        return shipmentRepository.save(existingShipment);
    }

    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);

    }
}