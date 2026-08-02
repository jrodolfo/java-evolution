package net.jrodolfo.java_evolution.java15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HiddenClassesNotesTest {

	private final HiddenClassesNotes notes = new HiddenClassesNotes();

	@Test
	void notesExplainHiddenClassPurposeAndApi() {
		// When / Then
		assertThat(notes.purpose())
				.as("The notes should explain the framework-oriented purpose")
				.contains("framework")
				.contains("not discoverable by name");
		assertThat(notes.primaryApi())
				.as("The notes should identify the main hidden class API")
				.isEqualTo("MethodHandles.Lookup.defineHiddenClass");
		assertThat(notes.projectDecision())
				.as("The notes should explain why no bytecode demo is included")
				.contains("without bytecode generation")
				.contains("lightweight");
	}
}
