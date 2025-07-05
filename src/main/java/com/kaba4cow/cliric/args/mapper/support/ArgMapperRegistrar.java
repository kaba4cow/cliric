package com.kaba4cow.cliric.args.mapper.support;

import com.kaba4cow.cliric.args.mapper.ArgMapper;

public class ArgMapperRegistrar {

	private final ArgMapperRegistry registry;

	public ArgMapperRegistrar(ArgMapperRegistry registry) {
		this.registry = registry;
	}

	public void registerArgMapper(ArgMapper<?> argMapper) {
		for (Class<?> argType : argMapper.getArgTypes())
			registry.registerArgMapper(argType, argMapper);
	}

}
