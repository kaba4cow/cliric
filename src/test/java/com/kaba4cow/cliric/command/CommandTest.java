package com.kaba4cow.cliric.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kaba4cow.cliric.CliricExecutor;
import com.kaba4cow.cliric.args.mapper.impl.BooleanArgMapper;
import com.kaba4cow.cliric.args.mapper.impl.IntegerArgMapper;
import com.kaba4cow.cliric.args.mapper.impl.StringArgMapper;
import com.kaba4cow.cliric.args.mapper.support.ArgMapperRegistrar;
import com.kaba4cow.cliric.error.UnknownCommandPathException;
import com.kaba4cow.cliric.error.UnsupportedArgTypeException;
import com.kaba4cow.cliric.executor.support.ExecutorRegistrar;

public class CommandTest {

	private static CliricExecutor context;

	@BeforeAll
	public static void setupContext() {
		context = new CliricExecutor();

		ArgMapperRegistrar argMapperRegistrar = context.getArgMapperRegistrar();
		argMapperRegistrar.registerArgMapper(new StringArgMapper());
		argMapperRegistrar.registerArgMapper(new BooleanArgMapper());
		argMapperRegistrar.registerArgMapper(new IntegerArgMapper());

		ExecutorRegistrar executorRegistrar = context.getExecutorRegistrar();
		executorRegistrar.registerExecutor(new SayCommand());
	}

	@Test
	public void sayHelloOutputs() {
		assertCommandOutputs("Hello, User!", "say hello --name=User");
	}

	@Test
	public void sayGoodbyeOutputs() {
		assertCommandOutputs("Goodbye, User.", "say goodbye --name=User");
	}

	@Test
	public void unknownCommandThrows() {
		assertCommandThrows(UnknownCommandPathException.class, "unknown command");
	}

	@Test
	public void unsupportedArgTypeThrows() {
		assertCommandThrows(UnsupportedArgTypeException.class, "say unsupported --value=value");
	}

	private static void assertCommandThrows(Class<? extends Throwable> throwable, String input) {
		assertThrows(throwable, () -> context.execute(input));
	}

	private static void assertCommandOutputs(String expected, String input) {
		assertEquals(expected, context.execute(input));
	}

}
