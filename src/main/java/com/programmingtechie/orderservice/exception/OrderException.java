package com.programmingtechie.orderservice.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    private static final Long serialVersionUID = 1L;
    private final String errorCode;
    private final String message;

    public OrderException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
