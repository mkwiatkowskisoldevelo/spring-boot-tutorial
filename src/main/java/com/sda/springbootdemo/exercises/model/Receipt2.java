package com.sda.springbootdemo.exercises.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="receipts2")
public class Receipt2 extends BaseEntity {

    @Column(nullable = false, name = "buyer")
    private String buyer;

    @Column(nullable = false, name = "date")
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "receipt_products",
        joinColumns =
        @JoinColumn(name = "receipt_id", nullable = false),
        inverseJoinColumns =
        @JoinColumn(name = "product_id", nullable = false))
    private List<Product> products;
}
