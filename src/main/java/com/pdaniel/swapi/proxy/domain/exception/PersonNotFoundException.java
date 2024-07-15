package com.pdaniel.swapi.proxy.domain.exception;

public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5487256536415828982L;
	
	public PersonNotFoundException(String message) {
        super(message);
    }

}
