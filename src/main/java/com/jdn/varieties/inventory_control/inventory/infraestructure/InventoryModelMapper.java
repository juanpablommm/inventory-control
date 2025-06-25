package com.jdn.varieties.inventory_control.inventory.infraestructure;

import com.jdn.varieties.inventory_control.inventory.domain.InventoryDomain;
import com.jdn.varieties.inventory_control.inventory.domain.InventoryTypeEnum;
import com.jdn.varieties.inventory_control.roles.infraestructure.RoleEntity;
import com.jdn.varieties.inventory_control.shared.infrastructure.ModelMapperFactory;
import com.jdn.varieties.inventory_control.users.domain.UserDomain;
import com.jdn.varieties.inventory_control.users.infrastructure.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Objects;

@Component
public final class InventoryModelMapper {

	public static InventoryDomain toDomain(InventoryEntity inventory) {
		return new InventoryDomain(inventory.getDescription(),
				InventoryTypeEnum.valueOf(inventory.getInventoryType()), inventory.getDate(),
				inventory.getRegistrationDate(), inventory.getPrice(), inventory.getAmount(),
				inventory.getUnitPrice(), inventory.getUser().getUsername());
	}

	public static InventoryEntity toEntity(InventoryDomain inventoryDomain, UserEntity userEntity) {
		return InventoryEntity.builder()
				.description(inventoryDomain.description())
				.inventoryType(inventoryDomain.inventoryType().getType())
				.user(userEntity)
				.date(inventoryDomain.date())
				.price(inventoryDomain.price())
				.amount(inventoryDomain.amount())
				.registrationDate(OffsetDateTime.now())
				.unitPrice(inventoryDomain.unitPrice())
				.build();
	}

}
