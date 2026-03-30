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

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public CartService(CartRepository cartRepo,
                       ProductRepository productRepo,
                       UserRepository userRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public Cart addToCart(Long userId, Long productId, int qty) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < qty) {
            throw new RuntimeException("Out of stock");
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(qty);

        return cartRepo.save(cart);
    }

    public List<Cart> getUserCart(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepo.findByUser(user);
    }
}  
