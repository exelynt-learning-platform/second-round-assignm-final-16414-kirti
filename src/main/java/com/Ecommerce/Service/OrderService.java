
package com.Ecommerce.Service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ecommerce.Model.Cart;
import com.Ecommerce.Model.Order;
import com.Ecommerce.Model.OrderItem;
import com.Ecommerce.Model.Product;
import com.Ecommerce.Model.User;
import com.Ecommerce.Repository.CartRepository;
import com.Ecommerce.Repository.OrderRepository;
import com.Ecommerce.Repository.ProductRepository;

@Service
public class OrderService {

    private final CartRepository cartRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderService(CartRepository cartRepo,
                        OrderRepository orderRepo,
                        ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public Order createOrder(User user) {

        List<Cart> cartItems = cartRepo.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus("CREATED");

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (Cart cart : cartItems) {

            Product product = cart.getProduct();

            if (product.getStockQuantity() < cart.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + product.getName());
            }

            // Reduce stock
            product.setStockQuantity(product.getStockQuantity() - cart.getQuantity());
            productRepo.save(product);

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(cart.getQuantity());

            orderItems.add(item);

            total += product.getPrice() * cart.getQuantity();
        }

        order.setTotalPrice(total);
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepo.save(order);

        
        cartRepo.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> getOrders(User user) {
        return orderRepo.findByUser(user);
    }

    public String pay(Long orderId, User user) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        if ("PAID".equals(order.getStatus())) {
            throw new RuntimeException("Already paid");
        }

        order.setStatus("PAID");
        orderRepo.save(order);

        return "Payment Successful (Simulated)";
    }
}


