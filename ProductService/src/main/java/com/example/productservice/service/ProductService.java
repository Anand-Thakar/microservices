package com.example.productservice.service;

import com.example.productservice.model.ProductRequestDto;
import com.example.productservice.model.ProductResponseDto;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

   void reduceQuantity(Long productId,  Long wantedQuantity);

    ProductResponseDto getProductById(long productId);

}
