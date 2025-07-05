package com.kaba4cow.cliric;

import com.kaba4cow.cliric.args.ExecutorArgs;
import com.kaba4cow.cliric.args.cli.CliArgs;
import com.kaba4cow.cliric.args.cli.parser.CliArgsParser;
import com.kaba4cow.cliric.args.cli.parser.DefaultCliArgsParser;
import com.kaba4cow.cliric.args.mapper.ArgsMapper;
import com.kaba4cow.cliric.args.mapper.support.ArgMapperRegistrar;
import com.kaba4cow.cliric.args.mapper.support.ArgMapperRegistry;
import com.kaba4cow.cliric.commandpath.CommandPath;
import com.kaba4cow.cliric.error.UnknownCommandPathException;
import com.kaba4cow.cliric.executor.Executor;
import com.kaba4cow.cliric.executor.support.ExecutorRegistrar;
import com.kaba4cow.cliric.executor.support.ExecutorRegistry;

public class CliricExecutor {

	private final ExecutorRegistry executorRegistry = new ExecutorRegistry();

	private final ExecutorRegistrar executorRegistrar = new ExecutorRegistrar(executorRegistry);

	private final ArgMapperRegistry argMapperRegistry = new ArgMapperRegistry();

	private final ArgMapperRegistrar argMapperRegistrar = new ArgMapperRegistrar(argMapperRegistry);

	private final ArgsMapper argsMapper = new ArgsMapper(argMapperRegistry);

	private final CliArgsParser cliArgsParser;

	public CliricExecutor(CliArgsParser cliArgsParser) {
		this.cliArgsParser = cliArgsParser;
	}

	public CliricExecutor() {
		this(new DefaultCliArgsParser());
	}

	public String execute(String input) {
		CliArgs cliArgs = cliArgsParser.parse(input);
		CommandPath commandPath = cliArgs.getCommandPath();
		Executor executor = executorRegistry.getExecutor(commandPath)//
				.orElseThrow(//
						() -> new UnknownCommandPathException(commandPath));
		ExecutorArgs executorArgs = argsMapper.mapArgs(cliArgs, executor);
		return executor.execute(executorArgs);
	}

	public ExecutorRegistrar getExecutorRegistrar() {
		return executorRegistrar;
	}

	public ArgMapperRegistrar getArgMapperRegistrar() {
		return argMapperRegistrar;
	}

}
