package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleImportDeclarationsPreviewNotesTest {

	private final ModuleImportDeclarationsPreviewNotes notes = new ModuleImportDeclarationsPreviewNotes();

	@Test
	void notesConnectModuleImportsPreviewToFinalRelease() {
		String purpose = notes.purpose();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(purpose)
				.as("Module imports should explain importing all exported packages from a module")
				.contains("exported")
				.contains("module");
		assertThat(status)
				.as("The Java 23 note should connect the preview to its Java 25 final release")
				.contains("preview in Java 23")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The bridge note should point learners to the final Java 25 notes")
				.contains("ModuleImportDeclarationsNotes")
				.contains("Java 25");
	}
}
