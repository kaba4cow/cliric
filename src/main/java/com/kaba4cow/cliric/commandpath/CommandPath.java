package com.kaba4cow.cliric.commandpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommandPath implements Comparable<CommandPath> {

	private final List<String> commands = new ArrayList<>();

	@Override
	public int compareTo(CommandPath other) {
		return toCliString().compareTo(other.toCliString());
	}

	public void addCommand(String command) {
		commands.add(command);
	}

	public void addCommands(List<String> commands) {
		this.commands.addAll(commands);
	}

	public boolean containsCommand(String command) {
		return commands.contains(command);
	}

	public List<String> getCommands() {
		return Collections.unmodifiableList(commands);
	}

	public int length() {
		return commands.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(commands);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandPath other = (CommandPath) obj;
		return Objects.equals(commands, other.commands);
	}

	public String toCliString() {
		return commands.stream().collect(Collectors.joining(" "));
	}

	@Override
	public String toString() {
		return String.format("CommandPath [commands=%s]", commands);
	}

}
