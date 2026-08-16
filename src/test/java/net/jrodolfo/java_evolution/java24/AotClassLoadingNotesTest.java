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
		assertThat(notes.before())
				.as("The note should name the startup work this feature targets")
				.contains("class loading")
				.contains("linking");
		assertThat(notes.after())
				.as("The note should explain that some startup work can be prepared earlier")
				.contains("before the application run");
		assertThat(notes.projectDecision())
				.as("The note should explain why there is no normal unit-test style example")
				.contains("runtime startup feature")
				.contains("unit-test");
	}
}
