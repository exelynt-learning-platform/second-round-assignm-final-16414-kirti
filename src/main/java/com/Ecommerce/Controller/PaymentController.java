package com.Ecommerce.Controller;

import org.springframework.web.bind.annotation.*;

import com.Ecommerce.Service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public String createPayment(@RequestParam Double amount) {
        return service.createPayment(amount);
    }
}