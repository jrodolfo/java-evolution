package net.jrodolfo.java_evolution.java25.jfr_enhancements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordedMethod;
import jdk.jfr.consumer.RecordingFile;

/**
 * Demonstrates Java Flight Recorder (JFR) method timing and tracing introduced
 * in Java 25.
 *
 * <p>
 * Java 25 also added JFR CPU-time profiling and cooperative sampling. Those are
 * documented here as runtime caveats because CPU-time profiling is experimental
 * and platform-dependent, and cooperative sampling is a profiling-quality
 * improvement rather than a behavior that a small unit test can prove
 * faithfully.
 * </p>
 */
public class JfrEnhancementsExamples {

	private static final String TARGET_METHOD =
			"net.jrodolfo.java_evolution.java25.jfr_enhancements.JfrEnhancementsExamples::tracedWork";
	private static final int WORKLOAD_INVOCATIONS = 5_000;

	/**
	 * Explains the problem that JFR helps solve.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "developers need runtime evidence before deciding which application behavior or performance problem to optimize";
	}

	/**
	 * Names the Java 25 JFR areas represented by this learning module.
	 *
	 * @return feature summary
	 */
	public String features() {
		return "CPU-time profiling, cooperative sampling, and method timing and tracing";
	}

	/**
	 * Explains why CPU-time profiling remains explanatory in this repository.
	 *
	 * @return a short compatibility caveat
	 */
	public String cpuTimeProfilingCaveat() {
		return "JFR CPU-time profiling is an experimental Linux feature, so this repository does not require it in portable tests";
	}

	/**
	 * Explains why cooperative sampling remains explanatory in this repository.
	 *
	 * @return a short testing caveat
	 */
	public String cooperativeSamplingCaveat() {
		return "JFR cooperative sampling improves stack-sampling stability at safepoints, but a tiny unit test should not pretend to prove sampling quality";
	}

	/**
	 * Records JFR method timing and tracing events for a selected method.
	 *
	 * <p>
	 * The method writes a real {@code .jfr} recording, reads it back with the JFR
	 * consumer API, and returns the observed event counts. The target method is
	 * deliberately narrow so the recording stays deterministic enough for
	 * executable documentation.
	 * </p>
	 *
	 * @param recordingFile the file that will receive the JFR recording
	 * @return the method-timing and method-tracing events observed in the recording
	 * @throws IOException when the recording cannot be written or read
	 */
	public JfrMethodObservation recordMethodTimingAndTracing(Path recordingFile) throws IOException {
		Files.deleteIfExists(recordingFile);

		try (Recording recording = new Recording()) {
			recording.enable("jdk.MethodTrace")
					.with("filter", TARGET_METHOD)
					.withThreshold(Duration.ZERO)
					.withStackTrace();
			recording.enable("jdk.MethodTiming")
					.with("filter", TARGET_METHOD)
					.with("period", "endChunk");

			recording.start();
			long checksum = runWorkload();
			recording.stop();
			recording.dump(recordingFile);

			return observeRecording(recordingFile, checksum);
		}
	}

	/**
	 * Performs a small amount of repeatable work for the JFR recording.
	 *
	 * <p>
	 * The implementation is intentionally simple. The lesson is how JFR records a
	 * selected method, not how to microbenchmark Java code.
	 * </p>
	 *
	 * @param value input value
	 * @return deterministic computed value
	 */
	public static long tracedWork(int value) {
		long total = value;
		for (int index = 0; index < 128; index++) {
			total = (total * 31) + index;
		}
		return total;
	}

	private long runWorkload() {
		long checksum = 0;
		for (int invocation = 0; invocation < WORKLOAD_INVOCATIONS; invocation++) {
			checksum += tracedWork(invocation);
		}
		return checksum;
	}

	private JfrMethodObservation observeRecording(Path recordingFile, long checksum) throws IOException {
		int methodTraceCount = 0;
		int methodTimingCount = 0;
		long timedInvocations = 0;
		Set<String> methodNames = new LinkedHashSet<>();

		for (RecordedEvent event : RecordingFile.readAllEvents(recordingFile)) {
			String eventName = event.getEventType().getName();
			if ("jdk.MethodTrace".equals(eventName)) {
				methodTraceCount++;
				methodNames.add(methodName(event));
			}
			if ("jdk.MethodTiming".equals(eventName)) {
				methodTimingCount++;
				timedInvocations += event.getLong("invocations");
				methodNames.add(methodName(event));
			}
		}

		return new JfrMethodObservation(
				recordingFile,
				WORKLOAD_INVOCATIONS,
				checksum,
				methodTraceCount,
				methodTimingCount,
				timedInvocations,
				List.copyOf(methodNames));
	}

	private String methodName(RecordedEvent event) {
		RecordedMethod method = event.getValue("method");
		return method.getType().getName() + "::" + method.getName();
	}

	/**
	 * Summary of method events observed in a JFR recording.
	 *
	 * @param recordingFile the generated recording file
	 * @param workloadInvocations how many times the target method was called
	 * @param checksum deterministic result of the measured workload
	 * @param methodTraceCount number of {@code jdk.MethodTrace} events
	 * @param methodTimingCount number of {@code jdk.MethodTiming} events
	 * @param timedInvocations invocation count reported by method timing events
	 * @param methodNames method names found in timing or tracing events
	 */
	public record JfrMethodObservation(
			Path recordingFile,
			int workloadInvocations,
			long checksum,
			int methodTraceCount,
			int methodTimingCount,
			long timedInvocations,
			List<String> methodNames) {
	}
}
