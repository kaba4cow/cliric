package com.kaba4cow.cliric.args;

import java.util.Objects;

public class Arg<T> {

	private final String name;

	private final Class<?> type;

	private T value;

	public Arg(String name, T value) {
		this.name = name;
		this.type = value.getClass();
		this.value = value;
	}

	public Arg(String name, Class<T> type) {
		this.name = name;
		this.type = type;
		this.value = null;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public T getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	public void setValue(Object value) {
		this.value = (T) value;
	}

	public boolean isPresent() {
		return Objects.nonNull(value);
	}

	public boolean isEmpty() {
		return Objects.isNull(value);
	}

}
