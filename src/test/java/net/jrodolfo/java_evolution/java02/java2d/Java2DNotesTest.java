package net.jrodolfo.java_evolution.java02.java2d;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Java2DNotesTest {

	private final Java2DNotes notes = new Java2DNotes();

	@Test
	void notesExplainJava2DPurposeApisAndVisualDecision() {
		assertThat(notes.problemSolved()).contains("drawing").contains("rendering");
		assertThat(notes.apiAreas()).contains("Graphics2D").contains("transforms").contains("rendering hints");
		assertThat(notes.repositoryDecision()).contains("visual").contains("explanatory");
	}
}
