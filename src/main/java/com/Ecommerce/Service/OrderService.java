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
    private final PaymentService paymentService;

    private static final String STATUS_CREATED = "CREATED";
    private static final String STATUS_PAID = "PAID";

    public OrderService(CartRepository cartRepo,
                        OrderRepository orderRepo,
                        ProductRepository productRepo,
                        PaymentService paymentService) {
        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.paymentService = paymentService;
    }


    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(User user) {

        List<Cart> cartItems = cartRepo.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        for (Cart cart : cartItems) {
            Product product = cart.getProduct();

            if (product.getStockQuantity() < cart.getQuantity()) {
                throw new RuntimeException("Insufficient stock for " + product.getName());
            }
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(STATUS_CREATED);

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        
        synchronized (this) {
            for (Cart cart : cartItems) {

                Product product = productRepo.findById(cart.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                product.setStockQuantity(product.getStockQuantity() - cart.getQuantity());
                productRepo.save(product);

                OrderItem item = new OrderItem();
                item.setOrder(order);
                item.setProduct(product);
                item.setQuantity(cart.getQuantity());

                orderItems.add(item);

                total += product.getPrice() * cart.getQuantity();
            }
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(total);

        return orderRepo.save(order);
    }


    public List<Order> getOrders(User user) {
        return orderRepo.findByUser(user);
    }

    
    @Transactional(rollbackFor = Exception.class)
    public String pay(Long orderId, User user) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

    
        if (order.getUser() == null ||
            !order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        if (STATUS_PAID.equals(order.getStatus())) {
            throw new RuntimeException("Order already paid");
        }

        String paymentId = paymentService.createPayment(order.getTotalPrice());

        order.setStatus(STATUS_PAID);
        orderRepo.save(order);

        
        List<Cart> cartItems = cartRepo.findByUser(user);
        cartRepo.deleteAll(cartItems);

        return "PAYMENT_SUCCESS:" + paymentId;
    }
}
