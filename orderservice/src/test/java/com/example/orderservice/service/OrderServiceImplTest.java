//package com.example.orderservice.service;
//import com.example.orderservice.entity.Order;
//import com.example.orderservice.exception.ProductNotFoundInDatabase;
//import com.example.orderservice.model.OrderRequestDto;
//import com.example.orderservice.model.OrderResponseDto;
//import com.example.orderservice.model.PaymentMode;
//import com.example.orderservice.repository.OrderRepository;
//import org.junit.jupiter.api.*;
//import static org.junit.jupiter.api.Assertions.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import static org.mockito.Mockito.*;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.util.Optional;
//@SpringBootTest
//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//class OrderServiceImplTest {
//
//    @Mock
//    private OrderRepository orderRepository;
//    @InjectMocks
//    private OrderServiceImpl orderServiceImpl;
//
//
//    @BeforeEach
//    @Test
//    public void beforeEachSetUp() {
//        OrderRequestDto orderRequestDto = getMockOrderRequestDto();
//        Order order = getMockOrder();
//    }
//
//    @Test
//    public void placingOrderSuccessFully() {
//
//        Order order = getMockOrder();
//
//
//        Order save = orderRepository.save(order);
//        OrderResponseDto orderResponseDto = getMockOrderResponseDto();
//
//        //mock
//        when(orderRepository.save(order)).thenReturn(save);
//        //verify
//        verify(orderRepository, times(1)).save(order);
//        //assertion check
//        assertEquals(order.getId(), orderResponseDto.getId());
//    }
//
//    @Test
//    public void findOrderByIDSuccess() {
//        Order order = getMockOrder();
//        //mock
//        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
//
//        //call for invocation
//        OrderResponseDto orderResponseDto = orderServiceImpl.findById(anyLong());
//
//        //verifying
//        verify(orderRepository, times(1)).findById(anyLong());
//
//        //assert checking
//        assertEquals(order.getId(),orderResponseDto.getId() );
//        assertNotNull(orderResponseDto);
//    }
//
//    @Test
//    public void findOrderByIDFailureScenario() {
//        Order order = getMockOrder();
//
//        //mock
//        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
//
//        //assert checking
//        ProductNotFoundInDatabase ex =
//                assertThrows(ProductNotFoundInDatabase.class,()-> orderServiceImpl.findById(1l));
//        //call for invocation
//
//        //just watching
//        System.out.println(ex.getMessage());
//
//        //verifying
//        verify(orderRepository, times(1)).findById(anyLong());
//
//        assertEquals(404,ex.getStatusCode());
//    }
//
//    @Test
//
//
//    public OrderRequestDto getMockOrderRequestDto() {
//        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
//                .quantity("2")
//                .amount(200.22)
//                .productName("TV")
//                .paymentMode(PaymentMode.CASH)
//                .id(1l).build();
//
//        return orderRequestDto;
//    }
//
//    public Order getMockOrder() {
//
//
//        Order order = Order.builder()
//                .quantity(getMockOrderRequestDto().getQuantity())
//                .productName(getMockOrderRequestDto().getProductName())
//                .id(getMockOrderRequestDto().getId())
//                .build();
//        return order;
//    }
//
//    public OrderResponseDto getMockOrderResponseDto() {
//        Order order = getMockOrder();
//
//        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
//                .quantity(order.getQuantity())
//                .productName(order.getProductName())
//                .id(order.getId())
//                .build();
//        return orderResponseDto;
//
//    }
//
//}