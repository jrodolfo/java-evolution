package net.jrodolfo.java_evolution.java25.compact_object_headers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompactObjectHeadersExamplesTest {

	private final CompactObjectHeadersExamples examples = new CompactObjectHeadersExamples();

	@Test
	void enabledOptionIsReportedByChildJvm() throws Exception {
		CompactObjectHeadersExamples.VmFlagState state = examples.enabledFlagState();

		assertThat(state.name())
				.as("The executable example should inspect the real compact-object-header VM flag")
				.isEqualTo("UseCompactObjectHeaders");
		assertThat(state.enabled())
				.as("Java 25 should accept -XX:+UseCompactObjectHeaders as a product runtime option")
				.isTrue();
		assertThat(state.source())
				.as("PrintFlagsFinal should show that the enabled value came from the command line")
				.contains("command line");
	}

	@Test
	void disabledOptionIsReportedByChildJvm() throws Exception {
		CompactObjectHeadersExamples.VmFlagState state = examples.disabledFlagState();

		assertThat(state.name())
				.as("The disabled probe should inspect the same VM flag")
				.isEqualTo("UseCompactObjectHeaders");
		assertThat(state.enabled())
				.as("The child JVM should report the explicitly disabled compact-object-header option")
				.isFalse();
		assertThat(state.source())
				.as("PrintFlagsFinal should show that the disabled value came from the command line")
				.contains("command line");
	}

	@Test
	void exampleExplainsItsMeasurementBoundary() {
		assertThat(examples.executableBoundary())
				.as("Compact object headers are executable as a runtime option, not as source-level syntax")
				.contains("JVM option")
				.contains("not object-size measurements");
		assertThat(examples.measurementCaution())
				.as("The example should not pretend to prove heap savings in a portable unit test")
				.contains("workload-specific heap measurement")
				.contains("object-layout tooling");
	}
}
