package net.jrodolfo.java_evolution.java19;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ForeignFunctionMemoryApiPreviewNotesTest {

	private final ForeignFunctionMemoryApiPreviewNotes notes = new ForeignFunctionMemoryApiPreviewNotes();

	@Test
	void notesExplainForeignFunctionAndMemoryApi() {
		// When / Then
		assertThat(notes.purpose())
				.as("The notes should explain native calls and off-heap memory access")
				.contains("native functions")
				.contains("outside the Java heap");
		assertThat(notes.alternativeTo())
				.as("The notes should identify the older native integration technology")
				.isEqualTo("JNI");
		assertThat(notes.projectDecision())
				.as("The notes should explain why native code is avoided")
				.contains("preview")
				.contains("native code");
	}
}
