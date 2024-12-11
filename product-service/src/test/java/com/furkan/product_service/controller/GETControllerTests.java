package com.furkan.product_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class GETControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllEndpoint() throws Exception {
        mockMvc.perform(get("/v1/products"))
                .andExpect(status().isOk());
    }
}
