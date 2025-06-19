package com.jdn.varieties.inventory_control.users.application.login;

import java.util.List;

@FunctionalInterface
public interface CreateAccessTokenJwt {

	String createJWtToken(String email, List<String> roles);
}
