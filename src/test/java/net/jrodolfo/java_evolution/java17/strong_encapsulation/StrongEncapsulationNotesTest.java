package net.jrodolfo.java_evolution.java17.strong_encapsulation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StrongEncapsulationNotesTest {

	private final StrongEncapsulationNotes notes = new StrongEncapsulationNotes();

	@Test
	void notesExplainStrongEncapsulationImpact() {
		// When / Then
		assertThat(notes.before())
				.as("The notes should explain the unsupported internal-API habit")
				.contains("reflection")
				.contains("internal JDK implementation details");
		assertThat(notes.after())
				.as("The notes should contrast internals with documented public APIs")
				.contains("documented public APIs")
				.contains("platform contracts");
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
