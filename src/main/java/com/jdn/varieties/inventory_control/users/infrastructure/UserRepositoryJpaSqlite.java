package com.jdn.varieties.inventory_control.users.infrastructure;

import com.jdn.varieties.inventory_control.users.domain.UserDomain;
import com.jdn.varieties.inventory_control.users.domain.UserRepository;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Primary
@AllArgsConstructor
public class UserRepositoryJpaSqlite implements UserRepository {

	private final UserRepositoryJpa userRepositoryJpa;
	private final RefreshTokenRepositoryJpa refreshTokenRepositoryJpa;

	@Override
	public Optional<UserDomain> findByUsername(String username) {
		Optional<UserEntity> userEntity = this.userRepositoryJpa.findByUsername(username);
		return userEntity.map(UserModelMapper::mapToUserDomain);
	}

	@Transactional
	@Override
	public void createRefreshTokenFromUser(String username, String refreshToken) {
		// TODO: Ajustar por el manejo de exceptions
		final UserEntity userForToken = this.userRepositoryJpa.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found"));
		RefreshTokenEntity refreshTokenEntity = userForToken.getToken();
		if (Objects.isNull(refreshTokenEntity)) refreshTokenEntity = RefreshTokenEntity.builder().user(userForToken).build();

		refreshTokenEntity.setToken(refreshToken);
		refreshTokenEntity.setExpiryTime(OffsetDateTime.now().plusMinutes(10));
		this.refreshTokenRepositoryJpa.save(refreshTokenEntity);
	}
}
