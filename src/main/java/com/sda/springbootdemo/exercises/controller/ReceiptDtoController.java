package com.sda.springbootdemo.exercises.controller;

import com.sda.springbootdemo.exercises.dto.ProductDto;
import com.sda.springbootdemo.exercises.dto.ReceiptDto;
import com.sda.springbootdemo.exercises.exception.NotFoundException;
import com.sda.springbootdemo.exercises.model.Receipt;
import com.sda.springbootdemo.exercises.repository.ReceiptRepository;
import com.sda.springbootdemo.exercises.service.ReceiptService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ReceiptDtoController {

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private ReceiptRepository receiptRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiptDto create(@RequestBody Receipt receipt) {
        return new ReceiptDto(receiptRepository.save(receipt));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReceiptDto> search(
        @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
        @RequestParam(value = "endDate", required = false) LocalDateTime endDate) {
        return receiptService.search(startDate, endDate)
            .stream()
            .map(ReceiptDto::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReceiptDto get(@PathVariable("id") Long id) {
        return new ReceiptDto(receiptRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("No receipt find with %s id", id))));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReceiptDto update(@PathVariable("id") Long id, @RequestBody Receipt receipt) {
        Receipt savedReceipt = receiptRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("No product find with %s id", id)));
        receipt.setId(id);
        return new ReceiptDto(receiptRepository.save(savedReceipt));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long id) {
        Receipt receipt = receiptRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Receipt with id %s not found", id)));
        receiptRepository.delete(receipt);
    }

    @GetMapping("/{id}/summary")
    @ResponseStatus(HttpStatus.OK)
    public Double summary(@PathVariable("id") Long id) {
        return receiptService.summary(id);
    }

    @GetMapping("/summary")
    @ResponseStatus(HttpStatus.OK)
    public Double summary(@RequestParam List<Long> ids) {
        return receiptService.summary(ids);
    }

    @GetMapping("{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> receiptProducts(@PathVariable("id") Long id) {
        Receipt receipt = receiptRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("No receipt find with %s id", id)));
        return receipt.getProducts()
            .stream()
            .map(ProductDto::new)
            .collect(Collectors.toList());
    }
}
