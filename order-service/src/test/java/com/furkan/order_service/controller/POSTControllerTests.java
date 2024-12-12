package com.furkan.order_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furkan.order_service.dto.OrderLineItemDto;
import com.furkan.order_service.dto.OrderRequest;
import com.furkan.order_service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class POSTControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    public void testCreateUserEndpoint() throws Exception {
        ArrayList<OrderLineItemDto> orderLineItemDtoList = new ArrayList<>();

        OrderLineItemDto item1 = new OrderLineItemDto();
        item1.setSkuCode("1111111");
        item1.setPrice(BigDecimal.valueOf(111.11));
        item1.setQuantity(11);

        OrderLineItemDto item2 = new OrderLineItemDto();
        item2.setSkuCode("2222222");
        item2.setPrice(BigDecimal.valueOf(222.22));
        item2.setQuantity(22);

        orderLineItemDtoList.add(item1);
        orderLineItemDtoList.add(item2);

        OrderRequest dto = new OrderRequest(orderLineItemDtoList);

        mockMvc.perform(post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}
