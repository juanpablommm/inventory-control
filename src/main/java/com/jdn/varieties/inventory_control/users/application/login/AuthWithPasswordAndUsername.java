package com.jdn.varieties.inventory_control.users.application.login;

import com.jdn.varieties.inventory_control.users.domain.UserDomain;

@FunctionalInterface
public interface AuthWithPasswordAndUsername {

	UserDomain authentication(String username, String password);
}
