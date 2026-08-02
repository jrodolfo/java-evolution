package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleImportDeclarationsSecondPreviewNotesTest {

	private final ModuleImportDeclarationsSecondPreviewNotes notes = new ModuleImportDeclarationsSecondPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		assertThat(notes.status())
				.as("Module imports should point to Java 25 finalization")
				.contains("final in Java 25");
	}
}
