package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AotClassLoadingNotesTest {

	private final AotClassLoadingNotes notes = new AotClassLoadingNotes();

	@Test
	void notesExplainStartupOrientedRuntimeWork() {
		assertThat(notes.purpose())
				.as("AOT class loading is a startup/runtime topic, not a language syntax feature")
				.contains("startup");
	}
}
