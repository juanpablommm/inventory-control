package com.jdn.varieties.inventory_control.users.domain;

import java.util.Optional;

public interface UserRepository {

	Optional<UserDomain> findByUsername(String username);

	void createRefreshTokenFromUser(String username, String refreshToken);
}
