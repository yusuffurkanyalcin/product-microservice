package com.furkan.order_service.service;

import com.furkan.order_service.dto.OrderLineItemDto;
import com.furkan.order_service.dto.OrderRequest;
import com.furkan.order_service.entity.Order;
import com.furkan.order_service.entity.OrderLineItem;
import com.furkan.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.orderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItem::getSkuCode)
                .toList();

        return Optional.of(orderRepository.save(order));

    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }
}
