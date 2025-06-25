package com.jdn.varieties.inventory_control.inventory.domain;


import com.jdn.varieties.inventory_control.shared.domain.PaginatedDomain;

import java.time.OffsetDateTime;
import java.util.Set;

public interface InventoryRepository {

    void create(InventoryDomain inventoryDomain);

    InventoryDomain findByDate(OffsetDateTime date);

    PaginatedDomain<InventoryDomain> findAll(Integer size, Integer page);

}
