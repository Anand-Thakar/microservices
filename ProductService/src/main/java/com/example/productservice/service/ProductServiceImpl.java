package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.InsufficientQuantityInStock;
import com.example.productservice.exception.ProductNotFoundInDatabase;
import com.example.productservice.model.ProductRequestDto;
import com.example.productservice.model.ProductResponseDto;
import com.example.productservice.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product product = Product.builder()
                .price(productRequestDto.getPrice())
                .productName(productRequestDto.getProductName())
                .quantityInStock(productRequestDto.getQuantityInStock()).build();

        Product save = productRepository.save(product);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .productId(save.getProductID())
                .productName(save.getProductName())
                .remainingQuantity(save.getQuantityInStock())
                .price(save.getPrice().longValue()).build();
        return productResponseDto;
    }

    @Override
    public void reduceQuantity(Long productId, Long wantedQuantity) {

        log.info("INSIDE PRODUCT SERVICE");
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundInDatabase("product with " + productId + " not found in database to place order", 404));

        if (product.getQuantityInStock() > wantedQuantity) {
            product.setQuantityInStock(product.getQuantityInStock() - wantedQuantity.intValue());
            productRepository.save(product);
        } else {
            throw new InsufficientQuantityInStock("We don't have sufficient stock Wanted Quantity: " + wantedQuantity + " Stock Available: " + product.getQuantityInStock(), 404);
        }
    }

    @Override
    public ProductResponseDto getProductById(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundInDatabase
                ("product with " + productId + " not found in database to place order", 404));

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .productId(product.getProductID())
                .productName(product.getProductName())
                .remainingQuantity(product.getQuantityInStock())
                .price(product.getPrice().longValue()).build();
        return productResponseDto;
    }
}