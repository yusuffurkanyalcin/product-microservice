package com.furkan.product_service.service;

import com.furkan.product_service.entity.Product;
import com.furkan.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class GETServiceTests {

    @Autowired
    private ProductServiceImpl productService;

    @MockitoBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void testGetAll() {

        Product product1 = new Product("1","PC", "Monster", BigDecimal.valueOf(199.99));
        Product product2 = new Product("2","Mouse", "MSI", BigDecimal.valueOf(299.99));
        List<Product> mockProducts = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> products = productService.getAll();
        assertThat(products).isEqualTo(mockProducts);
    }
}
