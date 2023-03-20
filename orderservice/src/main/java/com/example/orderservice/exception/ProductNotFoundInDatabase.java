package com.example.orderservice.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProductNotFoundInDatabase extends RuntimeException {
    private int statusCode;
    public ProductNotFoundInDatabase(String message) {
        super(message);
    }
    public ProductNotFoundInDatabase(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
