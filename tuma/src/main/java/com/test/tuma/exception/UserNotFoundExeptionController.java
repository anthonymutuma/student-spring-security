package com.test.tuma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExeptionController {
	@ExceptionHandler(value=UserNotFoundExeption.class)
	public ResponseEntity<?> exception(UserNotFoundExeption exception) {	
		return new ResponseEntity<>("user not found ",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = EmailIsPresent.class)
	public ResponseEntity<?> emailIsPresent(EmailIsPresent emailPresent){
		return new ResponseEntity<>("Email is Present in the System, please register another Email",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = StudentUpdatedSuccessException.class)
	public ResponseEntity<?> studentUpdatedSuccess(StudentUpdatedSuccessException studentUpdatedSuccessException){
		return new ResponseEntity<>("Student Updated Successfully(Success)", HttpStatus.ACCEPTED);
	}
	
	@ExceptionHandler(value = StudentAddedSuccessfullyException.class)
	public ResponseEntity<?> studentAddedSuccessfully(StudentAddedSuccessfullyException studentAddedSuccessfullyException){
		return new ResponseEntity<>("Student has been added successfully",HttpStatus.OK);
	}
}
