package com.test.tuma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExeption  extends RuntimeException{
	
	@ExceptionHandler(value=UserNotFoundExeption.class)
	public ResponseEntity<?> exception(UserNotFoundExeption exception) {
		
		return new ResponseEntity<>("user not found ",HttpStatus.NOT_FOUND);
	}

}
