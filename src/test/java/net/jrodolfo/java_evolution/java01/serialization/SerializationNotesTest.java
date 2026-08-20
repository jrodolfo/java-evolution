package net.jrodolfo.java_evolution.java01.serialization;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SerializationNotesTest {

	private final SerializationNotes notes = new SerializationNotes();

	@Test
	void notesExplainSerializationPurposeApiAndModernCaution() {
		assertThat(notes.problemSolved()).contains("object graphs").contains("RMI");
		assertThat(notes.coreApi()).contains("Serializable").contains("ObjectOutputStream").contains("ObjectInputStream");
		assertThat(notes.modernCaution()).contains("untrusted data").contains("compatibility contracts");
	}
}
