package com.example.productservice.exception;

import lombok.Data;

@Data
public class ProductNotFoundInDatabase extends RuntimeException {
    private int statusCode;
    public ProductNotFoundInDatabase(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
