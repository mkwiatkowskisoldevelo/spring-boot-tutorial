package com.sda.springbootdemo.exercises.dto;

import com.sda.springbootdemo.exercises.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BaseProductDto extends BaseDto {

    protected String name;
    protected Double price;

    public BaseProductDto(Product product) {
        this(product.getId(), product.getName(), product.getPrice());
    }

    public BaseProductDto(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
