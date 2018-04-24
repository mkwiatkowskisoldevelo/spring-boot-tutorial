package com.sda.springbootdemo.exercises.repository;

import com.sda.springbootdemo.exercises.model.Product;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);
    Optional<Product> findByNameIgnoreCase(String name);

    List<Product> findByNameIgnoreCaseContaining(String name);
    List<Product> findByPriceGreaterThanEqual(Double minPrice);
    List<Product> findByPriceLessThanEqual(Double maxPrice);
    List<Product> findByNameIgnoreCaseContainingAndPriceLessThanEqual(
        String name,
        Double maxPrice);
    List<Product> findByNameIgnoreCaseContainingAndPriceGreaterThanEqual(
        String name,
        Double minPrice);
    List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(
        Double minPrice,
        Double maxPrice);
    List<Product> findByNameIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(
        String name,
        Double minPrice,
        Double maxPrice);
    List<Product> findByNameIgnoreCaseContainingAndPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(
        String name,
        Double minPrice,
        Double maxPrice);
}
