package net.jrodolfo.java_evolution.java23.scoped_values;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesThirdPreviewNotesTest {

	private final ScopedValuesThirdPreviewNotes notes = new ScopedValuesThirdPreviewNotes();

	@Test
	void notesExplainBoundedContextInsteadOfGlobalMutableState() {
		String problemSolved = notes.problemSolved();
		String threadLocalContrast = notes.threadLocalContrast();
		String java22Connection = notes.java22Connection();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("Scoped values should be explained as bounded context, not global mutable state")
				.contains("immutable contextual data")
				.contains("call chains")
				.contains("bounded scope");
		assertThat(threadLocalContrast)
				.as("The note should contrast scoped values with broad mutable ThreadLocal state")
				.contains("ThreadLocal")
				.contains("stale-context")
				.contains("cleanup");
		assertThat(java22Connection)
				.as("The bridge note should connect Java 23 back to the Java 22 second preview")
				.contains("Java 22")
				.contains("second preview");
		assertThat(status)
				.as("The Java 23 note should document the third preview before Java 25 finalization")
				.contains("third preview in Java 23")
				.contains("fourth preview in Java 24")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The bridge note should point learners to the final Java 25 scoped-values module")
				.contains("Java 25")
				.contains("scoped_values");
	}
}
