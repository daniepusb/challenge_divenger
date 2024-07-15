package com.pdaniel.swapi.proxy.domain.exception;

public class VehicleNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -5882998139335827204L;

	public VehicleNotFoundException(String message) {
        super(message);
    }

}
