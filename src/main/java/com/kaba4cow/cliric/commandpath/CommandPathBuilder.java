package com.kaba4cow.cliric.commandpath;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.kaba4cow.cliric.annotation.Command;

public class CommandPathBuilder {

	private CommandPathBuilder() {}
	
	public static CommandPath buildTypeCommandPath(Class<?> type) {
		CommandPath typeCommandPath = new CommandPath();
		if (type.isAnnotationPresent(Command.class))
			typeCommandPath.addCommands(Arrays.asList(type.getAnnotation(Command.class).value()));
		return typeCommandPath;
	}

	public static CommandPath buildMethodCommandPath(CommandPath typeCommandPath, Method method) {
		CommandPath methodCommandPath = new CommandPath();
		methodCommandPath.addCommands(typeCommandPath.getCommands());
		if (method.isAnnotationPresent(Command.class))
			methodCommandPath.addCommands(Arrays.asList(method.getAnnotation(Command.class).value()));
		return methodCommandPath;
	}

}
