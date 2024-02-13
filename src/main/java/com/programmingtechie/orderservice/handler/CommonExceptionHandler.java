package com.programmingtechie.orderservice.handler;

import com.programmingtechie.orderservice.dto.ErrorListResponse;
import com.programmingtechie.orderservice.dto.ErrorResponse;
import com.programmingtechie.orderservice.enums.OrderEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorListResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorListResponse response = new ErrorListResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        List<ErrorResponse> errorsList = new ArrayList<>();

        String firstKey = errors.keySet().iterator().next();
        String firstValue = errors.values().iterator().next();
        errorResponse.setErrorMessage(firstKey + " " + firstValue);
        errorsList.add(errorResponse);

        response.setMessageCode(OrderEnum.INVALID_INPUT.getErrorCode());
        response.setMessage(OrderEnum.INVALID_INPUT.getMessage());
        response.setError(errorsList);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
