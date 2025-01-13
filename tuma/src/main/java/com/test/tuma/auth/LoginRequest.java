package com.test.tuma.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

//	private String firstname;
//	private String lastname;
//	private String email;
	private String password;
	private String username;
	public LoginRequest(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "RegisterRequest [password=" + password + ", username=" + username + "]";
	}
	

	
	
}
