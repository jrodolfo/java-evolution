package net.jrodolfo.java_evolution.java27.g1_default;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class G1DefaultNotesTest {

	@Test
	void notesExplainDefaultCollectorChange() {
		G1DefaultNotes notes = new G1DefaultNotes();
		assertThat(notes.summary()).contains("G1").contains("default");
		assertThat(notes.projectDecision()).contains("workloads").contains("unit test");
	}
}
