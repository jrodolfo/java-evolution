package net.jrodolfo.java_evolution.java22;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ForeignFunctionMemoryApiNotesTest {

	private final ForeignFunctionMemoryApiNotes notes = new ForeignFunctionMemoryApiNotes();

	@Test
	void notesExplainFinalFfmApi() {
		assertThat(notes.purpose()).contains("native code").contains("outside the Java heap");
		assertThat(notes.replacesManyUseCasesFor()).isEqualTo("JNI");
		assertThat(notes.projectDecision()).contains("platform-specific");
	}
}
