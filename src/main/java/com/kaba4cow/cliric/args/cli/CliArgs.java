package com.kaba4cow.cliric.args.cli;

import com.kaba4cow.cliric.args.Args;
import com.kaba4cow.cliric.commandpath.CommandPath;

public class CliArgs extends Args<CliArg> {

	private final CommandPath commandPath = new CommandPath();

	public void addCommand(String command) {
		commandPath.addCommand(command);;
	}

	public CommandPath getCommandPath() {
		return commandPath;
	}

}
