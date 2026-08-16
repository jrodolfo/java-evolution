package net.jrodolfo.java_evolution.java25.aot_command_line;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AotCommandLineErgonomicsNotesTest {

	private final AotCommandLineErgonomicsNotes notes = new AotCommandLineErgonomicsNotes();

	@Test
	void notesExplainStartupProblemAndCacheCreation() {
		assertThat(notes.problem())
				.as("AOT ergonomics should start from the startup work being optimized")
				.contains("startup")
				.contains("loading")
				.contains("linking");
		assertThat(notes.java25Idea())
				.as("The notes should name the Java 25 one-command cache creation option")
				.contains("AOT cache")
				.contains("-XX:AOTCacheOutput");
	}

	@Test
	void notesExplainProductionUsageAndOperationalNature() {
		assertThat(notes.productionWorkflow())
				.as("The notes should show that production uses the created AOT cache")
				.contains("-XX:AOTCache")
				.contains("ahead-of-time data");
		assertThat(notes.projectDecision())
				.as("The notes should explain why this is not a normal source-code unit-test example")
				.contains("JVM launch")
				.contains("measurement workflow");
	}
}
