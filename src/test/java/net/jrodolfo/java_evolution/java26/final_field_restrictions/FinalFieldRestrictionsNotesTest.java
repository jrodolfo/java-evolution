package net.jrodolfo.java_evolution.java26.final_field_restrictions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FinalFieldRestrictionsNotesTest {

	private final FinalFieldRestrictionsNotes notes = new FinalFieldRestrictionsNotes();

	@Test
	void notesExplainFinalFieldIntegrityMigration() {
		assertThat(notes.problem())
				.as("Final-field restrictions should be tied to immutability and JVM trust")
				.contains("deep reflection")
				.contains("immutability")
				.contains("JVM");
		assertThat(notes.java26Behavior())
				.as("Java 26 behavior should be warnings, not immediate failure")
				.contains("Java 26")
				.contains("warnings");
		assertThat(notes.migrationDirection())
				.as("Migration guidance should describe selective legacy enablement")
				.contains("avoid")
				.contains("legacy frameworks");
	}
}
