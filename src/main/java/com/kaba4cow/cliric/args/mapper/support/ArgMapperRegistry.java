package com.kaba4cow.cliric.args.mapper.support;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaba4cow.cliric.args.mapper.ArgMapper;

public class ArgMapperRegistry {

	private static final Logger log = LoggerFactory.getLogger("ArgMapperRegistry");

	private final Map<Class<?>, ArgMapper<?>> registry = new ConcurrentHashMap<>();

	void registerArgMapper(Class<?> argType, ArgMapper<?> argMapper) {
		registry.put(argType, argMapper);
		log.debug("Registered ArgMapper {} for type {}", argMapper.getClass().getName(), argType.getName());
	}

	@SuppressWarnings("unchecked")
	public <T> Optional<ArgMapper<T>> getArgMapper(Class<T> argType) {
		return Optional.ofNullable((ArgMapper<T>) registry.get(argType));
	}

}
