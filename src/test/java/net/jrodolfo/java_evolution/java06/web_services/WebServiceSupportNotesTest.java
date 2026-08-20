package net.jrodolfo.java_evolution.java06.web_services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class WebServiceSupportNotesTest {

	private final WebServiceSupportNotes notes = new WebServiceSupportNotes();

	@Test
	void notesExplainJavaSixWebServiceDirectionAndApiAreas() {
		assertThat(notes.javaSixDirection())
				.as("Java 6 web-service notes should describe APIs moving into Java SE")
				.contains("XML")
				.contains("web-service APIs")
				.contains("Java SE");

		assertThat(notes.apiAreas())
				.as("The major web-service and XML areas should stay visible")
				.contains("web-service metadata")
				.contains("XML binding")
				.contains("XML web services")
				.contains("XML digital signatures");
	}

	@Test
	void notesExplainModernJdkCompatibilityCaveat() {
		assertThat(notes.modernJdkCaveat())
				.as("Modern Java should not imply old Java EE APIs are still bundled")
				.contains("modern JDKs")
				.contains("removed")
				.contains("explicit dependencies");
	}
}
