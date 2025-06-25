package com.jdn.varieties.inventory_control.users.infrastructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByUsername(String username);

	Optional<UserEntity> findByEmail(String email);
}
