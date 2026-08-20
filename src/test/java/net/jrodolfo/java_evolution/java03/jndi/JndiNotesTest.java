package net.jrodolfo.java_evolution.java03.jndi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JndiNotesTest {

	private final JndiNotes notes = new JndiNotes();

	@Test
	void notesExplainJndiPurposeConceptsAndProviderNeed() {
		assertThat(notes.problemSolved()).contains("named resources");
		assertThat(notes.coreConcepts()).contains("contexts").contains("lookups").contains("providers");
		assertThat(notes.repositoryDecision()).contains("directory provider").contains("explanatory");
	}
}
