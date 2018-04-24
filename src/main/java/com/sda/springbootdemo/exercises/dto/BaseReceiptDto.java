package com.sda.springbootdemo.exercises.dto;

import com.sda.springbootdemo.exercises.model.Receipt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BaseReceiptDto extends BaseDto {

    protected String buyersName;
    protected LocalDateTime date;

    public BaseReceiptDto(Receipt receipt) {
        this(receipt.getId(), receipt.getBuyer(), receipt.getDate());
    }

    public BaseReceiptDto(Long id, String buyersName, LocalDateTime date) {
        this.id = id;
        this.buyersName = buyersName;
        this.date = date;
    }
}
