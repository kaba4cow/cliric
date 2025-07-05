package com.kaba4cow.cliric.error;

public class CliricException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CliricException(String message, Throwable cause) {
		super(message, cause);
	}

	public CliricException(String message) {
		super(message);
	}

	public CliricException(Throwable cause) {
		super(cause);
	}

}
