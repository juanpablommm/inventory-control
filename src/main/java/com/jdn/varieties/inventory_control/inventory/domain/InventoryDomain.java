package com.jdn.varieties.inventory_control.inventory.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

public record InventoryDomain (
    String description,
    InventoryTypeEnum inventoryType,
    OffsetDateTime date,
    OffsetDateTime creationDate,
    Double price,
    Integer amount,
    Double unitPrice,
    String createdByUser
){}