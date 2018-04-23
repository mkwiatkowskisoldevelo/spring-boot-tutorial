package com.sda.springbootdemo.exercises.dto;

import com.sda.springbootdemo.exercises.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends BaseProductDto {

    private BaseReceiptDto receipt;

    public ProductDto(Product product) {
        super(product);
        this.receipt = null == product.getReceipt()
                ? null
                : new BaseReceiptDto(product.getReceipt());
    }
}
