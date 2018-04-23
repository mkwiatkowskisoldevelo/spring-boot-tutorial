package com.sda.springbootdemo.exercises.controller;

import com.sda.springbootdemo.exercises.model.CounterRequest;
import com.sda.springbootdemo.exercises.model.Person;
import com.sda.springbootdemo.exercises.service.CountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @Autowired
    private CountingService countingService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello my new app!");
    }

    @GetMapping("/counter")
    public ResponseEntity<String> getCounter() {
        String wartoscDozwrocenia = String.valueOf(countingService.getLicznik());
        if (countingService.getLicznik() < 10) {
            return ResponseEntity.ok(wartoscDozwrocenia);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/counter")
    public ResponseEntity postCounter() {
        countingService.increment();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/count")
    public ResponseEntity postCounter(@RequestBody CounterRequest counterRequest) {
        countingService.increment(counterRequest.getHowMany());
        return ResponseEntity.ok().build();
    }
}
