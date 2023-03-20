package com.example.orderservice.controller;
import com.example.orderservice.model.OrderRequestDto;
import com.example.orderservice.model.OrderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.orderservice.service.OrderService;

@RequestMapping("/order")
@RestController
public class  OrderController {
    private OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.valueOf(200));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<OrderResponseDto> getOrderById(@PathVariable("id") Long id){
        OrderResponseDto byId = orderService.findById(id);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }
}