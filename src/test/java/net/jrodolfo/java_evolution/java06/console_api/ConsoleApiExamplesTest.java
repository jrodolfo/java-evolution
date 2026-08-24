package net.jrodolfo.java_evolution.java06.console_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ConsoleApiExamplesTest {

	private final ConsoleApiExamples examples = new ConsoleApiExamples();

	@Test
	void currentProcessCanReportConsoleAvailabilityWithoutRequiringATerminal() {
		ConsoleApiExamples.ConsoleAvailability availability = examples.currentConsoleAvailability();

		assertThat(availability.message())
				.as("System.console() may be present in a terminal and absent under Maven, IDEs, CI, or redirected processes")
				.isNotBlank();
	}

	@Test
	void nullConsoleUsesSafeUnavailablePath() {
		ConsoleApiExamples.ConsoleAvailability availability = examples.consoleAvailability(null);

		assertThat(availability.available())
				.as("code using System.console() must handle the normal null result")
				.isFalse();
		assertThat(availability.message())
				.as("the unavailable result should explain the process-boundary condition")
				.contains("System.console()")
				.contains("not available");
		assertThat(examples.greetUser(null))
				.as("application code should have a deterministic fallback when no console is attached")
				.isEqualTo("console unavailable");
	}

	@Test
	void fakeConsoleMakesLineInputAndPromptingTestable() {
		FakeConsoleSession console = new FakeConsoleSession("Rodolfo", new char[] { 's', 'e', 'c', 'r', 'e', 't' });

		String greeting = examples.greetUser(console);

		assertThat(greeting)
				.as("command-line behavior can be tested once Console is isolated at the edge")
				.isEqualTo("hello, Rodolfo");
		assertThat(console.output())
				.as("Console supports formatted output alongside line input")
				.contains("hello, Rodolfo");
		assertThat(console.lastLinePrompt())
				.as("Console.readLine can include formatted prompts")
				.isEqualTo("name: ");
	}

	@Test
	void passwordInputUsesCharArrayThatCanBeCleared() {
		char[] password = new char[] { 's', 'e', 'c', 'r', 'e', 't' };
		FakeConsoleSession console = new FakeConsoleSession("ignored", password);

		int length = examples.readPasswordLengthAndClear(console);

		assertThat(length)
				.as("the example uses the password before clearing it")
				.isEqualTo(6);
		assertThat(password)
				.as("char[] secrets can be overwritten after use")
				.containsOnly('\0');
		assertThat(console.lastPasswordPrompt())
				.as("Console.readPassword supports a prompt and avoids echoing in a real terminal")
				.isEqualTo("password: ");
	}

	@Test
	void passwordGuidanceMentionsTheJavaSixSecurityHabit() {
		assertThat(examples.passwordGuidance())
				.as("the API lesson is to prefer readPassword char arrays over immutable String secrets")
				.contains("Console.readPassword")
				.contains("char[]")
				.contains("clear");
	}

	private static final class FakeConsoleSession implements ConsoleApiExamples.ConsoleSession {

		private final String line;
		private final char[] password;
		private final StringBuilder output = new StringBuilder();
		private String lastLinePrompt;
		private String lastPasswordPrompt;

		private FakeConsoleSession(String line, char[] password) {
			this.line = line;
			this.password = password;
		}

		@Override
		public String readLine(String prompt, Object... args) {
			lastLinePrompt = String.format(prompt, args);
			return line;
		}

		@Override
		public char[] readPassword(String prompt, Object... args) {
			lastPasswordPrompt = String.format(prompt, args);
			return password;
		}

		@Override
		public void printf(String format, Object... args) {
			output.append(String.format(format, args));
		}

		private String output() {
			return output.toString();
		}

		private String lastLinePrompt() {
			return lastLinePrompt;
		}

		private String lastPasswordPrompt() {
			return lastPasswordPrompt;
		}
	}
}
