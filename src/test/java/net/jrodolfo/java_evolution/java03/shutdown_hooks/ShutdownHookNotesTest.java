package net.jrodolfo.java_evolution.java03.shutdown_hooks;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ShutdownHookNotesTest {

	private final ShutdownHookNotes notes = new ShutdownHookNotes();

	@Test
	void notesExplainShutdownHookPurposeApiAndTestDecision() {
		assertThat(notes.problemSolved()).contains("JVM shutdown");
		assertThat(notes.apiShape()).contains("Runtime.addShutdownHook").contains("Thread");
		assertThat(notes.repositoryDecision()).contains("terminate a JVM process").contains("explanatory");
	}
}
