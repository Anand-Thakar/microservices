//package com.example.orderservice.controller;
//
//import com.example.orderservice.OrderServiceConfig;
//import com.example.orderservice.repository.OrderRepository;
//import com.example.orderservice.service.OrderService;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.RegisterExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.util.StreamUtils;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.util.StreamUtils.*;
//
//@SpringBootTest({"server.port=0"})
//@EnableConfigurationProperties
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = {OrderServiceConfig.class})
//class OrderControllerTest {
//
//
//    //order service, order repo
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @RegisterExtension
//    static WireMockExtension wireMockServer = WireMockExtension.newInstance().options(WireMockConfiguration.wireMockConfig().port(8080)).build();
//
//    private ObjectMapper objectMapper = new ObjectMapper()
//            .findAndRegisterModules()
//            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//    @BeforeEach
//    void setUp() throws IOException {
//
//        getOrderDetailsResponse();
//
//    }
//
//    private void getOrderDetailsResponse() throws IOException {
//
//        wireMockServer.stubFor((get("/order/1")
//                .willReturn(aResponse().withStatus(HttpStatus.OK.value())
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody(copyToString(OrderControllerTest.class.getClassLoader()
//                                .getResourceAsStream("mock/GetOrder.json"), Charset.defaultCharset())))));
//    }
//
//
//    public void test_WhenPlaceOrder_DoPayment_Success() throws Exception {
//        // first place order - mock order done
//
//
//
//      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/order/1")
//              .content(MediaType.APPLICATION_JSON_VALUE))
//              .andExpect(MockMvcResultMatchers.status().isOk())
//              .andReturn();
//
//        String orderID = mvcResult.
//                getResponse().getContentAsString();
//
//
//
//        // get order by order ID from DB and check
//        // output
//    }
//
//}