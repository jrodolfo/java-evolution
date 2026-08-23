package net.jrodolfo.java_evolution.java04.preferences;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PreferencesNotesTest {

	private final PreferencesNotes notes = new PreferencesNotes();

	@Test
	void notesExplainPreferencesPurposeApiAndEnvironmentCaveat() {
		assertThat(notes.problemSolved()).contains("small user or system configuration");
		assertThat(notes.apiShape()).contains("java.util.prefs").contains("key/value");
		assertThat(notes.repositoryDecision()).contains("platform-specific backing stores").contains("synchronize");
	}
}
