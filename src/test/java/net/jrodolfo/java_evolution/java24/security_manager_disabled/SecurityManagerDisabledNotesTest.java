package net.jrodolfo.java_evolution.java24.security_manager_disabled;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SecurityManagerDisabledNotesTest {

	private final SecurityManagerDisabledNotes notes = new SecurityManagerDisabledNotes();

	@Test
	void notesPointToDeploymentLevelIsolation() {
		String originalGoal = notes.originalGoal();
		String oldModel = notes.oldModel();
		String permissionExamples = notes.permissionExamples();
		String java24Impact = notes.java24Impact();
		String modernIsolationAdvice = notes.modernIsolationAdvice();

		assertThat(originalGoal)
				.as("The old feature existed to restrict less-trusted code")
				.contains("less-trusted code")
				.contains("sensitive actions");
		assertThat(oldModel)
				.as("The old Security Manager model was an in-process JVM sandbox")
				.contains("same JVM")
				.contains("sandbox");
		assertThat(permissionExamples)
				.as("The note should name sensitive actions that historically triggered permission checks")
				.contains("file access")
				.contains("network access")
				.contains("permissions");
		assertThat(java24Impact)
				.as("Java 24 permanently disabled the Security Manager sandbox model")
				.contains("permanently disabled")
				.contains("sandbox");
		assertThat(modernIsolationAdvice)
				.as("The note should point to modern isolation approaches")
				.contains("operating system")
				.contains("container")
				.contains("process")
				.contains("deployment");
	}
}
