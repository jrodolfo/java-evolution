package net.jrodolfo.java_evolution.java26;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Http3ClientNotesTest {

	private final Http3ClientNotes notes = new Http3ClientNotes();

	@Test
	void notesExplainStandardHttpClientProtocolUpgrade() {
		assertThat(notes.problem())
				.as("HTTP/3 should be explained as a standard HTTP Client protocol gap")
				.contains("standard HTTP Client")
				.contains("HTTP/3");
		assertThat(notes.protocolContext())
				.as("HTTP/3 notes should define the QUIC transport relationship")
				.contains("QUIC")
				.contains("TCP");
		assertThat(notes.projectDecision())
				.as("Java 26 HTTP/3 should stay notes-only until its executable boundary is reviewed")
				.contains("focused executable-example feasibility review");
	}
}
