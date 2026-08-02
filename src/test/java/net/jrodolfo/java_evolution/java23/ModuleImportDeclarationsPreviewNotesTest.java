package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleImportDeclarationsPreviewNotesTest {

	private final ModuleImportDeclarationsPreviewNotes notes = new ModuleImportDeclarationsPreviewNotes();

	@Test
	void notesConnectModuleImportsPreviewToFinalRelease() {
		assertThat(notes.purpose())
				.as("Module imports should explain importing all exported packages from a module")
				.contains("module");
		assertThat(notes.status())
				.as("The Java 23 note should connect the preview to its Java 25 final release")
				.contains("final in Java 25");
	}
}
