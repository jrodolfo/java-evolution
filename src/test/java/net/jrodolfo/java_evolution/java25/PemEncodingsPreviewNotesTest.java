package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PemEncodingsPreviewNotesTest {

	private final PemEncodingsPreviewNotes notes = new PemEncodingsPreviewNotes();

	@Test
	void notesExplainCryptographicTextEncodingSupport() {
		assertThat(notes.purpose())
				.as("PEM support should be documented as cryptographic text encoding support")
				.contains("PEM");
	}
}
