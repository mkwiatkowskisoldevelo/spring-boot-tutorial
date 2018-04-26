package com.sda.springbootdemo.exercises.repository.custom;

import com.sda.springbootdemo.exercises.model.Product;
import com.sda.springbootdemo.exercises.model.Receipt;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<Product> search(String name, Receipt receipt, Pageable pageable);
    List<Product> search(String name, Receipt receipt);
}
