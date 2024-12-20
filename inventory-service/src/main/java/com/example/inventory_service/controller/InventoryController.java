package com.example.inventory_service.controller;

import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.service.InventoryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/inventory")
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    // https://localhost:80802/v1/inventory?skuCode=asus-1-laptop
    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
