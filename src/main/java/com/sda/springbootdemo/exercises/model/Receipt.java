package com.sda.springbootdemo.exercises.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="receipts")
public class Receipt extends BaseEntity {

    @Column(nullable = false)
    private String buyer;

    @Column(nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "receipt")
    private List<Product> products = new ArrayList<>();
}
