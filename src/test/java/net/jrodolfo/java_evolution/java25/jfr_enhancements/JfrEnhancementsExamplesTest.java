package net.jrodolfo.java_evolution.java25.jfr_enhancements;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class JfrEnhancementsExamplesTest {

	private final JfrEnhancementsExamples examples = new JfrEnhancementsExamples();

	@Test
	void methodTimingAndTracingWriteARealJfrRecording(@TempDir Path workspace) throws IOException {
		Path recordingFile = workspace.resolve("method-timing-and-tracing.jfr");

		JfrEnhancementsExamples.JfrMethodObservation observation =
				examples.recordMethodTimingAndTracing(recordingFile);

		assertThat(Files.exists(recordingFile))
				.as("The example should write a real .jfr file that can be inspected later")
				.isTrue();
		assertThat(observation.workloadInvocations())
				.as("The workload should call the traced method enough times to produce method events")
				.isEqualTo(5_000);
		assertThat(observation.checksum())
				.as("The workload result prevents the traced method from being purely decorative")
				.isNotZero();
		assertThat(observation.methodTraceCount())
				.as("JEP 520 method tracing should record selected method invocations")
				.isGreaterThan(0);
		assertThat(observation.methodTimingCount())
				.as("JEP 520 method timing should summarize selected method invocation timing")
				.isGreaterThan(0);
		assertThat(observation.timedInvocations())
				.as("Method timing should report that the selected method actually ran")
				.isGreaterThan(0);
		assertThat(observation.methodNames())
				.as("The JFR recording should identify the method selected by the filter")
				.anyMatch(methodName -> methodName.endsWith("JfrEnhancementsExamples::tracedWork"));
	}

	@Test
	void explanationsKeepTheOtherJfrEnhancementsInTheirRuntimeContext() {
		assertThat(examples.problem())
				.as("JFR should be introduced as runtime evidence for performance and behavior questions")
				.contains("runtime evidence")
				.contains("performance");
		assertThat(examples.features())
				.as("The module should still name all Java 25 JFR enhancement areas")
				.contains("CPU-time")
				.contains("cooperative sampling")
				.contains("method timing");
		assertThat(examples.cpuTimeProfilingCaveat())
				.as("CPU-time profiling should be marked as experimental and platform-dependent")
				.contains("experimental")
				.contains("Linux")
				.contains("portable tests");
		assertThat(examples.cooperativeSamplingCaveat())
				.as("Cooperative sampling should not be reduced to a misleading deterministic unit assertion")
				.contains("stack-sampling stability")
				.contains("safepoints")
				.contains("sampling quality");
	}
}
