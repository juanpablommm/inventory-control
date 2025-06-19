package com.jdn.varieties.inventory_control.users.domain;

import java.util.Objects;

public class UserDomain {

	private String username;
	private String password;
	private String email;
	private String role;
	private String token;

	public UserDomain() {
	}

	public UserDomain(String username, String password, String email, String role, String token) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserDomain{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", email='" + email
				+ '\'' + ", role='" + role + '\'' + ", token='" + token + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		UserDomain that = (UserDomain) o;
		return Objects.equals(username, that.username) && Objects.equals(password, that.password)
				&& Objects.equals(email, that.email) && Objects.equals(role, that.role)
				&& Objects.equals(token, that.token);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, password, email, role, token);
	}
}
