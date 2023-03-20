package com.example.orderservice.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MyRestTemplateException extends RuntimeException{

    private int statusCode;
    public MyRestTemplateException(String message) {
        super(message);
    }
    public MyRestTemplateException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
