package com.jdn.varieties.inventory_control.users.application.find;

import com.jdn.varieties.inventory_control.shared.infrastructure.ModelMapperFactory;
import com.jdn.varieties.inventory_control.users.application.dto.UserResponseDto;
import com.jdn.varieties.inventory_control.users.domain.UserDomain;
import com.jdn.varieties.inventory_control.users.domain.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandHandlerFindUser {

	private final UserRepository userRepository;

	public UserResponseDto findUser(final String username) {
		final Optional<UserDomain> optionalUserDomain = this.userRepository.findByUsername(username);

		optionalUserDomain.orElseThrow(() -> new RuntimeException("User not found"));
		// TODO: Ajustar
		return ModelMapperFactory.getModelMapper().map(optionalUserDomain.get(), UserResponseDto.class);
	}
}
