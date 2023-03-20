package com.example.orderservice.service;

import com.example.orderservice.model.OrderRequestDto;
import com.example.orderservice.model.OrderResponseDto;

public interface OrderService {

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto findById(Long id);
}
