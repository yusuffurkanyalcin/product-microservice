package com.furkan.product_service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furkan.product_service.dto.CreateProductDto;
import com.furkan.product_service.entity.Product;
import com.furkan.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	public void setUp() {
		productRepository.deleteAll();
	}

	@Test
	public void testCreateProductEndpoint() throws Exception {
		CreateProductDto dto = new CreateProductDto("PC", "MSI", BigDecimal.valueOf(199.99));

		mockMvc.perform(post("/v1/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(objectMapper.writeValueAsString(
						productRepository.findAll().get(0)
				)));
	}

	@Test
	public void testGetAllProductsEndpoint() throws Exception {
		Product product1 = new Product();
		product1.setName("PC");
		product1.setDescription("MSI");
		product1.setPrice(BigDecimal.valueOf(199.99));

		Product product2 = new Product();
		product2.setName("Laptop");
		product2.setDescription("Dell");
		product2.setPrice(BigDecimal.valueOf(299.99));

		productRepository.saveAll(List.of(product1, product2));

		mockMvc.perform(get("/v1/products"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(objectMapper.writeValueAsString(
						productRepository.findAll()
				)));
	}

}
