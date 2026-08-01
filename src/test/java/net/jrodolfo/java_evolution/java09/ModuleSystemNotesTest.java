package net.jrodolfo.java_evolution.java09;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleSystemNotesTest {

	private final ModuleSystemNotes notes = new ModuleSystemNotes();

	@Test
	void minimalModuleDeclarationShowsModuleInfoShape() {
		// When
		String declaration = notes.minimalModuleDeclaration();

		// Then
		assertThat(declaration)
				.as("The example should show the module and exports directives")
				.contains("module net.jrodolfo.java_evolution")
				.contains("exports net.jrodolfo.java_evolution.java09");
	}

	@Test
	void notesExplainRequiresAndExportsDirectives() {
		// When / Then
		assertThat(notes.requiresDirective())
				.as("requires should be explained as a dependency declaration")
				.contains("another module");
		assertThat(notes.exportsDirective())
				.as("exports should be explained as package visibility for other modules")
				.contains("package")
				.contains("other modules");
	}

	@Test
	void projectDecisionExplainsWhyRepoIsNotModularizedYet() {
		// When / Then
		assertThat(notes.projectDecision())
				.as("The notes should explain the intentional Spring Boot simplification")
				.contains("Spring Boot")
				.contains("without converting");
	}
}
