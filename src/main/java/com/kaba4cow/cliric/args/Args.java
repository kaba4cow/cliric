package com.kaba4cow.cliric.args;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class Args<T extends Arg<?>> {

	private final Map<String, T> args = new TreeMap<>();

	public void addArg(T arg) {
		args.put(arg.getName(), arg);
	}

	public boolean containsArg(String name) {
		return args.containsKey(name);
	}

	public T getArg(String name) {
		return args.get(name);
	}

	public Collection<T> getArgs() {
		return Collections.unmodifiableCollection(args.values());
	}

	public Set<String> getArgNames() {
		return Collections.unmodifiableSet(args.keySet());
	}

}
