package net.jrodolfo.java_evolution.java01.javabeans;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JavaBeansNotesTest {

	private final JavaBeansNotes notes = new JavaBeansNotes();

	@Test
	void notesExplainJavaBeansPurposeConventionsAndInfluence() {
		assertThat(notes.problemSolved()).contains("component properties").contains("events");
		assertThat(notes.conventions()).contains("getter").contains("setter").contains("introspection");
		assertThat(notes.influence()).contains("property conventions").contains("frameworks");
	}
}
