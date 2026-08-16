package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClassFileApiPreviewNotesTest {

	private final ClassFileApiPreviewNotes notes = new ClassFileApiPreviewNotes();

	@Test
	void notesExplainClassFileApiPreview() {
		String purpose = notes.purpose();
		String audience = notes.audience();
		String nextStep = notes.nextStep();
		String projectDecision = notes.projectDecision();

		assertThat(purpose)
				.as("The Class-File API preview should identify class files as the problem space")
				.contains("class files");
		assertThat(audience)
				.as("The note should identify the tool authors most likely to care")
				.contains("tools")
				.contains("frameworks");
		assertThat(nextStep)
				.as("The Java 22 preview note should point to the final Java 24 executable module")
				.contains("Java 24")
				.contains("class_file");
		assertThat(projectDecision)
				.as("The note should explain why this package avoids the preview API shape")
				.contains("Java 22")
				.contains("preview");
	}
}
