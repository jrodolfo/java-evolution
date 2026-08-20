package net.jrodolfo.java_evolution.java02.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SecurityPolicyNotesTest {

	private final SecurityPolicyNotes notes = new SecurityPolicyNotes();

	@Test
	void notesExplainSecurityPolicyConceptsAndModernContext() {
		assertThat(notes.problemSolved()).contains("permission model").contains("applet sandbox");
		assertThat(notes.coreConcepts()).contains("protection domains").contains("policy files");
		assertThat(notes.modernContext()).contains("Security Manager").contains("deprecated").contains("disabled");
	}
}
