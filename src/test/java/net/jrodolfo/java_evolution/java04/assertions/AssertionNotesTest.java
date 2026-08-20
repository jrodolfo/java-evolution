package net.jrodolfo.java_evolution.java04.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AssertionNotesTest {

	private final AssertionNotes notes = new AssertionNotes();

	@Test
	void notesExplainAssertionPurposeEnablementAndUse() {
		assertThat(notes.problemSolved()).contains("internal assumptions");
		assertThat(notes.enablement()).contains("-ea").contains("-enableassertions");
		assertThat(notes.appropriateUse()).contains("internal invariants").contains("not user input validation");
	}
}
