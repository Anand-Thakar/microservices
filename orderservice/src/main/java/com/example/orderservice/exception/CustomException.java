package com.example.orderservice.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomException extends RuntimeException  {
    private int statusCode;
    public CustomException(String message) {
        super(message);
    }
    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
