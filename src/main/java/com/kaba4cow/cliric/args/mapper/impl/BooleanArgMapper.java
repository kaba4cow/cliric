package com.kaba4cow.cliric.args.mapper.impl;

import java.util.Arrays;
import java.util.List;

import com.kaba4cow.cliric.args.mapper.ArgMapper;

public class BooleanArgMapper implements ArgMapper<Boolean> {

	@Override
	public Boolean mapValue(String value) {
		return Boolean.parseBoolean(value);
	}

	@Override
	public Boolean defaultValue() {
		return false;
	}

	@Override
	public List<Class<Boolean>> getArgTypes() {
		return Arrays.asList(Boolean.class);
	}

}
