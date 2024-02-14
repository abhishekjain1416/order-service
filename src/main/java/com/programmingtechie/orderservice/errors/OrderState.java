package com.programmingtechie.orderservice.errors;

public class OrderState {

    public static final String ORDER_PLACED_SUCCESS_MESSAGE = "Order Placed Successfully.";
    public static final String SOMETHING_WENT_WRONG_MESSAGE = "Oops! Something went wrong, please order after some time!";
    public static final String PRODUCTS_OUT_OF_STOCK_MESSAGE = "Products are not in stock, please try again later.";
    public static final String INVALID_ARGUMENTS_MESSAGE = "Invalid data.";
    public static final String FREE_ITEMS_QUANTITY_LIMIT_MESSAGE = "You can't select more than 1 item if price is 0.";

    public static final String ORDER_PLACED_SUCCESS_CODE = "2001";
    public static final String SOMETHING_WENT_WRONG_ERROR_CODE = "4000";
    public static final String PRODUCTS_OUT_OF_STOCK_CODE = "4001";
    public static final String INVALID_ARGUMENTS_CODE = "4002";
    public static final String FREE_ITEMS_QUANTITY_LIMIT_CODE = "4003";
}
