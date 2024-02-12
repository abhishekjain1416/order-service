package com.programmingtechie.orderservice.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class OrderRequest {
    @NotEmpty @Valid
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
