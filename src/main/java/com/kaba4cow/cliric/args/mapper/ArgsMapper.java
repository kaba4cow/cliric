package com.kaba4cow.cliric.args.mapper;

import com.kaba4cow.cliric.args.Arg;
import com.kaba4cow.cliric.args.ExecutorArgs;
import com.kaba4cow.cliric.args.cli.CliArg;
import com.kaba4cow.cliric.args.cli.CliArgs;
import com.kaba4cow.cliric.args.mapper.support.ArgMapperRegistry;
import com.kaba4cow.cliric.error.UnsupportedArgTypeException;
import com.kaba4cow.cliric.executor.Executor;

public class ArgsMapper {

	private final ArgMapperRegistry registry;

	public ArgsMapper(ArgMapperRegistry registry) {
		this.registry = registry;
	}

	public ExecutorArgs mapArgs(CliArgs cliArgs, Executor executor) {
		ExecutorArgs executorArgs = executor.createExecutorArgs();
		for (String argName : executorArgs.getArgNames()) {
			Arg<?> executorArg = executorArgs.getArg(argName);
			if (cliArgs.containsArg(argName)) {
				CliArg cliArg = cliArgs.getArg(argName);
				Class<?> argType = executorArg.getType();
				ArgMapper<?> argMapper = registry.getArgMapper(argType)//
						.orElseThrow(() -> new UnsupportedArgTypeException(argType));
				Object mappedValue = argMapper.mapArg(cliArg);
				executorArg.setValue(mappedValue);
			}
		}
		return executorArgs;
	}

}
