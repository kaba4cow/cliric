package com.kaba4cow.cliric.args.mapper.impl;

import java.util.Arrays;
import java.util.List;

import com.kaba4cow.cliric.args.mapper.ArgMapper;

public class IntegerArgMapper implements ArgMapper<Integer> {

	@Override
	public Integer mapValue(String value) {
		return Integer.parseInt(value);
	}

	@Override
	public Integer defaultValue() {
		return null;
	}

	@Override
	public List<Class<Integer>> getArgTypes() {
		return Arrays.asList(Integer.class, int.class);
	}

}
