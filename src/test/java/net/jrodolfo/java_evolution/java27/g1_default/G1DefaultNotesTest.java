package net.jrodolfo.java_evolution.java27.g1_default;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class G1DefaultNotesTest {

	@Test
	void childJvmReportsG1AsTheDefaultCollector() throws Exception {
		G1DefaultNotes notes = new G1DefaultNotes();
		var result = notes.inspectDefaultCollector();
		assertThat(result.exitCode()).isEqualTo(0);
		assertThat(result.output()).containsPattern("(?m)^\\s*bool\\s+UseG1GC\\s+=\\s+true\\s+\\{product\\}\\s+\\{ergonomic\\}");
	}

	@Test
	void notesExplainDefaultCollectorChange() {
		G1DefaultNotes notes = new G1DefaultNotes();
		assertThat(notes.summary()).contains("G1").contains("default");
		assertThat(notes.projectDecision())
				.contains("deterministic JVM configuration")
				.contains("throughput consequences")
				.contains("not proved by this test");
	}
}
