package net.jrodolfo.java_evolution.java15.hidden_classes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HiddenClassesNotesTest {

	private final HiddenClassesNotes notes = new HiddenClassesNotes();

	@Test
	void notesExplainGeneratedClassProblemAndHiddenClassPurpose() {
		assertThat(notes.generatedClassProblem())
				.as("Hidden classes should start from generated implementation classes being too discoverable")
				.contains("runtime-generated")
				.contains("discoverable by name")
				.contains("not use them directly");

		assertThat(notes.purpose())
				.as("The notes should explain the framework-oriented purpose")
				.contains("framework-generated")
				.contains("not discoverable by normal name lookup");
	}

	@Test
	void notesIdentifyApiUseCasesAndProjectBoundary() {
		assertThat(notes.primaryApi())
				.as("The notes should identify the main hidden class API")
				.isEqualTo("MethodHandles.Lookup.defineHiddenClass");

		assertThat(notes.realisticUseCase())
				.as("Hidden classes should be tied to framework and runtime generation scenarios")
				.contains("framework")
				.contains("proxies")
				.contains("dynamic language runtimes");

		assertThat(notes.projectDecision())
				.as("The notes should explain why no bytecode demo is included")
				.contains("without bytecode generation")
				.contains("distract from the Java 15 concept");
	}
}
