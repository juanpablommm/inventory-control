package com.jdn.varieties.inventory_control.inventory.infraestructure;

import com.jdn.varieties.inventory_control.inventory.domain.InventoryDomain;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryRepository;
import com.jdn.varieties.inventory_control.shared.domain.PaginatedDomain;

import java.time.OffsetDateTime;
import java.util.Set;

public class InMemoryInventoryRepository implements InventoryRepository {

    @Override
    public void create(InventoryDomain inventoryDomain) {

    }

    @Override
    public InventoryDomain findByDate(OffsetDateTime date) {
        return null;
    }

    @Override
    public PaginatedDomain<InventoryDomain> findAll(Integer page, Integer size) {
        return null;
    }
}
