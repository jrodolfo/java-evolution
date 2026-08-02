package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesThirdPreviewNotesTest {

	private final ScopedValuesThirdPreviewNotes notes = new ScopedValuesThirdPreviewNotes();

	@Test
	void notesExplainBoundedContextInsteadOfGlobalMutableState() {
		assertThat(notes.purpose())
				.as("Scoped values should be explained as bounded context, not global mutable state")
				.contains("bounded scope");
		assertThat(notes.status())
				.as("The note should clarify that Java 23 scoped values were still preview")
				.contains("preview");
	}
}
