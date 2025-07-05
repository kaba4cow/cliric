package com.kaba4cow.cliric.executor.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaba4cow.cliric.commandpath.CommandPath;
import com.kaba4cow.cliric.executor.Executor;

public class ExecutorRegistry {

	private static final Logger log = LoggerFactory.getLogger("ExecutorRegistry");

	private final Map<CommandPath, Executor> registry = new HashMap<>();

	void registerExecutor(Executor executor) {
		registry.put(executor.getCommandPath(), executor);
		log.debug("Registered {}", executor);
	}

	public Set<CommandPath> getCommandPaths() {
		return Collections.unmodifiableSet(registry.keySet());
	}

	public List<CommandPath> getSortedCommandPaths() {
		return Collections.unmodifiableList(registry.keySet().stream()//
				.sorted()//
				.collect(Collectors.toList()));
	}

	public Optional<Executor> getExecutor(CommandPath commandPath) {
		return Optional.ofNullable(registry.get(commandPath));
	}

}
