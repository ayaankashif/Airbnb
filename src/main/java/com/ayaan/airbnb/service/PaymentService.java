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

    public Payment saveHotel(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getHotelById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment updateHotel(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deleteHotel(Integer id){
        paymentRepository.deleteById(id);
    }
}
