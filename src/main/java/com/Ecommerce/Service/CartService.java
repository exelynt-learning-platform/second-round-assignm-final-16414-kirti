package com.Ecommerce.Service;

import java.util.List;

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

    public Cart addToCart(Long userId, Long productId, int quantity) {

        
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        Cart existingItem = cartRepo.findByUserIdAndProductId(userId, productId);

        if (existingItem != null) {
            
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return cartRepo.save(existingItem);
        }

        
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProduct(product);
        cart.setQuantity(quantity);

        return cartRepo.save(cart);
    }
}
