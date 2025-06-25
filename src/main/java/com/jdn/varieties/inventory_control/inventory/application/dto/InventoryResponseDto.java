package com.jdn.varieties.inventory_control.inventory.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class InventoryResponseDto {

    private String description;
    private Double price;
    private Double unitPrice;
    private String date;
    private Integer amount;
}
