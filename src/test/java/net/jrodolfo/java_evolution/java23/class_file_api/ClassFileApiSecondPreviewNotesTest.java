package net.jrodolfo.java_evolution.java23.class_file_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClassFileApiSecondPreviewNotesTest {

	private final ClassFileApiSecondPreviewNotes notes = new ClassFileApiSecondPreviewNotes();

	@Test
	void notesIdentifyClassFileToolingAsTheProblemSpace() {
		String problemSolved = notes.problemSolved();
		String platformReason = notes.platformReason();
		String java22Connection = notes.java22Connection();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The Class-File API note should identify bytecode/class-file tooling as the problem space")
				.contains("standard API")
				.contains("parse")
				.contains("generate")
				.contains("transform")
				.contains("class files");
		assertThat(platformReason)
				.as("The note should explain why the API belongs near the JDK class-file format")
				.contains("standard JDK model")
				.contains("evolve")
				.contains("class-file format");
		assertThat(java22Connection)
				.as("The bridge note should connect Java 23 back to the Java 22 first preview")
				.contains("Java 22")
				.contains("first preview");
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
