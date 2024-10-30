package com.test.tuma.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.tuma.responses.CustomResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	public AuthenticationController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<CustomResponse> register(
			@RequestBody LoginRequest registerRequest
			){
		return ResponseEntity.ok(authenticationService.login(registerRequest));
	}
	
	@PostMapping("/authentcate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest authenticationRequest
			){
		return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
	}
}
