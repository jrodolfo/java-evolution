package net.jrodolfo.java_evolution.java27.vector_api;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class VectorApiTwelfthIncubatorNotesTest {

	@Test
	void notesExplainWhyIncubatorContinuationIsNotDuplicated() {
		VectorApiTwelfthIncubatorNotes notes = new VectorApiTwelfthIncubatorNotes();
		assertThat(notes.summary()).contains("twelfth incubator").contains("SIMD");
		assertThat(notes.projectDecision()).contains("executable SIMD lesson");
	}
}
