package com.example.paymentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDetailsNotFound extends RuntimeException {
    private int statusCode;
    public PaymentDetailsNotFound(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
