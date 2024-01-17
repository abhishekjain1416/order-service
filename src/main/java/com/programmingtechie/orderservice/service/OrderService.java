package com.programmingtechie.orderservice.service;

import org.springframework.stereotype.Service;

import com.programmingtechie.orderservice.dto.OrderRequest;

@Service
public interface OrderService {

    String placeOrder(OrderRequest orderRequest);
}
