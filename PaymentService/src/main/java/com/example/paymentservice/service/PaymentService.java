package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDtoRequest;
import com.example.paymentservice.dto.PaymentDtoResponse;

public interface PaymentService {
    PaymentDtoResponse getPaymentDetailsByOrderId(Long id);

    PaymentDtoResponse doPayment(PaymentDtoRequest paymentDtoRequest);
}
