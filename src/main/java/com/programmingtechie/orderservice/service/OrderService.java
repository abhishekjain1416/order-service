package com.programmingtechie.orderservice.service;

import org.springframework.stereotype.Service;

import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.dto.OrderResponse;

@Service
public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);
}
