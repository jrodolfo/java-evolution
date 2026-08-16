package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleImportDeclarationsSecondPreviewNotesTest {

	private final ModuleImportDeclarationsSecondPreviewNotes notes = new ModuleImportDeclarationsSecondPreviewNotes();

	@Test
	void notesPointToJava25Finalization() {
		String featureGoal = notes.featureGoal();
		String status = notes.status();
		String nextStep = notes.nextStep();

		assertThat(featureGoal)
				.as("The note should explain what module import declarations import")
				.contains("exported packages")
				.contains("module");
		assertThat(status)
				.as("Module imports should point to Java 25 finalization")
				.contains("second preview in Java 24")
				.contains("final in Java 25");
		assertThat(nextStep)
				.as("The Java 24 bridge note should point to the final Java 25 notes")
				.contains("ModuleImportDeclarationsNotes")
				.contains("Java 25");
	}
}
