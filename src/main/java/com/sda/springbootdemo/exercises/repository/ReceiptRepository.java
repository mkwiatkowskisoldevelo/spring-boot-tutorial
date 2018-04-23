package com.sda.springbootdemo.exercises.repository;

import com.sda.springbootdemo.exercises.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByDateGreaterThanEqualAndDateLessThanEqual(LocalDateTime startDate, LocalDateTime endDate);
    List<Receipt> findByDateGreaterThanEqual(LocalDateTime startDate);
    List<Receipt> findByDateLessThanEqual(LocalDateTime endDate);
}
