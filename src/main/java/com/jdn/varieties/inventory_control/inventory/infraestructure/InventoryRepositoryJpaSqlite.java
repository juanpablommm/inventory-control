package com.jdn.varieties.inventory_control.inventory.infraestructure;

import com.jdn.varieties.inventory_control.inventory.domain.InventoryDomain;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryRepository;
import com.jdn.varieties.inventory_control.shared.domain.PaginatedDomain;
import com.jdn.varieties.inventory_control.shared.infrastructure.InventoryApplicationException;
import com.jdn.varieties.inventory_control.users.infrastructure.UserEntity;
import com.jdn.varieties.inventory_control.users.infrastructure.UserRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
@AllArgsConstructor
public class InventoryRepositoryJpaSqlite implements InventoryRepository {

    private final InventoryRepositoryJpa inventoryRepositoryJpa;
    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public void create(InventoryDomain inventoryDomain) {
        final InventoryEntity inventoryEntity = InventoryModelMapper.toEntity(inventoryDomain, this.getUserFromSession());
        this.inventoryRepositoryJpa.save(inventoryEntity);
    }

    private UserEntity getUserFromSession(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String emailUser = authentication.getName();
        return this.userRepositoryJpa.findByEmail(emailUser)
                .orElseThrow(() -> new InventoryApplicationException("Error creating an inventory",
                        "No user was found for the email address %s when creating the inventory".formatted(emailUser),
                        HttpStatus.FORBIDDEN));
    }

    @Override
    public InventoryDomain findByDate(OffsetDateTime date) {
        return null;
    }

    @Override
    public PaginatedDomain<InventoryDomain> findAll(Integer size, Integer page) {
        final Page<InventoryEntity> inventoryEntityPage = this.inventoryRepositoryJpa.findAll(PageRequest.of(page, size));
        final Set<InventoryDomain> inventoryDomains = inventoryEntityPage.get().map(InventoryModelMapper::toDomain).collect(Collectors.toSet());
        return new PaginatedDomain<>(
                inventoryDomains, page, inventoryEntityPage.getTotalElements(),
                inventoryEntityPage.getTotalPages(), inventoryEntityPage.hasNext(),
                inventoryEntityPage.hasPrevious());
    }
}
