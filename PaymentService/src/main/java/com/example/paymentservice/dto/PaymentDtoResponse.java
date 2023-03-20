package com.example.paymentservice.dto;

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
    private long paymentAmount;
    private Instant paymentDate;
    private String referenceNumber;
}
