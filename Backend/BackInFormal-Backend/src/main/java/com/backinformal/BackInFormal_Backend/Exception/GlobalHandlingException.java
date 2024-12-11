package com.backinformal.BackInFormal_Backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalHandlingException {

	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundExcep(UserNotFoundException error)
    {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
}
