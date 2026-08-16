package net.jrodolfo.java_evolution.java22.class_file_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClassFileApiPreviewNotesTest {

	private final ClassFileApiPreviewNotes notes = new ClassFileApiPreviewNotes();

	@Test
	void notesExplainWhyTheClassFileApiWasPreviewed() {
		String problemSolved = notes.problemSolved();
		String audience = notes.audience();
		String platformReason = notes.platformReason();
		String previewStatus = notes.previewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The Class-File API preview should identify class files as the problem space")
				.contains("parse")
				.contains("generate")
				.contains("transform")
				.contains("class files");
		assertThat(audience)
				.as("The note should identify the tool authors most likely to care")
				.contains("frameworks")
				.contains("compilers")
				.contains("agents")
				.contains("analysis tools");
		assertThat(platformReason)
				.as("The note should explain why this API belongs near the class-file format")
				.contains("standard API")
				.contains("evolve")
				.contains("class-file format");
		assertThat(previewStatus)
				.as("The Java 22 note should be explicit that the API was still preview")
				.contains("preview")
				.contains("Java 22")
				.contains("final in Java 24");
		assertThat(nextStep)
				.as("The preview module should send learners to the final runnable Java 24 module")
				.contains("Java 24")
				.contains("class_file")
				.contains("final executable");
	}
}
