package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GenerationalShenandoahNotesTest {

	private final GenerationalShenandoahNotes notes = new GenerationalShenandoahNotes();

	@Test
	void notesDocumentShenandoahGcBehavior() {
		assertThat(notes.purpose())
				.as("Generational Shenandoah should be documented as GC behavior")
				.contains("Shenandoah");
	}
}
