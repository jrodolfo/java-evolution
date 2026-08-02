package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UnsafeMemoryAccessDeprecationNotesTest {

	private final UnsafeMemoryAccessDeprecationNotes notes = new UnsafeMemoryAccessDeprecationNotes();

	@Test
	void notesPointUnsafeMemoryAccessTowardSupportedApis() {
		assertThat(notes.purpose())
				.as("The note should frame Unsafe memory access as a migration topic")
				.contains("unsupported unsafe memory-access methods");
		assertThat(notes.replacementDirection())
				.as("Unsafe memory access should point learners toward supported replacement APIs")
				.contains("Foreign Function and Memory API");
	}
}
