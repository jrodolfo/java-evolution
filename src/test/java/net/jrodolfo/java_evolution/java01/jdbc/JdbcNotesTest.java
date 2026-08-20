package net.jrodolfo.java_evolution.java01.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JdbcNotesTest {

	private final JdbcNotes notes = new JdbcNotes();

	@Test
	void notesExplainJdbcPurposeTypesAndRepositoryDecision() {
		assertThat(notes.problemSolved()).contains("portable database API");
		assertThat(notes.coreTypes()).contains("Connection").contains("PreparedStatement").contains("ResultSet");
		assertThat(notes.repositoryDecision()).contains("driver").contains("database").contains("schema");
	}
}
