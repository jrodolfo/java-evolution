package net.jrodolfo.java_evolution.java23.zgc_generational_mode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

class ZgcGenerationalModeExamplesTest {

	private final ZgcGenerationalModeExamples examples = new ZgcGenerationalModeExamples();

	@Test
	void selectedZgcIsReportedByChildJvm() throws Exception {
		assumeTrue(examples.zgcAvailable(), "this JDK build does not support ZGC");

		ZgcGenerationalModeExamples.VmFlagState state = examples.zgcFlagState();

		assertThat(state.name())
				.as("The runtime example should inspect the real ZGC VM flag")
				.isEqualTo("UseZGC");
		assertThat(state.enabled())
				.as("The child JVM should report ZGC as enabled after -XX:+UseZGC")
				.isTrue();
		assertThat(state.source())
				.as("PrintFlagsFinal should show that ZGC selection came from the command line")
				.contains("command line");
	}

	@Test
	void initializationLogShowsYoungAndOldGenerationWorkers() throws Exception {
		assumeTrue(examples.zgcAvailable(), "this JDK build does not support ZGC");

		ZgcGenerationalModeExamples.ProcessResult result = examples.zgcInitializationLog();

		assertThat(result.exitCode())
				.as("The child JVM should start with ZGC and GC initialization logging")
				.isZero();
		assertThat(result.output())
				.as("ZGC initialization logs should identify the selected collector")
				.contains("Initializing The Z Garbage Collector");
		assertThat(result.output())
				.as("Java 23 made ZGC generational by default, so the log should expose old and young generation workers")
				.contains("GC Workers for Old Generation")
				.contains("GC Workers for Young Generation");
	}

	@Test
	void oldGenerationalSwitchIsOnlyHistoricalContextInModernJdk() throws Exception {
		assumeTrue(examples.zgcAvailable(), "this JDK build does not support ZGC");

		ZgcGenerationalModeExamples.ProcessResult result = examples.oldGenerationalSwitchStatus();

		assertThat(result.exitCode())
				.as("Modern JDKs should keep running while warning that the old ZGenerational switch is ignored")
				.isZero();
		assertThat(result.output())
				.as("The old switch should be documented as removed rather than used for comparison")
				.contains("Ignoring option ZGenerational")
				.contains("support was removed in 24.0");
	}

	@Test
	void exampleExplainsGenerationalIdeaAndBenchmarkBoundary() {
		assertThat(examples.generationalObservation())
				.as("The example should explain why generational garbage collectors exist")
				.contains("die young")
				.contains("young and old generations")
				.contains("default");
		assertThat(examples.benchmarkBoundary())
				.as("The example should not pretend to prove GC performance")
				.contains("runtime selection")
				.contains("initialization logs")
				.contains("not throughput or pause-time improvement");
	}
}
