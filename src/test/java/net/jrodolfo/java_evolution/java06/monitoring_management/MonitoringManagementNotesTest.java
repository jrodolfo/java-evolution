package net.jrodolfo.java_evolution.java06.monitoring_management;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MonitoringManagementNotesTest {

	private final MonitoringManagementNotes notes = new MonitoringManagementNotes();

	@Test
	void notesExplainOperationalMonitoringProblemAndTerms() {
		assertThat(notes.problemSolved())
				.as("Monitoring notes should be about observing running JVM applications")
				.contains("running JVM")
				.contains("memory")
				.contains("threads");

		assertThat(notes.importantTerms())
				.as("Java 6 monitoring vocabulary should remain visible")
				.contains("JMX")
				.contains("MXBeans")
				.contains("JConsole")
				.contains("jps")
				.contains("jstat");
	}

	@Test
	void notesExplainWhyRuntimeToolingIsNotAUnitExample() {
		assertThat(notes.repositoryDecision())
				.as("The repository decision should distinguish tooling behavior from pure methods")
				.contains("running process")
				.contains("management tools")
				.contains("notes");
	}
}
