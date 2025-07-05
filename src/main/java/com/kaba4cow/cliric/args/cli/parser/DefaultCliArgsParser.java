package com.kaba4cow.cliric.args.cli.parser;

import java.util.ArrayList;
import java.util.List;

import com.kaba4cow.cliric.args.cli.CliArg;
import com.kaba4cow.cliric.args.cli.CliArgs;

public class DefaultCliArgsParser implements CliArgsParser {

	@Override
	public CliArgs parse(String input) {
		CliArgs cliArgs = new CliArgs();
		List<String> tokens = tokenize(input);
		int i = 0;
		while (i < tokens.size()) {
			String token = tokens.get(i);
			if (token.startsWith("--")) {
				String arg = token.substring(2);
				int assignmentIndex = arg.indexOf('=');
				if (assignmentIndex == -1) {
					String name = arg.trim();
					if (name.isEmpty())
						throw new CliArgsParserException(String.format("Empty argument name: %s", token));
					cliArgs.addArg(new CliArg(name));
				} else {
					String name = arg.substring(0, assignmentIndex).trim();
					String value = arg.substring(assignmentIndex + 1).trim();
					if (name.isEmpty())
						throw new CliArgsParserException(String.format("Empty argument name: %s", token));
					if (value.isEmpty())
						throw new CliArgsParserException(String.format("Empty value for argument: %s", token));
					cliArgs.addArg(new CliArg(name, value));
				}
			} else if (token.startsWith("-"))
				throw new CliArgsParserException(String.format("Invalid token: %s", token));
			else if (cliArgs.getArgs().isEmpty())
				cliArgs.addCommand(token);
			else
				throw new CliArgsParserException(String.format("Unexpected token: %s", token));
			i++;
		}
		return cliArgs;
	}

	private List<String> tokenize(String input) {
		List<String> tokens = new ArrayList<>();
		StringBuilder token = new StringBuilder();
		boolean inQuotes = false;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == '"')
				inQuotes = !inQuotes;
			else if (Character.isWhitespace(c) && !inQuotes) {
				if (token.length() > 0) {
					tokens.add(token.toString());
					token.setLength(0);
				}
			} else
				token.append(c);
		}
		if (inQuotes)
			throw new CliArgsParserException("Unclosed quoted string");
		if (token.length() > 0)
			tokens.add(token.toString());
		return tokens;
	}

}
