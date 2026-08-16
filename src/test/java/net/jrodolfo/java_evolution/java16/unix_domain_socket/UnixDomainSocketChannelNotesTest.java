package net.jrodolfo.java_evolution.java16.unix_domain_socket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UnixDomainSocketChannelNotesTest {

	private final UnixDomainSocketChannelNotes notes = new UnixDomainSocketChannelNotes();

	@Test
	void notesExplainUnixDomainSocketChannelSupport() {
		// When / Then
		assertThat(notes.problemSolved())
				.as("The notes should explain the local communication problem")
				.contains("same machine")
				.contains("without opening a TCP port");
		assertThat(notes.protocolFamily())
				.as("The notes should name the Java protocol family")
				.isEqualTo("StandardProtocolFamily.UNIX");
		assertThat(notes.addressShape())
				.as("The notes should explain that Unix-domain sockets use local path addresses")
				.contains("file-system path");
		assertThat(notes.comparedWithTcp())
				.as("The notes should contrast local path sockets with host-and-port TCP sockets")
				.contains("host and port")
				.contains("local path");
		assertThat(notes.purpose())
				.as("The notes should explain the local IPC use case")
				.contains("inter-process communication")
				.contains("without TCP ports");
		assertThat(notes.projectDecision())
				.as("The notes should explain why there is no platform-specific socket test")
				.contains("operating system support");
	}
}
