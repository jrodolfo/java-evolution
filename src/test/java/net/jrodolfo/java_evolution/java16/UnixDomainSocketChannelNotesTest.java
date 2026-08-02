package net.jrodolfo.java_evolution.java16;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UnixDomainSocketChannelNotesTest {

	private final UnixDomainSocketChannelNotes notes = new UnixDomainSocketChannelNotes();

	@Test
	void notesExplainUnixDomainSocketChannelSupport() {
		// When / Then
		assertThat(notes.protocolFamily())
				.as("The notes should name the Java protocol family")
				.isEqualTo("StandardProtocolFamily.UNIX");
		assertThat(notes.purpose())
				.as("The notes should explain the local IPC use case")
				.contains("inter-process communication")
				.contains("without TCP ports");
		assertThat(notes.projectDecision())
				.as("The notes should explain why there is no platform-specific socket test")
				.contains("operating system support");
	}
}
