package com.jdn.varieties.inventory_control.users.infrastructure;

import com.jdn.varieties.inventory_control.roles.infraestructure.RoleEntity;
import com.jdn.varieties.inventory_control.shared.infrastructure.ModelMapperFactory;
import com.jdn.varieties.inventory_control.users.domain.UserDomain;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class UserModelMapper {

	public static UserDomain mapToUserDomain(UserEntity userEntity) {
		final Optional<RefreshTokenEntity> token = Optional.of(userEntity.getToken());
		final Optional<RoleEntity> role = Optional.of(userEntity.getRole());
		return new UserDomain(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail(),
                role.map(RoleEntity::getName).orElse(null),
				token.map(RefreshTokenEntity::getToken).orElse(null));

	}

	public static UserEntity mapToUserEntity(UserDomain userDomain) {
		final RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
				.token(userDomain.token())
				.build();
		final RoleEntity roleEntity = RoleEntity.builder()
				.name(userDomain.username())
				.build();

		return UserEntity.builder()
				.email(userDomain.email())
				.password(userDomain.password())
				.username(userDomain.username())
				.token(refreshTokenEntity)
				.role(roleEntity)
				.build();
	}
}
