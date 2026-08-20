package net.jrodolfo.java_evolution.java18.inet_address_resolution;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InetAddressResolutionNotesTest {

	private final InetAddressResolutionNotes notes = new InetAddressResolutionNotes();

	@Test
	void notesExplainInetAddressResolutionSpi() {
		// When
		var extensionPoint = notes.extensionPoint();
		var useCase = notes.useCase();
		var projectDecision = notes.projectDecision();

		// Then
		assertThat(extensionPoint)
				.as("The notes should identify the extension point")
				.contains("InetAddressResolverProvider")
				.contains("service-provider");
		assertThat(useCase)
				.as("The notes should describe advanced networking use cases")
				.contains("custom DNS");
		assertThat(projectDecision)
				.as("The notes should explain why no live resolver provider is installed")
				.contains("process-wide")
				.contains("without installing");
	}
}
