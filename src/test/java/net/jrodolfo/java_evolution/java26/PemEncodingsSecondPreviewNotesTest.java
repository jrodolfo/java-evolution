package net.jrodolfo.java_evolution.java26;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PemEncodingsSecondPreviewNotesTest {

	private final PemEncodingsSecondPreviewNotes notes = new PemEncodingsSecondPreviewNotes();

	@Test
	void notesExplainSecondPreviewPemSupport() {
		assertThat(notes.purpose())
				.as("PEM notes should explain standardized cryptographic text encoding support")
				.contains("cryptographic objects")
				.contains("PEM");
		assertThat(notes.status())
				.as("PEM support should be marked as second preview in Java 26")
				.contains("second preview")
				.contains("Java 26")
				.contains("Java 25");
		assertThat(notes.projectDecision())
				.as("Preview API should not be compiled as a normal JDK 25 example")
				.contains("preview API")
				.contains("JDK 25");
	}
}
