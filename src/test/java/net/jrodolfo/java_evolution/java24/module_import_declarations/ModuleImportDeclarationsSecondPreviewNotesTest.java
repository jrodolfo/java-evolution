package net.jrodolfo.java_evolution.java24.module_import_declarations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleImportDeclarationsSecondPreviewNotesTest {

	private final ModuleImportDeclarationsSecondPreviewNotes notes = new ModuleImportDeclarationsSecondPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		String problemSolved = notes.problemSolved();
		String olderImportModel = notes.olderImportModel();
		String moduleImportModel = notes.moduleImportModel();
		String syntaxShape = notes.syntaxShape();
		String previewStep = notes.previewStep();
		String status = notes.secondPreviewStatus();
		String nextStep = notes.nextStep();

		assertThat(problemSolved)
				.as("The note should explain why import ceremony can grow")
				.contains("ordinary imports")
				.contains("several packages")
				.contains("same module");
		assertThat(olderImportModel)
				.as("The note should contrast module imports with ordinary imports")
				.contains("ordinary imports")
				.contains("one type")
				.contains("one package");
		assertThat(moduleImportModel)
				.as("The note should explain what module import declarations import")
				.contains("public top-level classes and interfaces")
				.contains("exported packages")
				.contains("named module");
		assertThat(syntaxShape)
				.as("The note should show the module import declaration shape")
				.isEqualTo("import module java.base;");
		assertThat(previewStep)
				.as("The note should explain Java 24's place in the preview sequence")
				.contains("second preview")
				.contains("Java 23")
				.contains("Java 25");
		assertThat(status)
				.as("Module imports should point to Java 25 finalization")
				.contains("second preview in Java 24")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The Java 24 bridge note should point to the final Java 25 executable example")
				.contains("ModuleImportDeclarationsExamples")
				.contains("Java 25");
	}
}
