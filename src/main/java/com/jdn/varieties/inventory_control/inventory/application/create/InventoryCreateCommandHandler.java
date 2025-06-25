package com.jdn.varieties.inventory_control.inventory.application.create;

import com.jdn.varieties.inventory_control.inventory.application.InventoryMapper;
import com.jdn.varieties.inventory_control.inventory.application.dto.InventoryRequestDto;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryDomain;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class InventoryCreateCommandHandler {

    private final InventoryRepository inventoryRepository;

    public InventoryCreateCommandHandler(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void create(InventoryRequestDto inventoryRequestDto) {
        final InventoryDomain inventoryDomain = InventoryMapper.toDomain(inventoryRequestDto);
        this.inventoryRepository.create(inventoryDomain);
    }
}
