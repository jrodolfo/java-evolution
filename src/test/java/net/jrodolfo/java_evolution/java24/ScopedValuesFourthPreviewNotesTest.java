package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesFourthPreviewNotesTest {

	private final ScopedValuesFourthPreviewNotes notes = new ScopedValuesFourthPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		String featureGoal = notes.featureGoal();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(featureGoal)
				.as("The note should explain the context propagation goal")
				.contains("immutable contextual data")
				.contains("bounded dynamic scope");
		assertThat(status)
				.as("Scoped values should point to Java 25 finalization")
				.contains("fourth preview in Java 24")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The Java 24 bridge note should point to the final Java 25 scoped-values module")
				.contains("Java 25")
				.contains("scoped_values");
	}
}
