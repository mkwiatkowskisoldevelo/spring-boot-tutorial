package com.sda.springbootdemo.exercises.service;

import com.sda.springbootdemo.exercises.exception.NotFoundException;
import com.sda.springbootdemo.exercises.exception.ValidationException;
import com.sda.springbootdemo.exercises.model.Product;
import com.sda.springbootdemo.exercises.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        validateName(product.getName(), null);
        return productRepository.save(product);
    }

    public Product update(Product product, Long id) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("No product find with %s id", id)));

        validateName(product.getName(), savedProduct.getName());

        product.setId(id);
        return productRepository.save(product);
    }

    private void validateName(String newName, String currentName) {
        if (null == newName) {
            throw new ValidationException("Product name is missing");
        }
        if ((null != currentName && !newName.equals(currentName))
                || productRepository.existsByName(newName)) {
            throw new ValidationException("Product name is not unique");
        }
    }
}
