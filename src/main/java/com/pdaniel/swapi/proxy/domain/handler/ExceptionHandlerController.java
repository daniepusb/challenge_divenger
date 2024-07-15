package com.pdaniel.swapi.proxy.domain.handler;

import com.pdaniel.swapi.proxy.domain.dto.ResponseErrorDto;
import com.pdaniel.swapi.proxy.domain.exception.PersonNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
	
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<ResponseErrorDto> handleError(HttpServletRequest req, Exception ex) {
		 log.error(ex.getMessage());
		 return new ResponseEntity<>(ResponseErrorDto.builder().code("500").description(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
	 @ExceptionHandler(PersonNotFoundException.class)
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	  public ResponseEntity<ResponseErrorDto> handlePersonNotFound(HttpServletRequest req, Exception ex) {
	    log.error(ex.getMessage());
	    return new ResponseEntity<>(ResponseErrorDto.builder().code("404").description(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
	  }

}
