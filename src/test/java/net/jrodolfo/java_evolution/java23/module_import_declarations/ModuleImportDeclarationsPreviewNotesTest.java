package net.jrodolfo.java_evolution.java23.module_import_declarations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleImportDeclarationsPreviewNotesTest {

	private final ModuleImportDeclarationsPreviewNotes notes = new ModuleImportDeclarationsPreviewNotes();

	@Test
	void notesConnectModuleImportsPreviewToFinalRelease() {
		String olderImportModel = notes.olderImportModel();
		String whatJavaPreviewed = notes.whatJavaPreviewed();
		String usefulContext = notes.usefulContext();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(olderImportModel)
				.as("The note should explain the familiar import model before module imports")
				.contains("individual types")
				.contains("packages");
		assertThat(whatJavaPreviewed)
				.as("Module imports should explain importing public top-level types from exported packages")
				.contains("public top-level types")
				.contains("exported packages")
				.contains("named module");
		assertThat(usefulContext)
				.as("The note should explain where broad module imports can improve readability")
				.contains("small programs")
				.contains("tutorials")
				.contains("learning examples");
		assertThat(status)
				.as("The Java 23 note should connect the preview to its Java 25 final release")
				.contains("preview in Java 23")
				.contains("second preview in Java 24")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The bridge note should point learners to the final Java 25 notes")
				.contains("ModuleImportDeclarationsNotes")
				.contains("Java 25");
	}
}
