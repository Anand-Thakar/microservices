package com.example.productservice.exception;

import lombok.Data;

@Data
public class InsufficientQuantityInStock extends RuntimeException {

    private  int errorCode;
    public InsufficientQuantityInStock(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
