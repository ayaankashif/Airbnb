package com.ayaan.airbnb.service;

import org.springframework.stereotype.Service;

import com.ayaan.airbnb.model.Payment;
import com.ayaan.airbnb.repository.PaymentRepository;

@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayament(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Integer id){
        paymentRepository.deleteById(id);
    }
}
