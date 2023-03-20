package com.example.orderservice.external.decoder;

import com.example.orderservice.exception.ErrorDetails;
import com.example.orderservice.exception.MyRestTemplateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError()) {

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getBody()))) {
                String httpBodyResponse = reader.lines()
                        .collect(Collectors.joining(""));

                ObjectMapper mapper = new ObjectMapper();

                ErrorDetails restTemplateError = mapper
                        .readValue(httpBodyResponse,
                                ErrorDetails.class);

                throw new MyRestTemplateException(restTemplateError.getMessage(), restTemplateError.getStatusCode());
            }
        }
    }
}
