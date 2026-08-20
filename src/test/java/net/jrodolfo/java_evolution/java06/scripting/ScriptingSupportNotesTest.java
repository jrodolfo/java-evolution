package net.jrodolfo.java_evolution.java06.scripting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScriptingSupportNotesTest {

	private final ScriptingSupportNotes notes = new ScriptingSupportNotes();

	@Test
	void notesExplainStandardScriptingApiAndEngineCaveat() {
		assertThat(notes.problemSolved())
				.as("The notes should start with standard hosted scripting")
				.contains("standard")
				.contains("scripting languages");

		assertThat(notes.apiPackage())
				.as("The Java 6 API package and engine types should stay visible")
				.contains("javax.script")
				.contains("ScriptEngineManager")
				.contains("ScriptEngine");

		assertThat(notes.modernJdkCaveat())
				.as("The notes should not imply JDK 25 ships the old JavaScript engine")
				.contains("JDK 25")
				.contains("does not guarantee")
				.contains("JavaScript engine");
	}

	@Test
	void notesExplainModernDependencyPractice() {
		assertThat(notes.modernPractice())
				.as("Modern code should treat script engines as pluggable dependencies")
				.contains("explicit dependency")
				.contains("ScriptEngineManager");
	}
}
