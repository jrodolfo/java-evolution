package net.jrodolfo.java_evolution.java26.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StructuredConcurrencySixthPreviewNotesTest {

	private final StructuredConcurrencySixthPreviewNotes notes = new StructuredConcurrencySixthPreviewNotes();

	@Test
	void notesExplainScopedConcurrentWorkAndPreviewStatus() {
		assertThat(notes.problem())
				.as("Structured concurrency should be motivated by lifetime, failure, and cancellation problems")
				.contains("outlive")
				.contains("failures")
				.contains("cancellation");
		assertThat(notes.idea())
				.as("Structured concurrency should be described as a scoped unit of work")
				.contains("scoped")
				.contains("unit of work");
		assertThat(notes.status())
				.as("Structured concurrency should be marked as sixth preview in Java 26")
				.contains("sixth preview")
				.contains("Java 26");
	}
}
