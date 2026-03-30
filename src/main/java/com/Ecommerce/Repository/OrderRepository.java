package com.Ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.Model.Order;
import com.Ecommerce.Model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
	 List<Order> findByUser(User user);

}
