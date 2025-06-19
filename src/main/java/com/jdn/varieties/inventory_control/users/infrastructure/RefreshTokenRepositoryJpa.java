package com.jdn.varieties.inventory_control.users.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepositoryJpa extends JpaRepository<RefreshTokenEntity, Integer> {

}
