package com.Ecommerce.Controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.Model.Cart;
import com.Ecommerce.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long userId,
                          @RequestParam Long productId,
                          @RequestParam int qty) {

        return service.addToCart(userId, productId, qty);
    }

    @GetMapping
    public List<Cart> getCart(@RequestParam Long userId) {
        return service.getUserCart(userId);
    }
}
