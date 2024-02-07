package com.programmingtechie.orderservice.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class OrderLineItemsDto {

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
