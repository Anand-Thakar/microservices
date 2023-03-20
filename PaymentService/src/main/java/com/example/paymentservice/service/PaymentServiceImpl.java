package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDtoRequest;
import com.example.paymentservice.dto.PaymentDtoResponse;
import com.example.paymentservice.entity.PaymentDetails;
import com.example.paymentservice.exception.PaymentDetailsNotFound;
import com.example.paymentservice.repo.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Service

public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDtoResponse getPaymentDetailsByOrderId(Long id) {

     com.example.paymentservice.entity.PaymentDetails paymentDetails = paymentRepository.findByOrderId(id).orElseThrow
                (() -> new PaymentDetailsNotFound("payment details for orderID " + id + "not found in database", 404));

        PaymentDtoResponse response = PaymentDtoResponse.builder()
                .paymentAmount(paymentDetails.getPaymentAmount())
                .paymentDate(paymentDetails.getPaymentDate())
                .referenceNumber(paymentDetails.getReferenceNumber())
                .paymentId(paymentDetails.getPaymentId())
                .build();

        return response;

    }

    @Override
    public PaymentDtoResponse doPayment(PaymentDtoRequest paymentDtoRequest) {
        //payments details
       PaymentDetails paymentDetails = PaymentDetails.builder()
                .paymentAmount(paymentDtoRequest.getAmount())
                .paymentDate(Instant.now())
                .referenceNumber(String.valueOf(UUID.randomUUID()))
                .build();

        //saving payment
        PaymentDetails save = paymentRepository.save(paymentDetails);

        //returning back response
        PaymentDtoResponse payment = PaymentDtoResponse.builder()
                .paymentAmount(paymentDetails.getPaymentAmount())
                .paymentDate(paymentDetails.getPaymentDate())
                .referenceNumber(paymentDetails.getReferenceNumber())
                .paymentId(paymentDetails.getPaymentId())
                .build();
        return payment;
    }
}
