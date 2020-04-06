	package com.springEMS.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<String> handleExcpetion(CustomException c){
		//System.out.println(c.getMessage());
		return new ResponseEntity<String>(c.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
