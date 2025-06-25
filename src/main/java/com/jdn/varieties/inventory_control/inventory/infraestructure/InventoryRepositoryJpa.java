package com.jdn.varieties.inventory_control.inventory.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepositoryJpa extends JpaRepository<InventoryEntity, Long> {
}
