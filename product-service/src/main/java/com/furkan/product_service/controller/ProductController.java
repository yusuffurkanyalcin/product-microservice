package com.furkan.product_service.controller;

import com.furkan.product_service.dto.CreateProductDto;
import com.furkan.product_service.entity.Product;
import com.furkan.product_service.service.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService1) {
        productService = productService1;
    }

    @PostMapping
    public ResponseEntity<Optional<Product>> createProduct(@RequestBody CreateProductDto dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
}

