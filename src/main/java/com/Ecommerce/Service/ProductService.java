package com.Ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Ecommerce.Model.Product;
import com.Ecommerce.Repository.ProductRepository;
import com.Ecommerce.Repository.UserRepository;


@Service

public class ProductService {



	private final ProductRepository repo;

	public ProductService(ProductRepository repo) {
	    this.repo = repo;
	}

	    public Product add(Product p) {
	        return repo.save(p);
	    }

	    public List<Product> getAll() {
	        return repo.findAll();
	    }

	    public Product getById(Long id) {
	        return repo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Not found"));
	    }
	    
	    public Product update(Long id, Product p) {
	        Product existing = getById(id);

	        existing.setName(p.getName());
	        existing.setPrice(p.getPrice());
	        existing.setStockQuantity(p.getStockQuantity());
	        existing.setDescription(p.getDescription());
	        existing.setImageUrl(p.getImageUrl());

	        return repo.save(existing);
	    }

	    public void delete(Long id) {
	        repo.deleteById(id);
	    }
	}

