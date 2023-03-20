package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDtoRequest;
import com.example.paymentservice.dto.PaymentDtoResponse;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentDtoResponse> getPaymentDetailsByOrderId(@PathVariable("orderId") Long orderId) {
        PaymentDtoResponse paymentDetailsByOrderId = paymentService.getPaymentDetailsByOrderId(orderId);
        return new ResponseEntity<>(paymentDetailsByOrderId, HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<PaymentDtoResponse> doPayment(@RequestBody PaymentDtoRequest paymentDtoRequest){
        PaymentDtoResponse paymentDtoResponse = paymentService.doPayment(paymentDtoRequest);
        return new ResponseEntity<>(paymentDtoResponse,HttpStatusCode.valueOf(200));
    }

}
