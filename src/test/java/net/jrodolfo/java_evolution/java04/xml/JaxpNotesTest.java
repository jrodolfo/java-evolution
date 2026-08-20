package net.jrodolfo.java_evolution.java04.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JaxpNotesTest {

	private final JaxpNotes notes = new JaxpNotes();

	@Test
	void notesExplainJaxpPurposeAreasAndHistoricalContext() {
		assertThat(notes.problemSolved()).contains("XML parsing").contains("transformation");
		assertThat(notes.apiAreas()).contains("DOM").contains("SAX").contains("XSLT");
		assertThat(notes.historicalContext()).contains("enterprise integration");
	}
}
