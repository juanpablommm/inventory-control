package com.jdn.varieties.inventory_control.inventory.application.dto;

public record InventoryRequestDto(String inventoryType, String date, Double price,
                                  Integer amount, String description) {
}
