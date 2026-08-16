package net.jrodolfo.java_evolution.java24.aot_class_loading;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AotClassLoadingNotesTest {

	private final AotClassLoadingNotes notes = new AotClassLoadingNotes();

	@Test
	void notesExplainStartupOrientedRuntimeWork() {
		String purpose = notes.purpose();
		String classLoading = notes.classLoading();
		String classLinking = notes.classLinking();
		String before = notes.before();
		String after = notes.after();
		String projectDecision = notes.projectDecision();

		assertThat(purpose)
				.as("AOT class loading is a startup/runtime topic, not a language syntax feature")
				.contains("startup");
		assertThat(classLoading)
				.as("The notes should define class loading before explaining the optimization")
				.contains("class data")
				.contains("JVM");
		assertThat(classLinking)
				.as("The notes should define class linking before explaining the optimization")
				.contains("prepares")
				.contains("JVM");
		assertThat(before)
				.as("The note should name the startup work this feature targets")
				.contains("class loading")
				.contains("linking");
		assertThat(after)
				.as("The note should explain that some startup work can be prepared earlier")
				.contains("before the application run");
		assertThat(projectDecision)
				.as("The note should explain why there is no normal unit-test style example")
				.contains("runtime startup feature")
				.contains("unit-test");
	}
}
