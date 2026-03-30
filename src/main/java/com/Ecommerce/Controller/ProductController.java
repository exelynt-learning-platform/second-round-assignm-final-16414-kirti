package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.Model.Product;

import com.Ecommerce.Service.ProductService;

@RestController
@RequestMapping("/products")

public class ProductController {
	
	private final ProductService service;
	
	public ProductController(ProductService service) {
		this.service=service;
   

}

	    @PostMapping
	    public Product add(@RequestBody Product p) {
	        return service.add(p);
	    }

	    @GetMapping
	    public List<Product> getAll() {
	        return service.getAll();
	    }

	    @GetMapping("/{id}")
	    public Product get(@PathVariable Long id) {
	        return service.getById(id);
	    }
	    
	    @PutMapping("/{id}")
	    public Product update(@PathVariable Long id, @RequestBody Product p) {
	        return service.update(id, p);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        service.delete(id);
	    }
	}

