package com.sda.springbootdemo.exercises.service;

import com.sda.springbootdemo.exercises.exception.NotFoundException;
import com.sda.springbootdemo.exercises.model.Product;
import com.sda.springbootdemo.exercises.model.Receipt;
import com.sda.springbootdemo.exercises.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> search(LocalDateTime startDate, LocalDateTime endDate) {
        if (null != startDate && null != endDate) {
            return receiptRepository.findByDateGreaterThanEqualAndDateLessThanEqual(startDate, endDate);
        } else if (null != startDate) {
            return receiptRepository.findByDateGreaterThanEqual(startDate);
        } else if (null != endDate) {
            return receiptRepository.findByDateLessThanEqual(endDate);
        }
        return receiptRepository.findAll();
    }

    public Double summary(Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Receipt with %s not found", id)));

        return receipt.getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Double summary(Collection<Long> ids) {
        List<Receipt> receipts = receiptRepository.findAllById(ids);

        return receipts
                .stream()
                .flatMap(receipt -> receipt.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
