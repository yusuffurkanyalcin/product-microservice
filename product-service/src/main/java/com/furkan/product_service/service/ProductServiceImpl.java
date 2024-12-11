package com.furkan.product_service.service;

import com.furkan.product_service.dto.CreateProductDto;
import com.furkan.product_service.entity.Product;
import com.furkan.product_service.mapper.ProductMapper;
import com.furkan.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository repository) {
        productRepository = repository;
    }

    public Optional<Product> createProduct(CreateProductDto dto) {
        Product product = new Product();
        ProductMapper.convertToProduct(dto, product);
        Product p = productRepository.save(product);
        return Optional.of(p);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
