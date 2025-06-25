package com.jdn.varieties.inventory_control.users.domain;

import java.util.Objects;

public record UserDomain (
	String username,
	String password,
	String email,
	String role,
	String token
){}