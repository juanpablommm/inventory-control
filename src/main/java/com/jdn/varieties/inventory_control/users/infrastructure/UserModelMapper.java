package com.jdn.varieties.inventory_control.users.infrastructure;

import com.jdn.varieties.inventory_control.roles.infraestructure.RoleEntity;
import com.jdn.varieties.inventory_control.shared.infrastructure.ModelMapperFactory;
import com.jdn.varieties.inventory_control.users.domain.UserDomain;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class UserModelMapper {

	public static UserDomain mapToUserDomain(UserEntity userEntity) {
		final ModelMapper modelMapper = ModelMapperFactory.getModelMapper();
		final UserDomain userDomain = modelMapper.map(userEntity, UserDomain.class);
		final RoleEntity roleEntity = userEntity.getRole();
		if (Objects.nonNull(roleEntity))
			userDomain.setRole(roleEntity.getName());
		return userDomain;
	}

	public static UserEntity mapToUserEntity(UserDomain userDomain) {
		final ModelMapper modelMapper = ModelMapperFactory.getModelMapper();
		final UserEntity userEntity = modelMapper.map(userDomain, UserEntity.class);
		if (Objects.nonNull(userDomain.getRole())) {
			userEntity.setRole(RoleEntity.builder().name(userDomain.getRole()).build());
		}
		return userEntity;
	}
}
