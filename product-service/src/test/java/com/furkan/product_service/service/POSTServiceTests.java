package com.furkan.product_service.service;

import com.furkan.product_service.dto.CreateProductDto;
import com.furkan.product_service.entity.Product;
import com.furkan.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class POSTServiceTests {

    @Autowired
    private ProductServiceImpl productService;

    @MockitoSpyBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void testCreateProduct() {

        CreateProductDto dto = new CreateProductDto("PC","MSI", BigDecimal.valueOf(199.99));

        Product product = new Product();
        product.setId("123456");
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());

        when(productRepository.save(product)).thenReturn(product);

        Optional<Product> createdProduct = productService.createProduct(dto);

        assertThat(createdProduct).isPresent();
        assertThat(createdProduct.get().getName()).isEqualTo(dto.name());
        assertThat(createdProduct.get().getDescription()).isEqualTo(dto.description());
        assertThat(createdProduct.get().getPrice()).isEqualTo(dto.price());
    }
}
