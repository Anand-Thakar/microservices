package com.example.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNotFoundInDatabase.class)
    public ResponseEntity<ErrorDetails> handleProductNotFoundInDatabase(ProductNotFoundInDatabase ex) {

       ErrorDetails errorDetails = ErrorDetails.builder()
                .statusCode(ex.getStatusCode())
                .message(ex.getMessage())
                .build();
       return  new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(ex.getStatusCode()));

    }
    @ExceptionHandler(MyRestTemplateException.class)
    public ResponseEntity<ErrorDetails> handleMyRestTemplateException(MyRestTemplateException ex) {

        ErrorDetails errorDetails = ErrorDetails.builder()
                .statusCode(ex.getStatusCode())
                .message(ex.getMessage())
                .build();
        return  new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDetails> handleCustomException(CustomException ex) {

        ErrorDetails errorDetails = ErrorDetails.builder()
                .statusCode(ex.getStatusCode())
                .message(ex.getMessage())
                .build();
        return  new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleOtherGenericException(Exception ex) {

        ErrorDetails errorDetails = ErrorDetails.builder()
                .statusCode(500)
                .message("Internal Server Error")
                .build();

        return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
