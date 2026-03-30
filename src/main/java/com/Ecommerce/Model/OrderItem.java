package com.Ecommerce.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderItem {

	    @Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JsonBackReference
	    private Order order;

	    @ManyToOne
	    private Product product;

	    @NotNull(message = "Quantity is required")
	    @Min(value = 1, message = "Quantity must be at least 1")
	    private Integer quantity;
	   
	 

		public OrderItem() {
			super();
			
		}


		public OrderItem(Long id, Order order, Product product,Integer quantity) {
			super();
			this.id = id;
			this.order = order;
			this.product = product;
			this.quantity = quantity;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Order getOrder() {
			return order;
		}


		public void setOrder(Order order) {
			this.order = order;
		}


		public Product getProduct() {
			return product;
		}


		public void setProduct(Product product) {
			this.product = product;
		}


		public Integer getQuantity() {
			return quantity;
		}


		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		
		
	    
	}


