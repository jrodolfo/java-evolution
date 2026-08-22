package net.jrodolfo.java_evolution.java26.aot_object_caching;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AotObjectCachingNotesTest {

	private final AotObjectCachingNotes notes = new AotObjectCachingNotes();

	@Test
	void notesExplainStartupRuntimeBehavior() {
		assertThat(notes.problem())
				.as("AOT object caching should be framed as repeated startup initialization work")
				.contains("startup")
				.contains("initialization");
		assertThat(notes.java26Idea())
				.as("Java 26 AOT object caching should mention initialized objects and any GC")
				.contains("initialized objects")
				.contains("any GC");
		assertThat(notes.projectDecision())
				.as("The project decision should mark this as runtime command-line behavior")
				.contains("runtime")
				.contains("command-line");
	}
}
