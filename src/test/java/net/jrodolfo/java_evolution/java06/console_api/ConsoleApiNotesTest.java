package net.jrodolfo.java_evolution.java06.console_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ConsoleApiNotesTest {

	private final ConsoleApiNotes notes = new ConsoleApiNotes();

	@Test
	void notesExplainConsolePurposeAndNullCaveat() {
		assertThat(notes.purpose())
				.as("Console notes should explain interactive input and prompting")
				.contains("java.io.Console")
				.contains("line input")
				.contains("password reading");

		assertThat(notes.nullConsoleCaveat())
				.as("Console examples must explain why System.console can be absent")
				.contains("System.console()")
				.contains("null")
				.contains("Maven")
				.contains("CI");
	}

	@Test
	void notesExplainPasswordHandling() {
		assertThat(notes.passwordGuidance())
				.as("Password guidance should include non-echoing input and clearing char arrays")
				.contains("readPassword")
				.contains("char[]")
				.contains("clear");
	}
}
