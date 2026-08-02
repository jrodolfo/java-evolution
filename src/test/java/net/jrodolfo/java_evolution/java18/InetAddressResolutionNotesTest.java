package net.jrodolfo.java_evolution.java18;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InetAddressResolutionNotesTest {

	private final InetAddressResolutionNotes notes = new InetAddressResolutionNotes();

	@Test
	void notesExplainInetAddressResolutionSpi() {
		// When / Then
		assertThat(notes.extensionPoint())
				.as("The notes should identify the extension point")
				.contains("resolver")
				.contains("service-provider");
		assertThat(notes.useCase())
				.as("The notes should describe advanced networking use cases")
				.contains("custom DNS");
		assertThat(notes.projectDecision())
				.as("The notes should explain why no live resolver provider is installed")
				.contains("without installing");
	}
}
