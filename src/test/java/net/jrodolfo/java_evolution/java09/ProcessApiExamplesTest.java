package net.jrodolfo.java_evolution.java09;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProcessApiExamplesTest {

	private final ProcessApiExamples examples = new ProcessApiExamples();

	@Test
	void currentProcessIdIsAvailable() {
		// When
		long pid = examples.currentProcessId();

		// Then
		assertThat(pid)
				.as("ProcessHandle.current().pid() should expose the running JVM process id")
				.isPositive();
	}

	@Test
	void currentProcessSummaryContainsSafeProcessMetadata() {
		// When
		ProcessApiExamples.CurrentProcessSummary summary = examples.currentProcessSummary();

		// Then
		assertThat(summary.pid())
				.as("The current process summary should include a positive process id")
				.isPositive();
		assertThat(summary.alive())
				.as("The current JVM process should be alive while the test is running")
				.isTrue();
	}

	@Test
	void optionalProcessMetadataCanBeQueriedSafely() {
		// When / Then
		assertThat(examples.currentProcessCommand())
				.as("The command is optional because availability can vary by platform")
				.isNotNull();
		assertThat(examples.parentProcess())
				.as("The parent process is optional because it may not be visible")
				.isNotNull();
	}
}
