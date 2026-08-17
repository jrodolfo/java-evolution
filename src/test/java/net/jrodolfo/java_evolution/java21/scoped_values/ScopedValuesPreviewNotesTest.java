package net.jrodolfo.java_evolution.java21.scoped_values;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesPreviewNotesTest {

	private final ScopedValuesPreviewNotes notes = new ScopedValuesPreviewNotes();

	@Test
	void notesExplainScopedValuesPreviewStatus() {
		String problemSolved = notes.problemSolved();
		String threadLocalProblem = notes.threadLocalProblem();
		String previewIdea = notes.previewIdea();
		String status = notes.firstPreviewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("Scoped values should be introduced through the contextual-data problem")
				.contains("contextual data")
				.contains("request IDs")
				.contains("call chain")
				.contains("without passing it through every method");
		assertThat(threadLocalProblem)
				.as("The first-preview note should explain the ThreadLocal motivation")
				.contains("ThreadLocal")
				.contains("mutable")
				.contains("cleaned up")
				.contains("stale context");
		assertThat(previewIdea)
				.as("The note should preserve the scoped-values mental model")
				.contains("immutable contextual data")
				.contains("bounded dynamic scope");
		assertThat(status)
				.as("The notes should identify Java 21 as the first preview release for scoped values")
				.contains("first previewed")
				.contains("Java 21");
		assertThat(nextStep)
				.as("The first-preview bridge should point to the final Java 25 learning module")
				.contains("Java 25")
				.contains("scoped_values")
				.contains("final runnable");
	}
}
