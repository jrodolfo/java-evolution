package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StatementsBeforeSuperPreviewNotesTest {

	private final StatementsBeforeSuperPreviewNotes notes = new StatementsBeforeSuperPreviewNotes();

	@Test
	void notesExplainStatementsBeforeSuperPreview() {
		assertThat(notes.purpose()).contains("before calling").contains("superclass constructor");
		assertThat(notes.limitation()).contains("cannot use the instance");
		assertThat(notes.projectDecision()).contains("preview-flag");
	}
}
