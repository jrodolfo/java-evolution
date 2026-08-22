package net.jrodolfo.java_evolution.java26.lazy_constants;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LazyConstantsSecondPreviewNotesTest {

	private final LazyConstantsSecondPreviewNotes notes = new LazyConstantsSecondPreviewNotes();

	@Test
	void notesExplainDeferredConstantLikeInitialization() {
		assertThat(notes.problem())
				.as("Lazy Constants should be introduced as lazy initialization")
				.contains("expensive")
				.contains("first needed");
		assertThat(notes.comparisonWithFinalFields())
				.as("The notes should distinguish final field timing from lazy constant timing")
				.contains("final fields")
				.contains("defer initialization")
				.contains("constant-like");
		assertThat(notes.status())
				.as("Lazy Constants should be marked as second preview after Stable Values")
				.contains("second preview")
				.contains("Stable Values");
	}
}
