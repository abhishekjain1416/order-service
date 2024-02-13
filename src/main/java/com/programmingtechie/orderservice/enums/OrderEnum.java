package com.programmingtechie.orderservice.enums;

import com.programmingtechie.orderservice.errors.OrderState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderEnum {

    ORDER_PLACED_SUCCESSFULLY(OrderState.ORDER_PLACED_SUCCESS_MESSAGE, OrderState.ORDER_PLACED_SUCCESS_CODE),
    PRODUCTS_OUT_OF_STOCK(OrderState.PRODUCTS_OUT_OF_STOCK_MESSAGE, OrderState.PRODUCTS_OUT_OF_STOCK_CODE),
    INVALID_INPUT(OrderState.INVALID_ARGUMENTS_MESSAGE, OrderState.INVALID_ARGUMENTS_CODE),
    SOMETHING_WENT_WRONG(OrderState.SOMETHING_WENT_WRONG_MESSAGE, OrderState.SOMETHING_WENT_WRONG_ERROR_CODE);

    private final String message;
    private final String code;

    public String getSuccessCode() {
        return "S16" + code;
    }

    public String getErrorCode() {
        return "E16" + code;
    }
}
