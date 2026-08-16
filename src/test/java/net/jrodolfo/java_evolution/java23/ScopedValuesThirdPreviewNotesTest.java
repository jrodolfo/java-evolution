package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesThirdPreviewNotesTest {

	private final ScopedValuesThirdPreviewNotes notes = new ScopedValuesThirdPreviewNotes();

	@Test
	void notesExplainBoundedContextInsteadOfGlobalMutableState() {
		String purpose = notes.purpose();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("Scoped values should be explained as bounded context, not global mutable state")
				.contains("immutable data")
				.contains("bounded scope");
		assertThat(status)
				.as("The Java 23 note should document the third preview before Java 25 finalization")
				.contains("third preview in Java 23")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The bridge note should point learners to the final Java 25 scoped-values module")
				.contains("Java 25")
				.contains("scoped_values");
	}
}
