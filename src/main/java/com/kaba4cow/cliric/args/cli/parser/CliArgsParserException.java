package com.kaba4cow.cliric.args.cli.parser;

public class CliArgsParserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CliArgsParserException() {
		super();
	}

	public CliArgsParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

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
