package com.example.orderservice.external.decoder;
import com.example.orderservice.exception.CustomException;
import com.example.orderservice.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        ErrorDetails message = null;

        try (InputStream bodyIs = response.body()
                .asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, ErrorDetails.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (response.status()) {
            case 404:
                return new CustomException(message.getMessage() != null ? message.getMessage() : "Not found", 404);
            case 500:
                return new CustomException(message.getMessage() != null ? message.getMessage() : "Server Error", 500);
            default:
                return errorDecoder.decode(s, response);
        }
    }
}
