package com.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Model.Product;
import com.Ecommerce.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setStockQuantity(updatedProduct.getStockQuantity());

        return productRepo.save(product);
    }

    
    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepo.deleteById(id);
    }
}
