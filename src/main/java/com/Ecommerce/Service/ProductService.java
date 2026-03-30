package com.Ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Model.Cart;
import com.Ecommerce.Model.Product;
import com.Ecommerce.Model.User;
import com.Ecommerce.Repository.CartRepository;
import com.Ecommerce.Repository.ProductRepository;
import com.Ecommerce.Repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    public Cart addToCart(Long userId, Long productId, int quantity) {

        
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        
        Cart existing = cartRepo.findByUser_IdAndProduct_Id(userId, productId);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
            return cartRepo.save(existing);
        }

        
        Cart cart = new Cart();
        cart.setUser(user);         
        cart.setProduct(product);  
        cart.setQuantity(quantity);

        return cartRepo.save(cart);
    }
}
