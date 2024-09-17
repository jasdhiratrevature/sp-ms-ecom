package com.revature.ms.inventory.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.revature.ms.inventory.dto.InventoryResponse;
import com.revature.ms.inventory.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    // http://localhost:8083/api/inventory/iphone-13,iphone13-red

    // http://localhost:8083/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }
}
