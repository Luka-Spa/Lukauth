package com.luka.lukauth.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class CustomControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<List<String>> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    List<String> errors = new ArrayList<String>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        errors.add(ex.getBindingResult().getFieldError().getField() + " " + error.getDefaultMessage());
	    });
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	@ResponseBody
	public ResponseEntity<String> handleJwtExceptions(
			ExpiredJwtException ex) {
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
}
