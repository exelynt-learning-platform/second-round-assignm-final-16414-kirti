package com.Ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.Model.Cart;
import com.Ecommerce.Model.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
	 List<Cart> findByUser(User user);

}
