package net.jrodolfo.java_evolution.java25.stable_values;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StableValuesPreviewNotesTest {

	private final StableValuesPreviewNotes notes = new StableValuesPreviewNotes();

	@Test
	void notesExplainLazyButImmutableProblem() {
		assertThat(notes.problem())
				.as("Stable Values should be introduced as lazy initialization that becomes immutable")
				.contains("initialized lazily")
				.contains("immutable");
	}

	@Test
	void notesExplainCommonAlternativesAndPreviewStatus() {
		assertThat(notes.commonAlternative())
				.as("The notes should explain common pre-Java-25 lazy-initialization approaches")
				.contains("nullable fields")
				.contains("double-checked locking");
		assertThat(notes.java25Idea())
				.as("The notes should explain deferred immutability and JVM optimization")
				.contains("deferred immutability")
				.contains("JVM");
		assertThat(notes.projectDecision())
				.as("The notes should explain why this preview API is not compiled in the normal build")
				.contains("--enable-preview");
	}
}
