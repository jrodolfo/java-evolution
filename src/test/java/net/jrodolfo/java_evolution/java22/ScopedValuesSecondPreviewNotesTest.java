package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesSecondPreviewNotesTest {

	private final ScopedValuesSecondPreviewNotes notes = new ScopedValuesSecondPreviewNotes();

	@Test
	void notesExplainScopedValuesSecondPreview() {
		String purpose = notes.purpose();
		String nextStep = notes.nextStep();
		String status = notes.secondPreviewStatus();

		assertThat(purpose)
				.as("Scoped values should be explained as bounded context")
				.contains("immutable values")
				.contains("bounded dynamic scope");
		assertThat(nextStep)
				.as("The Java 22 bridge note should point to the final Java 25 scoped-values module")
				.contains("Java 25")
				.contains("scoped_values");
		assertThat(status)
				.as("The note should document Java 22 as the second preview")
				.contains("second preview")
				.contains("Java 22");
	}
}
