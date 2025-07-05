package com.kaba4cow.cliric.args.mapper;

import java.util.List;

import com.kaba4cow.cliric.args.cli.CliArg;

public interface ArgMapper<T> {

	default T mapArg(CliArg arg) {
		if (arg.isEmpty())
			return defaultValue();
		try {
			return mapValue(arg.getValue());
		} catch (Exception exception) {
			return defaultValue();
		}
	}

	T mapValue(String value);

	T defaultValue();

	List<Class<T>> getArgTypes();

}
