package com.revature.ms.inventory.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.ms.inventory.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
}