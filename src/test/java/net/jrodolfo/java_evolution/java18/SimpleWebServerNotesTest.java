package net.jrodolfo.java_evolution.java18;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SimpleWebServerNotesTest {

	private final SimpleWebServerNotes notes = new SimpleWebServerNotes();

	@Test
	void notesExplainSimpleWebServerTool() {
		// When / Then
		assertThat(notes.command())
				.as("The notes should name the Java 18 command-line tool")
				.isEqualTo("jwebserver");
		assertThat(notes.exampleCommand())
				.as("The notes should include a practical command example")
				.contains("--port")
				.contains("--directory");
		assertThat(notes.purpose())
				.as("The notes should explain the static-file use case")
				.contains("static files");
	}
}
