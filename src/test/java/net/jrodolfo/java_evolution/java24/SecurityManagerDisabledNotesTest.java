package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SecurityManagerDisabledNotesTest {

	private final SecurityManagerDisabledNotes notes = new SecurityManagerDisabledNotes();

	@Test
	void notesPointToDeploymentLevelIsolation() {
		assertThat(notes.impact())
				.as("The test should document that Security Manager is no longer an application sandbox")
				.contains("sandbox");
		assertThat(notes.migrationAdvice())
				.as("The note should point to modern isolation approaches")
				.contains("deployment");
	}
}
