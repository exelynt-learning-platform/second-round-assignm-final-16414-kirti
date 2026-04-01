package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.Ecommerce.Model.Cart;
import com.Ecommerce.Model.User;
import com.Ecommerce.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Cart addToCart(@AuthenticationPrincipal User user,
                         @RequestParam Long productId,
                         @RequestParam Integer quantity) {
        return service.addToCart(user, productId, quantity);
    }

    @GetMapping("/my-cart")
    public List<Cart> getUserCart(@AuthenticationPrincipal User user) {
        return service.getUserCart(user);
    }
}
