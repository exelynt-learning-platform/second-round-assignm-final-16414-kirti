package com.Ecommerce.Service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    public String createPayment(Double amount) {

        
        if (amount == null || amount <= 0) {
            throw new RuntimeException("Invalid payment amount");
        }

        
        String paymentId = "PAY_" + UUID.randomUUID().toString();

        
        System.out.println("Payment processed: " + paymentId + " for amount: " + amount);

        return paymentId;
    }
}
