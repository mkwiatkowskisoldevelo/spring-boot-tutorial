package com.sda.springbootdemo.exercises.service;

import org.springframework.stereotype.Service;

@Service
public class CountingService {

    public int licznik = 0;

    public void increment() {
        licznik++;
    }

    public int getLicznik() {
        return licznik;
    }

    public void increment(int howMany) {
        licznik += howMany;
    }
}
