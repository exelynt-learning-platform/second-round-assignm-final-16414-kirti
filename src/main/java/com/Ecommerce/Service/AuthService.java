package com.Ecommerce.Service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PaymentService {

    public String createPayment(Double amount) {

        if (amount == null || amount <= 0) {
            throw new RuntimeException("Invalid payment amount");
        }

        
        return "PAY_" + UUID.randomUUID().toString();
    }
}
