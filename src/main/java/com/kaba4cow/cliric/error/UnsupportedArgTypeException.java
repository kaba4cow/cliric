package com.kaba4cow.cliric.error;

public class UnsupportedArgTypeException extends CliricException {

	private static final long serialVersionUID = 1L;

	public UnsupportedArgTypeException(Class<?> argType) {
		super(String.format("Unsupported arg type: %s", argType.getName()));
	}

}
