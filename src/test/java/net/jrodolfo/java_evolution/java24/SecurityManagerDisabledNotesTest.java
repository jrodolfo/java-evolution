package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SecurityManagerDisabledNotesTest {

	private final SecurityManagerDisabledNotes notes = new SecurityManagerDisabledNotes();

	@Test
	void notesPointToDeploymentLevelIsolation() {
		String oldModel = notes.oldModel();
		String impact = notes.impact();
		String migrationAdvice = notes.migrationAdvice();

		assertThat(oldModel)
				.as("The old Security Manager model was an in-process JVM sandbox")
				.contains("same JVM")
				.contains("sandbox");
		assertThat(impact)
				.as("Java 24 permanently disabled the Security Manager sandbox model")
				.contains("permanently disabled")
				.contains("sandbox");
		assertThat(migrationAdvice)
				.as("The note should point to modern isolation approaches")
				.contains("operating system")
				.contains("container")
				.contains("deployment");
	}
}
