package net.jrodolfo.java_evolution.java22.scoped_values;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesSecondPreviewNotesTest {

	private final ScopedValuesSecondPreviewNotes notes = new ScopedValuesSecondPreviewNotes();

	@Test
	void notesExplainScopedValuesSecondPreview() {
		String problemSolved = notes.problemSolved();
		String threadLocalMotivation = notes.threadLocalMotivation();
		String boundedDynamicScope = notes.boundedDynamicScope();
		String nextStep = notes.nextStep();
		String status = notes.secondPreviewStatus();

		assertThat(problemSolved)
				.as("Scoped values should be explained as contextual data across a call chain")
				.contains("immutable contextual data")
				.contains("call chain")
				.contains("without passing it through every method");
		assertThat(threadLocalMotivation)
				.as("The note should explain the older ThreadLocal problem scoped values improve")
				.contains("ThreadLocal")
				.contains("cleanup")
				.contains("stale-context");
		assertThat(boundedDynamicScope)
				.as("The note should explain bounded dynamic scope without assuming the term is already known")
				.contains("visible while")
				.contains("operation runs")
				.contains("disappears");
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
