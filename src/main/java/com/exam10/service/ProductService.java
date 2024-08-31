package com.exam10.service;

import com.exam10.exception.ResourceNotFoundException;
import com.exam10.model.Product;
import com.exam10.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Product found with this id :: " + id));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setType(productDetails.getType());
        product.setQuantity(productDetails.getQuantity());
        product.setUnitPrice(productDetails.getUnitPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Product found with this id : " + id));
        productRepository.delete(product);
    }
}

