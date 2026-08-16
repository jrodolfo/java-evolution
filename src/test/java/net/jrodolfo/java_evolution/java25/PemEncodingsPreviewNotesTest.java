package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PemEncodingsPreviewNotesTest {

	private final PemEncodingsPreviewNotes notes = new PemEncodingsPreviewNotes();

	@Test
	void notesExplainCryptographicTextEncodingSupport() {
		assertThat(notes.purpose())
				.as("PEM support should be documented as cryptographic text encoding support")
				.contains("Privacy-Enhanced Mail")
				.contains("cryptographic objects");
		assertThat(notes.formatShape())
				.as("PEM notes should explain the recognizable BEGIN/END text envelope")
				.contains("BEGIN")
				.contains("END")
				.contains("Base64");
		assertThat(notes.status())
				.as("PEM support should stay marked as preview in Java 25")
				.contains("preview")
				.contains("Java 25");
	}
}
