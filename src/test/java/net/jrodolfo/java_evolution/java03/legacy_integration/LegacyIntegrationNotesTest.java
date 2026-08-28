package net.jrodolfo.java_evolution.java03.legacy_integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LegacyIntegrationNotesTest {

	private final LegacyIntegrationNotes notes = new LegacyIntegrationNotes();

	@Test
	void notesExplainLegacyIntegrationContextAndModernCaveat() {
		assertThat(notes.historicalContext()).contains("RMI/IIOP").contains("CORBA");
		assertThat(notes.modernCaveat()).contains("removed from the JDK").contains("explicit dependencies");
		assertThat(notes.repositoryDecision()).contains("JDK 26");
	}
}
