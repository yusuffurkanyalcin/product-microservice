package com.example.inventory_service.service;

import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.entity.Inventory;
import com.example.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        List<Inventory> inventory = inventoryRepository.findBySkuCodeIn(skuCode);

        return inventory.stream()
                .map(inventory1 -> new InventoryResponse(inventory1.getSkuCode(), inventory1.getQuantity() > 0))
                .toList();
    }
}
