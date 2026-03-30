package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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
                          @RequestParam Integer quantity) {
        return service.addToCart(userId, productId, quantity);
    }

    @GetMapping("/{userId}")
    public List<Cart> getUserCart(@PathVariable Long userId) {
        return service.getUserCart(userId);
    }
}
