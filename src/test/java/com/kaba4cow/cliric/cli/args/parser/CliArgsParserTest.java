package com.kaba4cow.cliric.cli.args.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.kaba4cow.cliric.args.cli.CliArg;
import com.kaba4cow.cliric.args.cli.CliArgs;
import com.kaba4cow.cliric.args.cli.parser.CliArgsParser;
import com.kaba4cow.cliric.args.cli.parser.CliArgsParserException;
import com.kaba4cow.cliric.args.cli.parser.DefaultCliArgsParser;

public class CliArgsParserTest {

	private final CliArgsParser parser = new DefaultCliArgsParser();

	@Test
	public void simpleCommandAndArg() {
		CliArgs args = parser.parse("foo bar --baz=qux");
		assertEquals(2, args.getCommandPath().length());
		assertTrue(args.getCommandPath().containsCommand("foo"));
		assertTrue(args.getCommandPath().containsCommand("bar"));

		assertTrue(args.containsArg("baz"));
		CliArg baz = args.getArg("baz");
		assertNotNull(baz);
		assertEquals("qux", baz.getValue());
	}

	@Test
	public void quotedArgumentValue() {
		CliArgs args = parser.parse("foo --bar=\"hello world\"");
		assertEquals(1, args.getCommandPath().length());
		assertTrue(args.getCommandPath().containsCommand("foo"));

		assertTrue(args.containsArg("bar"));
		assertEquals("hello world", args.getArg("bar").getValue());
	}

	@Test
	public void commaSeparatedValues() {
		CliArgs args = parser.parse("foo --bar=one,two,three");
		assertTrue(args.containsArg("bar"));
		assertEquals("one,two,three", args.getArg("bar").getValue());
	}

	@Test
	public void singleDashThrows() {
		assertThrows(CliArgsParserException.class, () -> parser.parse("-bar=baz"));
	}

	@Test
	public void emptyValue() {
		assertThrows(CliArgsParserException.class, () -> parser.parse("foo --bar="));
	}

	@Test
	public void flagWithoutValue() {
		CliArgs args = parser.parse("foo --bar");
		assertTrue(args.containsArg("bar"));

		CliArg bar = args.getArgs().iterator().next();
		assertEquals("bar", bar.getName());
		assertTrue(bar.isEmpty());
		assertFalse(bar.isPresent());
	}

	@Test
	public void unclosedQuotesThrows() {
		assertThrows(CliArgsParserException.class, () -> parser.parse("foo --bar=\"hello world"));
	}

	@Test
	public void unexpectedTokenAfterArgThrows() {
		assertThrows(CliArgsParserException.class, () -> parser.parse("foo --bar=123 biz"));
	}

}
