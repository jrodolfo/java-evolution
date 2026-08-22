package net.jrodolfo.java_evolution.java26.applet_api_removal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AppletApiRemovalNotesTest {

	private final AppletApiRemovalNotes notes = new AppletApiRemovalNotes();

	@Test
	void notesExplainAppletRemovalAsHistoricalMigration() {
		assertThat(notes.historicalContext())
				.as("Applet notes should place applets in the browser plugin era")
				.contains("browser")
				.contains("plugin");
		assertThat(notes.java26Change())
				.as("The notes should identify Java 26 as the API removal release")
				.contains("Java 26")
				.contains("removes");
		assertThat(notes.migrationLesson())
				.as("Removed APIs should teach deprecation-for-removal as a real migration signal")
				.contains("deprecation-for-removal")
				.contains("migration");
	}
}
