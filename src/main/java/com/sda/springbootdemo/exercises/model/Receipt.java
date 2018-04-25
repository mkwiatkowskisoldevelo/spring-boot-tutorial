package com.sda.springbootdemo.exercises.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="receipts")
public class Receipt extends BaseEntity {

    @Column(nullable = false, name = "buyer")
    private String buyer;

    @Column(nullable = false, name = "date")
    private LocalDateTime date;

    @OneToMany(mappedBy = "receipt")
    private List<Product> products;
}
