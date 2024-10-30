package com.test.tuma.auth;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.tuma.entity.Role;
import com.test.tuma.entity.Student;
import com.test.tuma.exception.UserNotFoundExeption;
//import com.test.tuma.entity.User;
import com.test.tuma.repository.StudentRepository;
import com.test.tuma.service.JwtService;
import com.test.tuma.responses.CustomResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
	
	private final StudentRepository studentRepository;
	
	private BCryptPasswordEncoder encoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;
    public AuthenticationService(StudentRepository studentRepository, PasswordEncoder passwordEncoder,
			BCryptPasswordEncoder encoder, JwtService jwtService, AuthenticationManager authenticationManager) {
		super();
		this.studentRepository = studentRepository;
		this.encoder = encoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}





	public CustomResponse login(LoginRequest loginRequest) {
		//
//		Student user = new Student(
//				registerRequest.getFirstname(), 
//				registerRequest.getLastname(), 
//				registerRequest.getEmail(),
//				passwordEncoder.encode(registerRequest.getPassword()),
//				Role.STUDENT_MAKER
//				);
		Student student = studentRepository.findByUsername(loginRequest.getUsername());
		if (student == null) {
			System.out.println("Student not found");
			throw new UserNotFoundExeption();
		}
		

		if(validate(loginRequest.getPassword(),student.getPassword())) {
			String jwtToken = jwtService.generateToken(student);
			return new CustomResponse("200",jwtToken);
		}
		
//		studentRepository.save(user);
	      return new CustomResponse("201","failed to get the token");

	}



	private boolean validate(String password, String password2) {
		logger.info("matches Password-->{}",encoder.matches(password2, password));
	
	     return encoder.matches(password, password2);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
				authenticationRequest.getPassword()));
		var user = studentRepository.findStudentByEmail(authenticationRequest.getEmail()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}
	
	
//	public static void main(String [] args) {
//		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String pass = "123456";
//		String encodedPass = encoder.encode(pass);
//		System.out.println("Encoded Password:-->"+encodedPass);
//		//$2a$10$tIq19Y2OKLw0f3HNLezH9eXBQlhpInrT337wZbUpgLuCIZ6JGH3/O
//		//$2a$10$WpzNb5.lg31D0NrhXYaBh.AZPx8PYv4DV104xMkJzbLC7e.0LW20.
//	}

}
