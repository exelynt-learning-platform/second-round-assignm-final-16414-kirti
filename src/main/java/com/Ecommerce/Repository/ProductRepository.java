package com.Ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
