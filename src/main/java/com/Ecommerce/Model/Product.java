package com.Ecommerce.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name="product")
public class Product {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "Product name is required")
	    private String name;

	    @NotBlank(message = "Description is required")
	    private String description;

	    @NotNull(message = "Price is required")
	    @Positive(message = "Price must be greater than 0")
	    private Double price;

	    @NotNull(message = "Stock is required")
	    @Min(value = 0, message = "Stock cannot be negative")
	    private Integer stockQuantity;

	    private String imageUrl;

	    public Product() {}

	    public Product(Long id, String name, String description, Double price, Integer stockQuantity, String imageUrl) {
	        this.id = id;
	        this.name = name;
	        this.description = description;
	        this.price = price;
	        this.stockQuantity = stockQuantity;
	        this.imageUrl = imageUrl;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Integer getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(Integer stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

	    
	}
	

