package net.jrodolfo.java_evolution.java23.unsafe_memory_access_deprecation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UnsafeMemoryAccessDeprecationNotesTest {

	private final UnsafeMemoryAccessDeprecationNotes notes = new UnsafeMemoryAccessDeprecationNotes();

	@Test
	void notesPointUnsafeMemoryAccessTowardSupportedApis() {
		String oldUseCase = notes.oldUseCase();
		String purpose = notes.purpose();
		String risk = notes.risk();
		String deprecationMeaning = notes.deprecationMeaning();
		String replacementDirection = notes.replacementDirection();

		assertThat(oldUseCase)
				.as("The note should explain why advanced libraries historically used Unsafe")
				.contains("sun.misc.Unsafe")
				.contains("low-level memory access");
		assertThat(purpose)
				.as("The note should frame Unsafe memory access as a migration topic")
				.contains("unsupported unsafe memory-access methods");
		assertThat(risk)
				.as("The note should explain the compatibility risk of depending on JDK internals")
				.contains("JDK internals")
				.contains("compatibility");
		assertThat(deprecationMeaning)
				.as("The note should explain why deprecated for removal is stronger than ordinary deprecation")
				.contains("deprecated for removal")
				.contains("removed later");
		assertThat(replacementDirection)
				.as("Unsafe memory access should point learners toward supported replacement APIs")
				.contains("VarHandle")
				.contains("Foreign Function and Memory API");
	}
}
