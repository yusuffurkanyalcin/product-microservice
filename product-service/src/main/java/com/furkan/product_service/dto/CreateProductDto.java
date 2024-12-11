package com.furkan.product_service.dto;

import java.math.BigDecimal;

public record CreateProductDto(
         String name,
         String description,
         BigDecimal price
) {
}
