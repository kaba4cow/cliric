package com.kaba4cow.cliric.error;

public class CliArgsParserException extends CliricException {

	private static final long serialVersionUID = 1L;

	public CliArgsParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public CliArgsParserException(String message) {
		super(message);
	}

	public CliArgsParserException(Throwable cause) {
		super(cause);
	}

}
