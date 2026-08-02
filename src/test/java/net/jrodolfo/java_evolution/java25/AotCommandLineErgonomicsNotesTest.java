package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AotCommandLineErgonomicsNotesTest {

	private final AotCommandLineErgonomicsNotes notes = new AotCommandLineErgonomicsNotes();

	@Test
	void notesFrameAotErgonomicsAsOperationalStartupWork() {
		assertThat(notes.purpose())
				.as("AOT ergonomics should be framed as operational startup work")
				.contains("ahead-of-time");
	}
}
