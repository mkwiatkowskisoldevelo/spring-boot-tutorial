package com.sda.springbootdemo.exercises.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(min = 3, max = 8)
    @Pattern.List({
        @Pattern(regexp = "(?=.*[0-9]).+", message = "must contain at least 1 number"),
        @Pattern(regexp = "(?=\\S+$).+", message = "must not contain spaces")
    })
    @Column(nullable = false, name = "buyer")
    private String buyer;

    @Column(nullable = false, name = "date")
    private LocalDateTime date;

    @OneToMany(mappedBy = "receipt")
    private List<Product> products;
}
