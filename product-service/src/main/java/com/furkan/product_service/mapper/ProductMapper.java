package com.furkan.product_service.mapper;

import com.furkan.product_service.dto.CreateProductDto;
import com.furkan.product_service.entity.Product;

public final class ProductMapper {

    public static void convertToProduct(CreateProductDto dto, Product product) {
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
    }
}
