package net.jrodolfo.java_evolution.java24.scoped_values;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScopedValuesFourthPreviewNotesTest {

	private final ScopedValuesFourthPreviewNotes notes = new ScopedValuesFourthPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		String problemSolved = notes.problemSolved();
		String oldApproach = notes.oldApproach();
		String scopedValueModel = notes.scopedValueModel();
		String previewStep = notes.previewStep();
		String status = notes.fourthPreviewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The note should explain why contextual data is useful")
				.contains("contextual data")
				.contains("user")
				.contains("request ID")
				.contains("noisy parameters");
		assertThat(oldApproach)
				.as("The note should name ThreadLocal and its cleanup risk")
				.contains("ThreadLocal")
				.contains("mutable values")
				.contains("cleanup")
				.contains("leak data");
		assertThat(scopedValueModel)
				.as("The note should explain immutable context inside a bounded dynamic scope")
				.contains("immutable binding")
				.contains("bounded dynamic scope");
		assertThat(previewStep)
				.as("The note should explain Java 24's place in the feature history")
				.contains("fourth preview")
				.contains("Java 23")
				.contains("Java 25");
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
