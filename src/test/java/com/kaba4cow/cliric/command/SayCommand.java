package com.kaba4cow.cliric.command;

import com.kaba4cow.cliric.annotation.Command;
import com.kaba4cow.cliric.annotation.Param;

@Command("say")
public class SayCommand {

	@Command("hello")
	public String sayHello(@Param("name") String name) {
		return String.format("Hello, %s!", name);
	}

	@Command("goodbye")
	public String sayGoodbye(@Param("name") String name) {
		return String.format("Goodbye, %s.", name);
	}

	@Command("unsupported")
	public String unsupported(@Param("value") Thread value) {
		return value.toString();
	}

}
