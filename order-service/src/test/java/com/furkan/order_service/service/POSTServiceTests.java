package com.furkan.order_service.service;

import com.furkan.order_service.dto.OrderLineItemDto;
import com.furkan.order_service.dto.OrderRequest;
import com.furkan.order_service.entity.Order;
import com.furkan.order_service.entity.OrderLineItem;
import com.furkan.order_service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class POSTServiceTests {

    @Autowired
    private OrderServiceImpl orderService;

    @MockitoSpyBean
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    public void testCreateProduct() {
        final String orderNumber = UUID.randomUUID().toString();

        final String skuCode1 = "IPhone 11";
        final String skuCode2 = "IPhone 12";

        final BigDecimal price1 = BigDecimal.valueOf(111.11);
        final BigDecimal price2 = BigDecimal.valueOf(222.22);

        final Integer quantiy1 = 11;
        final Integer quantiy2 = 22;

        ArrayList<OrderLineItem> orderLineItemList = new ArrayList<>();
        ArrayList<OrderLineItemDto> orderLineItemDtoList = new ArrayList<>();

        OrderLineItem item1 = new OrderLineItem();
        item1.setSkuCode(skuCode1);
        item1.setPrice(price1);
        item1.setQuantity(quantiy1);

        OrderLineItem item2 = new OrderLineItem();
        item2.setSkuCode(skuCode2);
        item2.setPrice(price2);
        item2.setQuantity(quantiy2);

        orderLineItemList.add(item1);
        orderLineItemList.add(item2);

        OrderLineItemDto itemDto1 = new OrderLineItemDto();
        item1.setSkuCode(skuCode1);
        item1.setPrice(price1);
        item1.setQuantity(quantiy1);

        OrderLineItemDto itemDto2 = new OrderLineItemDto();
        item2.setSkuCode(skuCode2);
        item2.setPrice(price2);
        item2.setQuantity(quantiy2);

        orderLineItemDtoList.add(itemDto1);
        orderLineItemDtoList.add(itemDto2);

        OrderRequest dto = new OrderRequest(orderLineItemDtoList);

        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setOrderLineItems(orderLineItemList);

        when(orderRepository.save(order)).thenReturn(order);

        Optional<Order> createdOrder = orderService.placeOrder(dto);

        assertThat(createdOrder).isPresent();
        assertThat(createdOrder.get().getOrderNumber().equals(orderNumber));
        assertThat(createdOrder.get().getOrderLineItems().equals(orderLineItemList));
        assertThat(createdOrder.get());
    }
}
