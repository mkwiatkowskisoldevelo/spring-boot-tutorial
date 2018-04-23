package com.sda.springbootdemo.exercises.repository;

import com.sda.springbootdemo.exercises.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
