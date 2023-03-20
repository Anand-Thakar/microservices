package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.CustomException;
import com.example.orderservice.exception.ProductNotFoundInDatabase;
import com.example.orderservice.external.PaymentService;
import com.example.orderservice.external.ProductService;
import com.example.orderservice.external.decoder.RestTemplateResponseErrorHandler;
import com.example.orderservice.external.dto.PaymentDtoRequest;
import com.example.orderservice.external.dto.PaymentDtoResponse;
import com.example.orderservice.external.dto.ProductResponseDto;
import com.example.orderservice.model.OrderRequestDto;
import com.example.orderservice.model.OrderResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;
    private ProductService productService;
    private PaymentService paymentService;

    public OrderServiceImpl(OrderRepository orderRepository, RestTemplateBuilder restTemplateBuilder, ProductService productService, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.paymentService = paymentService;
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    @Transactional
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        log.info("order come checking quantity, rest template call for reducing quantity");

        //step 1
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        //step 2
        Map<String, Long> params = new HashMap<>();
        params.put("wantedQuantity", Long.valueOf(orderRequestDto.getQuantity()));

        //step 3
        HttpEntity entity = new HttpEntity(headers);

        String url = "http://localhost:8081/product/reduceQuantity/" + orderRequestDto.getProductId();
        String queryParam = orderRequestDto.getQuantity();
        URI uri = UriComponentsBuilder
                .fromUri(URI.create(url))
                .queryParam("wantedQuantity", queryParam)
                .build()
                .toUri();

            restTemplate.exchange(uri, HttpMethod.PUT, null, Void.class);



//      http://localhost:8081/product/reduceQuantity/1?wantedQuantity=1

//        productService.reduceQuantity(orderRequestDto.getProductId(), Long.valueOf(orderRequestDto.getQuantity()));
        log.info("passed quantity check saving order inside database");

        Order order = getOrder(orderRequestDto);
        Order save = orderRepository.save(order);


        //creating payment request so call do payment
        PaymentDtoRequest paymentDtoRequest = getPaymentDtoRequest(save);

        //creating response for return
        OrderResponseDto orderResponseDto = getResponseDto(save);

        //Feign call for product response to attach with order response
        ResponseEntity<ProductResponseDto> productById = productService.getProductById(save.getProductId());
        ProductResponseDto body = productById.getBody();
        orderResponseDto.setProductDetails(body);

/*        ResponseEntity<PaymentDtoResponse> paymentDtoResponseResponseEntity = paymentService.doPayment(paymentDtoRequest);
        PaymentDtoResponse body1 = paymentDtoResponseResponseEntity.getBody();*/
        log.info("product details attached");

        PaymentDtoResponse body1 = restTemplate.postForObject("http://localhost:8082/payment", paymentDtoRequest, PaymentDtoResponse.class);
        orderResponseDto.setPaymentDetails(body1);

        log.info("payment added successfully, changing order status accepted");
        order.setOrderStatus("ACCEPTED");

        //updating order and response
        orderRepository.save(order);
        orderResponseDto.setOrderStatus(order.getOrderStatus());

        return orderResponseDto;
    }

    @Override
    @Transactional
    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundInDatabase("We couldn't find product with ID: " + id + " inside the database", 404));
        OrderResponseDto orderResponseDto = getOrderResponseDto(order);

        PaymentDtoRequest paymentDtoRequest = getPaymentDtoRequest(order);

        PaymentDtoResponse paymentDtoResponse = restTemplate.postForObject("http://localhost:8082/payment", paymentDtoRequest, PaymentDtoResponse.class);
        orderResponseDto.setPaymentDetails(paymentDtoResponse);

        ResponseEntity<ProductResponseDto> productById = productService.getProductById(order.getProductId());
        ProductResponseDto body = productById.getBody();
        orderResponseDto.setProductDetails(body);

        order.setOrderStatus("ACCEPTED");
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderRepository.save(order);

        return orderResponseDto;
    }

    private static PaymentDtoRequest getPaymentDtoRequest(Order save) {
        PaymentDtoRequest paymentDtoRequest = PaymentDtoRequest.builder()
                .orderId(save.getOrderId())
                .amount(save.getAmount().longValue())
                .paymentMode(save.getPaymentMode())
                .build();
        return paymentDtoRequest;
    }

    private static Order getOrder(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .productId(orderRequestDto.getProductId())
                .quantity(orderRequestDto.getQuantity())
                .amount(orderRequestDto.getAmount())
                .orderDate(Instant.now())
                .paymentMode(orderRequestDto.getPaymentMode())
                .build();
        return order;
    }

    private static OrderResponseDto getResponseDto(Order save) {
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderID(save.getOrderId())
                .productId(save.getProductId())
                .quantity(save.getQuantity())
                .amount(save.getAmount())
                .orderDate(save.getOrderDate())
                .paymentMode(save.getPaymentMode())
                .build();
        return orderResponseDto;
    }

    private static OrderResponseDto getOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productId(order.getProductId())
                .orderID(order.getOrderId())
                .quantity(order.getQuantity())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .paymentMode(order.getPaymentMode())
                .build();
        return orderResponseDto;
    }

}
