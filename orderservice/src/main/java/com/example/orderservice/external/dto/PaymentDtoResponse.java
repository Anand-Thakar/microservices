package com.example.orderservice.external.dto;

import com.example.orderservice.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDtoResponse {
    private long paymentId;
    private Instant paymentDate;
    private String referenceNumber;

}
