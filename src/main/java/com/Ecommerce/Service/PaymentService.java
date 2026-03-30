package com.Ecommerce.Service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String createPayment(Double amount) {

        
        if (amount <= 0) {
            throw new RuntimeException("Invalid payment amount");
        }

        return "Payment initiated successfully for amount: " + amount;
    }
}