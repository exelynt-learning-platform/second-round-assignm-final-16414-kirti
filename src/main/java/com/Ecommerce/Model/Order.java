package com.Ecommerce.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "orders")
public class Order {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    private User user;

	    @NotNull(message = "Total price is required")
	    @Positive(message = "Total price must be greater than 0")
	    private Double totalPrice;

	    @NotBlank(message = "Status is required")
	    private String status;
	    

	    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	    @JsonManagedReference
	    private List<OrderItem> orderItems;

		public Order() {
			super();
			
		}


		public Order(Long id, User user, Double totalPrice, String status, List<OrderItem> orderItems) {
			super();
			this.id = id;
			this.user = user;
			this.totalPrice = totalPrice;
			this.status = status;
			this.orderItems = orderItems;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public Double getTotalPrice() {
			return totalPrice;
		}


		public void setTotalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public List<OrderItem> getOrderItems() {
			return orderItems;
		}


		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}
		
		

}