package net.jrodolfo.java_evolution.java19;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ForeignFunctionMemoryApiPreviewNotesTest {

	private final ForeignFunctionMemoryApiPreviewNotes notes = new ForeignFunctionMemoryApiPreviewNotes();

	@Test
	void notesExplainForeignFunctionAndMemoryApi() {
		String purpose = notes.purpose();
		String alternativeTo = notes.alternativeTo();
		String commonUseCase = notes.commonUseCase();
		String projectDecision = notes.projectDecision();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("The notes should explain native calls and off-heap memory access")
				.contains("native functions")
				.contains("outside the Java heap");
		assertThat(alternativeTo)
				.as("The notes should identify the older native integration technology")
				.isEqualTo("JNI");
		assertThat(commonUseCase)
				.as("The notes should explain why Java code may need native integration")
				.contains("operating-system functions")
				.contains("C");
		assertThat(projectDecision)
				.as("The notes should explain why native code is avoided")
				.contains("preview")
				.contains("native code");
		assertThat(nextStep)
				.as("The Java 19 notes should point to the final API example")
				.contains("Java 22")
				.contains("foreign_function")
				.contains("final API");
	}
}
