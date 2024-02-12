package com.programmingtechie.orderservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class OrderLineItemsDto {

    @NotBlank
    private String skuCode;
    @PositiveOrZero
    private BigDecimal price;
    @Positive
    private Integer quantity;
}
