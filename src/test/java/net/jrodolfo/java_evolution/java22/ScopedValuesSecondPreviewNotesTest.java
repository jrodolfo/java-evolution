package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesSecondPreviewNotesTest {

	private final ScopedValuesSecondPreviewNotes notes = new ScopedValuesSecondPreviewNotes();

	@Test
	void notesExplainScopedValuesSecondPreview() {
		assertThat(notes.purpose()).contains("bounded dynamic scope");
		assertThat(notes.secondPreviewStatus()).contains("second preview").contains("Java 22");
	}
}
