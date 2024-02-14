package com.programmingtechie.orderservice.handler;

import com.programmingtechie.orderservice.dto.ErrorListResponse;
import com.programmingtechie.orderservice.dto.ErrorResponse;
import com.programmingtechie.orderservice.exception.OrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorListResponse> orderException(OrderException ex) {

        ErrorListResponse response = new ErrorListResponse();
        List<ErrorResponse> lErrors = new ArrayList<>();
        ErrorResponse error = new ErrorResponse();

        error.setErrorMessage(ex.getMessage());
        lErrors.add(error);
        response.setMessage(ex.getMessage());
        response.setMessageCode(ex.getErrorCode());
        response.setError(lErrors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
