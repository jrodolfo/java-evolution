package net.jrodolfo.java_evolution.java25.compact_object_headers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompactObjectHeadersNotesTest {

	private final CompactObjectHeadersNotes notes = new CompactObjectHeadersNotes();

	@Test
	void notesExplainObjectHeadersAndMemoryFootprintGoal() {
		assertThat(notes.problem())
				.as("Compact object headers should explain that objects include JVM metadata beyond visible fields")
				.contains("JVM metadata")
				.contains("payload fields");
		assertThat(notes.purpose())
				.as("Compact object headers should be explained as a memory-footprint feature")
				.contains("object header")
				.contains("memory footprint")
				.contains("object-heavy applications");
	}

	@Test
	void notesExplainJava25StatusAndRuntimeOption() {
		assertThat(notes.java25Status())
				.as("Java 25 should be described as the product-feature transition")
				.contains("experimental")
				.contains("product feature");
		assertThat(notes.option())
				.as("The notes should identify the JVM option and avoid implying it is the default")
				.contains("-XX:+UseCompactObjectHeaders")
				.contains("not the default");
		assertThat(notes.projectDecision())
				.as("The notes should explain why this is not a normal source-code unit-test example")
				.contains("JVM flags")
				.contains("heap measurement")
				.contains("object-layout tooling");
	}
}
