package com.Ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
