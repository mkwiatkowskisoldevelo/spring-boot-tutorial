package com.sda.springbootdemo.exercises.dto;

import com.sda.springbootdemo.exercises.model.Receipt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDto extends BaseReceiptDto {

    private List<BaseProductDto> products;

    public ReceiptDto(Receipt receipt) {
        super(receipt);
        this.products = receipt.getProducts()
                .stream()
                .map(BaseProductDto::new)
                .collect(Collectors.toList());
    }
}
