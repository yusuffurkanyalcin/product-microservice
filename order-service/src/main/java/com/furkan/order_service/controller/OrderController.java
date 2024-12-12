package com.furkan.order_service.controller;

import com.furkan.order_service.dto.OrderRequest;
import com.furkan.order_service.service.OrderServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderServiceImpl.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}
