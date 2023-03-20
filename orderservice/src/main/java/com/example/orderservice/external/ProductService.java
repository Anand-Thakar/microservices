package com.example.orderservice.external;

import com.example.orderservice.external.decoder.CustomErrorDecoder;
import com.example.orderservice.external.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "PRODUCT-SERVICE", url = "localhost:8081/product",fallback = CustomErrorDecoder.class)
public interface ProductService {
    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long productId, @RequestParam Long wantedQuantity);

    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") long productId);
}
