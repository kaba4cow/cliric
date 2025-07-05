package com.kaba4cow.cliric.error;

import com.kaba4cow.cliric.commandpath.CommandPath;

public class UnknownCommandPathException extends CliricException {

	private static final long serialVersionUID = 1L;

	public UnknownCommandPathException(CommandPath commandPath) {
		super(String.format("Unknown command path: %s", commandPath.toCliString()));
	}

}
