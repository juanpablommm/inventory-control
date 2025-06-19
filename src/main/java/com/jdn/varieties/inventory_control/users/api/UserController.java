package com.jdn.varieties.inventory_control.users.api;

import com.jdn.varieties.inventory_control.users.application.dto.AuthRequestPasswordDto;
import com.jdn.varieties.inventory_control.users.application.dto.AuthResponseDto;
import com.jdn.varieties.inventory_control.users.application.dto.UserResponseDto;
import com.jdn.varieties.inventory_control.users.application.find.CommandHandlerFindUser;
import com.jdn.varieties.inventory_control.users.application.login.CommandHandlerLogin;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController {

	private final CommandHandlerFindUser commandHandlerFindUser;
	private final CommandHandlerLogin commandHandlerLogin;

	@GetMapping(path = "/{email}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable String email) {
		return ResponseEntity.ok(this.commandHandlerFindUser.findUser(email));
	}

	@PostMapping(path = "/login")
	// TODO: ajustar para validations
	public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestPasswordDto authRequestPasswordDto) {
		return ResponseEntity.ok(this.commandHandlerLogin.authentication(authRequestPasswordDto));
	}
}
