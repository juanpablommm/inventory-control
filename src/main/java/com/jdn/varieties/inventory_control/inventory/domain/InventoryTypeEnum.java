package com.jdn.varieties.inventory_control.inventory.domain;

public enum InventoryTypeEnum {

    EXPENSE("EXPENSE"), SALE("SALE");

    private final String type;

    InventoryTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
