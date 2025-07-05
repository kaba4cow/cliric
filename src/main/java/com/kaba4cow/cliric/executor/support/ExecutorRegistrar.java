package com.kaba4cow.cliric.executor.support;

import java.lang.reflect.Method;
import java.util.Objects;

import com.kaba4cow.cliric.annotation.Command;
import com.kaba4cow.cliric.commandpath.CommandPath;
import com.kaba4cow.cliric.commandpath.CommandPathBuilder;
import com.kaba4cow.cliric.error.CliricException;
import com.kaba4cow.cliric.executor.Executor;

public class ExecutorRegistrar {

	private final ExecutorRegistry registry;

	public ExecutorRegistrar(ExecutorRegistry registry) {
		this.registry = registry;
	}

	public void registerExecutor(Object object) {
		Objects.requireNonNull(object);
		Class<?> type = object.getClass();
		CommandPath typeCommandPath = CommandPathBuilder.buildTypeCommandPath(type);
		try {
			for (Method method : type.getMethods())
				if (method.isAnnotationPresent(Command.class)) {
					method.setAccessible(true);
					CommandPath methodCommandPath = CommandPathBuilder.buildMethodCommandPath(typeCommandPath, method);
					Executor executor = new Executor(methodCommandPath, object, method);
					registry.registerExecutor(executor);
				}
		} catch (Exception exception) {
			throw new CliricException(String.format("Could not register executor %s", type.getName()), exception);
		}
	}

}
