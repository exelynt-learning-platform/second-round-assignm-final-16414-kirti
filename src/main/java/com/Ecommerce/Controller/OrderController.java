

package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.Ecommerce.Model.Order;
import com.Ecommerce.Model.User;
import com.Ecommerce.Repository.UserRepository;
import com.Ecommerce.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final UserRepository userRepo;

    public OrderController(OrderService service, UserRepository userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    
    @PostMapping("/create")
    public Order create(@RequestParam Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return service.createOrder(user);
    }

   
    @PostMapping("/pay/{orderId}")
    public String pay(@PathVariable Long orderId,
                      @RequestParam Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return service.pay(orderId, user);
    }

    
    @GetMapping("/my-orders")
    public List<Order> getOrders(@RequestParam Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return service.getOrders(user);
    }
}