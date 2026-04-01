package com.Ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Ecommerce.Model.Cart;
import com.Ecommerce.Model.Product;
import com.Ecommerce.Model.User;
import com.Ecommerce.Repository.CartRepository;
import com.Ecommerce.Repository.ProductRepository;

@Service
public class CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo,
                       ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public Cart addToCart(User user, Long productId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Invalid quantity");
        }

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart existing = cartRepo.findByUserIdAndProductId(user.getId(), productId);

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

    public List<Cart> getUserCart(User user) {
        return cartRepo.findByUser(user);
    }
}
