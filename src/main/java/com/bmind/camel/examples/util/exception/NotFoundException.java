package com.bmind.camel.examples.util.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8687776222719512310L;
	
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(Throwable cause) {
		super(cause);
	}

}