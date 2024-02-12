package com.programmingtechie.orderservice.controller;

import java.util.concurrent.CompletableFuture;

import com.programmingtechie.orderservice.enums.OrderEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.dto.OrderResponse;
import com.programmingtechie.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    /**
     * This method asynchronously places an order using CompletableFuture.
     * @param orderRequest
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest orderRequest){
        
        /**
         * CompletableFuture.supplyAsync() is used to perform the order placement asynchronously.
         * The lambda expression inside supplyAsync() represents the task that will be executed asynchronously.
         */
        return CompletableFuture.supplyAsync(() -> 
            orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<OrderResponse> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){

    	OrderResponse response = new OrderResponse();
    	response.setMessageCode(OrderEnum.SOMETHING_WENT_WRONG.getErrorCode());
    	response.setMessage(OrderEnum.SOMETHING_WENT_WRONG.getMessage());

    	return CompletableFuture.supplyAsync(() -> response);
    }
}
