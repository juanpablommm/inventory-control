package com.jdn.varieties.inventory_control.inventory.application;

import com.jdn.varieties.inventory_control.inventory.application.dto.InventoryRequestDto;
import com.jdn.varieties.inventory_control.inventory.application.dto.InventoryResponseDto;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryDomain;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryTypeEnum;
import com.jdn.varieties.inventory_control.users.domain.UserDomain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class InventoryMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final ZoneId ZONE_ID = ZoneId.of("America/Bogota");

    public static InventoryDomain toDomain(InventoryRequestDto inventoryRequestDto){
        final InventoryTypeEnum inventoryType = InventoryTypeEnum.valueOf(inventoryRequestDto.inventoryType());
        final Double unitPrice = inventoryRequestDto.price() / inventoryRequestDto.amount();
        final LocalDate localDate = LocalDate.parse(inventoryRequestDto.date(), FORMATTER);
        final OffsetDateTime date = localDate.atStartOfDay(ZONE_ID).toOffsetDateTime();
        return new InventoryDomain(inventoryRequestDto.description(),
                inventoryType, date, OffsetDateTime.now(), inventoryRequestDto.price(), inventoryRequestDto.amount(),
                unitPrice, null);
    }

    public static InventoryResponseDto toDto(InventoryDomain inventoryDomain){
        final String date = inventoryDomain .date().format(FORMATTER);
        return InventoryResponseDto.builder()
                .amount(inventoryDomain.amount())
                .date(date)
                .description(inventoryDomain.description())
                .price(inventoryDomain.price())
                .unitPrice(inventoryDomain.unitPrice())
                .build();
    }
}
