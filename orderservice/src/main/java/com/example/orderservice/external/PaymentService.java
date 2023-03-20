package com.example.orderservice.external;

import com.example.orderservice.external.decoder.CustomErrorDecoder;
import com.example.orderservice.external.dto.PaymentDtoRequest;
import com.example.orderservice.external.dto.PaymentDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE", url = "localhost:8082",fallback = CustomErrorDecoder.class)
public interface PaymentService {
     @PostMapping("/payment")
     ResponseEntity<PaymentDtoResponse> doPayment(@RequestBody PaymentDtoRequest paymentDtoRequest);
}
