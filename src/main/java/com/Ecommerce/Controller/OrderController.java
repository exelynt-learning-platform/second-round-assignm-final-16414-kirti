package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.Ecommerce.Model.Order;
import com.Ecommerce.Model.User;
import com.Ecommerce.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public Order createOrder(@AuthenticationPrincipal User user) {
        return service.createOrder(user);
    }

    
    @PostMapping("/pay/{orderId}")
    public String pay(@PathVariable Long orderId,
                      @AuthenticationPrincipal User user) {
        return service.pay(orderId, user);
    }

    
    @GetMapping("/my-orders")
    public List<Order> getOrders(@AuthenticationPrincipal User user) {
        return service.getOrders(user);
    }
}
