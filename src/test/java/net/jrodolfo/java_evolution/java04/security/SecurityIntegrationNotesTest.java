package net.jrodolfo.java_evolution.java04.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SecurityIntegrationNotesTest {

	private final SecurityIntegrationNotes notes = new SecurityIntegrationNotes();

	@Test
	void notesExplainIntegratedSecurityApisAndModernCaution() {
		assertThat(notes.problemSolved()).contains("cryptography").contains("secure sockets").contains("authentication");
		assertThat(notes.integratedApis()).contains("JCE").contains("JSSE").contains("JAAS");
		assertThat(notes.modernCaution()).contains("current algorithms").contains("providers");
	}
}
