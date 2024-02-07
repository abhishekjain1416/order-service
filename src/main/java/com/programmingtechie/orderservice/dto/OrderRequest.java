package com.programmingtechie.orderservice.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
