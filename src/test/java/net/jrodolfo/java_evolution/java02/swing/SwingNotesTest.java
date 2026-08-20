package net.jrodolfo.java_evolution.java02.swing;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SwingNotesTest {

	private final SwingNotes notes = new SwingNotes();

	@Test
	void notesExplainSwingPurposeComponentsAndHeadlessDecision() {
		assertThat(notes.problemSolved()).contains("AWT").contains("lightweight GUI components");
		assertThat(notes.components()).contains("JFrame").contains("JButton").contains("JTable");
		assertThat(notes.repositoryDecision()).contains("headless").contains("explanatory");
	}
}
