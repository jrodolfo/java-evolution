package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClassFileApiPreviewNotesTest {

	private final ClassFileApiPreviewNotes notes = new ClassFileApiPreviewNotes();

	@Test
	void notesExplainClassFileApiPreview() {
		assertThat(notes.purpose()).contains("class files");
		assertThat(notes.audience()).contains("tools").contains("frameworks");
		assertThat(notes.projectDecision()).contains("preview");
	}
}
