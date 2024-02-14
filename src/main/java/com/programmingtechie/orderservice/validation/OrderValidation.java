package com.programmingtechie.orderservice.validation;

import com.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.enums.OrderEnum;
import com.programmingtechie.orderservice.exception.OrderException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderRequest orderRequest = (OrderRequest) target;
        List<OrderLineItemsDto> orderList = orderRequest.getOrderList();

        for(OrderLineItemsDto order : orderList) {
            BigDecimal price = order.getPrice();
            Integer quantity = order.getQuantity();

            if(price.equals(BigDecimal.ZERO) && quantity > 1) {
                throw new OrderException(OrderEnum.FREE_ITEMS_QUANTITY_LIMIT.getMessage(),
                        OrderEnum.FREE_ITEMS_QUANTITY_LIMIT.getErrorCode()
                );
            }
        }
    }
}
