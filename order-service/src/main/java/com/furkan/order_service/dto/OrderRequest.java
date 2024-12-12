package com.furkan.order_service.dto;

import java.util.List;

public record OrderRequest(
        List<OrderLineItemDto> orderLineItemDtoList
) {
}
