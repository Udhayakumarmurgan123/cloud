package com.example.demo.service;

import com.example.demo.entity.Payment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + id));
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + id));

        existingPayment.setAmount(paymentDetails.getAmount());
        existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
        existingPayment.setPaymentMethod(paymentDetails.getPaymentMethod());

        return paymentRepository.save(existingPayment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}