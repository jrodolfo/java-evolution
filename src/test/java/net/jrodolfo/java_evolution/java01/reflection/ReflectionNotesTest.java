package net.jrodolfo.java_evolution.java01.reflection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ReflectionNotesTest {

	private final ReflectionNotes notes = new ReflectionNotes();

	@Test
	void notesExplainReflectionPurposeApiAndTradeoff() {
		assertThat(notes.problemSolved()).contains("tools").contains("compile time");
		assertThat(notes.coreApi()).contains("java.lang.Class").contains("java.lang.reflect");
		assertThat(notes.tradeoff()).contains("runtime discovery").contains("compile-time checking");
	}
}
