package net.jrodolfo.java_evolution.java07;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringSwitchExamplesTest {

	private final StringSwitchExamples examples = new StringSwitchExamples();

	@Test
	void stringSwitchClassifiesCommands() {
		assertThat(examples.classifyCommand("start"))
				.as("Java 7 switch statements can branch on String values")
				.isEqualTo("activates work");
		assertThat(examples.classifyCommand("pause"))
				.isEqualTo("halts work");
		assertThat(examples.classifyCommand("other"))
				.isEqualTo("unknown command");
	}

	@Test
	void stringSwitchStillRejectsNullSelector() {
		NullPointerException thrown = null;
		try {
			examples.classifyCommand(null);
		}
		catch (NullPointerException exception) {
			thrown = exception;
		}

		assertThat(thrown)
				.as("A Java 7 String switch selector still throws when the selector is null")
				.isNotNull();
	}
}
