package com.sda.springbootdemo.exercises.repository;

import com.sda.springbootdemo.exercises.model.Receipt2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Receipt2Repository extends JpaRepository<Receipt2, Long> {}
