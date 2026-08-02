package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleImportDeclarationsNotesTest {

	private final ModuleImportDeclarationsNotes notes = new ModuleImportDeclarationsNotes();

	@Test
	void notesShowFinalModuleImportDeclarationShape() {
		assertThat(notes.purpose())
				.as("Module imports should explain reduced import ceremony")
				.contains("module");
		assertThat(notes.example())
				.as("Module imports should show the source declaration shape")
				.contains("import module");
	}
}
