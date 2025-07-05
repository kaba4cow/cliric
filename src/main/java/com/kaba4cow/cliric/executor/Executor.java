package com.kaba4cow.cliric.executor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.kaba4cow.cliric.annotation.Param;
import com.kaba4cow.cliric.args.Arg;
import com.kaba4cow.cliric.args.ExecutorArgs;
import com.kaba4cow.cliric.commandpath.CommandPath;
import com.kaba4cow.cliric.error.CliricException;

public class Executor {

	private final CommandPath commandPath;

	private final Object object;

	private final Method method;

	public Executor(CommandPath commandPath, Object object, Method method) {
		this.commandPath = commandPath;
		this.object = object;
		this.method = method;
	}

	public String execute(ExecutorArgs executorArgs) {
		try {
			Object[] methodArgs = toMethodArgs(executorArgs);
			return (String) method.invoke(object, methodArgs);
		} catch (Exception exception) {
			throw new CliricException(String.format("Could not execute command '%s'", commandPath.toCliString()), exception);
		}
	}

	private Object[] toMethodArgs(ExecutorArgs executorArgs) {
		Parameter[] parameters = method.getParameters();
		Object[] methodArgs = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			String name = getParameterName(parameters[i]);
			methodArgs[i] = executorArgs.containsArg(name)//
					? executorArgs.getArg(name).getValue()//
					: null;
		}
		return methodArgs;
	}

	public ExecutorArgs createExecutorArgs() {
		ExecutorArgs args = new ExecutorArgs();
		for (Parameter parameter : method.getParameters())
			args.addArg(new Arg<>(getParameterName(parameter), parameter.getType()));
		return args;
	}

	private String getParameterName(Parameter parameter) {
		return parameter.getAnnotation(Param.class).value();
	}

	public CommandPath getCommandPath() {
		return commandPath;
	}

	public Object getObject() {
		return object;
	}

	public Method getMethod() {
		return method;
	}

	@Override
	public String toString() {
		return String.format("Executor [commandPath=%s, object=%s, method=%s]", commandPath, object, method);
	}

}
