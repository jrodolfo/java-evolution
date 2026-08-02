package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompactSourceFilesNotesTest {

	private final CompactSourceFilesNotes notes = new CompactSourceFilesNotes();

	@Test
	void notesExplainRemovingClassAndMainCeremony() {
		assertThat(notes.purpose())
				.as("Compact source files should be framed as removing class/main ceremony")
				.contains("without an explicit class");
		assertThat(notes.projectDecision())
				.as("The note should explain why this is documented rather than compiled in this package")
				.contains("source");
	}
}
