package net.jrodolfo.java_evolution.java09.module_system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModuleSystemNotesTest {

	private final ModuleSystemNotes notes = new ModuleSystemNotes();

	@Test
	void notesExplainTheClasspathProblemAndModuleDescriptor() {
		assertThat(notes.classpathProblem())
				.as("JPMS should start from the lack of strong classpath boundaries")
				.contains("classpath")
				.contains("strong boundaries")
				.contains("internal packages");

		assertThat(notes.moduleDescriptorPurpose())
				.as("module-info.java should be explained as the module descriptor")
				.contains("module-info.java")
				.contains("requires")
				.contains("exports");
	}

	@Test
	void minimalModuleDeclarationShowsRequiresAndExportsShape() {
		String declaration = notes.minimalModuleDeclaration();

		assertThat(declaration)
				.as("The example should show the module, requires, and exports directives")
				.contains("module net.jrodolfo.java_evolution.examples")
				.contains("requires java.net.http")
				.contains("exports net.jrodolfo.java_evolution.examples");
	}

	@Test
	void notesExplainRequiresExportsAndModulePath() {
		assertThat(notes.requiresDirective())
				.as("requires should be explained as a dependency declaration")
				.contains("depends")
				.contains("compile and run");

		assertThat(notes.exportsDirective())
				.as("exports should be explained as selected package visibility")
				.contains("public types")
				.contains("other modules");

		assertThat(notes.modulePathVsClasspath())
				.as("JPMS notes should distinguish module path from classpath")
				.contains("classpath")
				.contains("module path")
				.contains("declared dependencies");
	}

	@Test
	void projectDecisionExplainsWhyRepoIsNotModularized() {
		assertThat(notes.projectDecision())
				.as("The notes should explain the intentional Spring Boot simplification")
				.contains("JPMS")
				.contains("Spring Boot")
				.contains("without converting");
	}
}
