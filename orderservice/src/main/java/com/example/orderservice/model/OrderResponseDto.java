package com.example.orderservice.model;

import com.example.orderservice.external.dto.PaymentDtoResponse;
import com.example.orderservice.external.dto.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderID;
    private Long productId;
    private String quantity;
    private Double amount;
    private Instant orderDate;
    private String orderStatus;
    private PaymentMode paymentMode;
    private PaymentDtoResponse paymentDetails;
    private ProductResponseDto productDetails;
}


