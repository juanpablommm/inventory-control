package com.jdn.varieties.inventory_control.inventory.application.find;

import com.jdn.varieties.inventory_control.inventory.application.InventoryMapper;
import com.jdn.varieties.inventory_control.inventory.application.dto.InventoryResponseDto;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryDomain;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryRepository;
import com.jdn.varieties.inventory_control.shared.application.PaginatedResponseDto;
import com.jdn.varieties.inventory_control.shared.domain.PaginatedDomain;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InventoryFindCommandHandler {

    private final InventoryRepository inventoryRepository;

    public InventoryFindCommandHandler(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public PaginatedResponseDto<InventoryResponseDto> find(Integer size, Integer page){
        final PaginatedDomain<InventoryDomain> inventoryDomainPaginatedDomain = this.inventoryRepository.findAll(size, page);
        final Set<InventoryResponseDto> setInventoryResponseDto = inventoryDomainPaginatedDomain.items().stream()
                .map(InventoryMapper::toDto).collect(Collectors.toSet());
        final PaginatedResponseDto.Meta meta = PaginatedResponseDto.Meta.builder()
                .hasNext(inventoryDomainPaginatedDomain.hasNext())
                .hasPrevious(inventoryDomainPaginatedDomain.hasPrevious())
                .pagesTotal(inventoryDomainPaginatedDomain.pagesTotal())
                .totalElements(inventoryDomainPaginatedDomain.totalElements())
                .build();

        return PaginatedResponseDto.<InventoryResponseDto>builder()
                .data(setInventoryResponseDto)
                .meta(meta)
                .build();
    }
}
