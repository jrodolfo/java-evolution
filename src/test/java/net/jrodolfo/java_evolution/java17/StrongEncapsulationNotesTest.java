package net.jrodolfo.java_evolution.java17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StrongEncapsulationNotesTest {

	private final StrongEncapsulationNotes notes = new StrongEncapsulationNotes();

	@Test
	void notesExplainStrongEncapsulationImpact() {
		// When / Then
		assertThat(notes.impact())
				.as("The notes should explain the move away from internal JDK packages")
				.contains("public APIs")
				.contains("internal JDK packages");
		assertThat(notes.migrationAdvice())
				.as("The notes should include practical migration advice")
				.contains("standard APIs");
		assertThat(notes.projectDecision())
				.as("The notes should explain why no illegal-access demo is included")
				.contains("runtime encapsulation")
				.contains("illegal access");
	}
}
