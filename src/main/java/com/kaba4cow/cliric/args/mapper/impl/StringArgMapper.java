package com.kaba4cow.cliric.args.mapper.impl;

import java.util.Arrays;
import java.util.List;

import com.kaba4cow.cliric.args.mapper.ArgMapper;

public class StringArgMapper implements ArgMapper<String> {

	@Override
	public String mapValue(String value) {
		return value;
	}

	@Override
	public String defaultValue() {
		return null;
	}

	@Override
	public List<Class<String>> getArgTypes() {
		return Arrays.asList(String.class);
	}

}
