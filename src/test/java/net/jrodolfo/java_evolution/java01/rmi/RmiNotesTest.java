package net.jrodolfo.java_evolution.java01.rmi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RmiNotesTest {

	private final RmiNotes notes = new RmiNotes();

	@Test
	void notesExplainRmiPurposeConceptsAndRepositoryDecision() {
		assertThat(notes.problemSolved()).contains("another JVM");
		assertThat(notes.coreConcepts()).contains("remote interfaces").contains("registries").contains("RemoteException");
		assertThat(notes.repositoryDecision()).contains("registry").contains("network binding");
	}
}
