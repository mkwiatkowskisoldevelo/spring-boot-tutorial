package com.sda.springbootdemo.exercises.controller;

import com.sda.springbootdemo.exercises.exception.NotFoundException;
import com.sda.springbootdemo.exercises.model.Receipt2;
import com.sda.springbootdemo.exercises.repository.Receipt2Repository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipts2")
public class Receipt2Controller {

    @Autowired
    private Receipt2Repository receipt2Repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Receipt2 create(@RequestBody Receipt2 receipt) {
        return receipt2Repository.save(receipt);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Receipt2> search() {
        return receipt2Repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Receipt2 get(@PathVariable("id") Long id) {
        return receipt2Repository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("No receipt find with %s id", id)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Receipt2 update(@PathVariable("id") Long id, @RequestBody Receipt2 receipt) {
        if (receipt2Repository.existsById(id)) {
            throw new NotFoundException(String.format("No receipt find with %s id", id));
        }
        receipt.setId(id);
        return receipt2Repository.save(receipt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long id) {
        Receipt2 receipt = receipt2Repository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Receipt with id %s not found", id)));
        receipt2Repository.delete(receipt);
    }
}
