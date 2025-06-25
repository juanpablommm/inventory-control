package com.jdn.varieties.inventory_control.users.application.login;

import com.jdn.varieties.inventory_control.users.application.dto.AuthRequestPasswordDto;
import com.jdn.varieties.inventory_control.users.application.dto.AuthResponseDto;
import com.jdn.varieties.inventory_control.users.domain.UserDomain;
import com.jdn.varieties.inventory_control.users.domain.UserRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandHandlerLogin {

	private final AuthWithPasswordAndUsername authWithPasswordAndUsername;
	private final CreateAccessTokenJwt createAccessTokenJwt;
	private final UserRepository userRepository;

	public AuthResponseDto authentication(AuthRequestPasswordDto authRequestPasswordDto) {
		final UserDomain userDomain = this.authWithPasswordAndUsername.authentication(authRequestPasswordDto.username(),
				authRequestPasswordDto.password());
		final String accessToken = this.createAccessTokenJwt.createJWtToken(userDomain.email(),
				List.of(userDomain.role()));
		final String refreshToken = String.valueOf(UUID.randomUUID());
		this.userRepository.createRefreshTokenFromUser(userDomain.username(), refreshToken);
		return AuthResponseDto.builder().accessToken(accessToken).token(refreshToken).build();
	}
}
