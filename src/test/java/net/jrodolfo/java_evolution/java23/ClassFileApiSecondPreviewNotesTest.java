package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClassFileApiSecondPreviewNotesTest {

	private final ClassFileApiSecondPreviewNotes notes = new ClassFileApiSecondPreviewNotes();

	@Test
	void notesIdentifyClassFileToolingAsTheProblemSpace() {
		String purpose = notes.purpose();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("The Class-File API note should identify bytecode/class-file tooling as the problem space")
				.contains("class files");
		assertThat(status)
				.as("The Java 23 note should document the second preview before Java 24 finalization")
				.contains("second preview in Java 23")
				.contains("final in Java 24");
		assertThat(nextStep)
				.as("The bridge note should point learners to the final Java 24 executable module")
				.contains("Java 24")
				.contains("class_file");
	}
}
