package com.kaba4cow.cliric.args.cli;

import com.kaba4cow.cliric.args.Arg;

public class CliArg extends Arg<String> {

	public CliArg(String name, String value) {
		super(name, value);
	}

	public CliArg(String name) {
		super(name, String.class);
	}

}
