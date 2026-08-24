package net.jrodolfo.java_evolution.java24.virtual_thread_synchronization;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class VirtualThreadSynchronizationExamplesTest {

	private final VirtualThreadSynchronizationExamples examples = new VirtualThreadSynchronizationExamples();

	@Test
	void childJvmShowsSynchronizedBlockingDoesNotMonopolizeOneCarrier(@TempDir Path workspace) throws Exception {
		VirtualThreadSynchronizationExamples.CommandResult result =
				examples.runSynchronizedBlockingProbe(workspace);

		assertThat(result.exitCode())
				.as("the child JVM should complete when synchronized blocking virtual threads release their carrier")
				.isZero();
		assertThat(result.output())
				.as("the probe should print a stable success marker when all virtual threads finish")
				.contains(examples.successMarker());
		assertThat(result.output())
				.as("the probe should run with one scheduler carrier to make the boundary visible")
				.contains("scheduler-parallelism=1");
	}

	@Test
	void probeSourceKeepsTheRuntimeBoundaryVisible() {
		assertThat(examples.probeSource())
				.as("the probe should use synchronized code because that is the Java 24 runtime improvement")
				.contains("synchronized void waitForRelease")
				.contains("Thread.ofVirtual()")
				.contains(examples.successMarker());
	}

	@Test
	void exampleExplainsPinningAndSynchronizedCodeScalability() {
		String pinning = examples.pinning();
		String synchronizedConcern = examples.synchronizedConcern();
		String java24Improvement = examples.java24Improvement();
		String benefit = examples.benefit();
		String schedulerBoundary = examples.schedulerBoundary();

		assertThat(pinning)
				.as("Pinning means a blocked virtual thread still occupies its carrier platform thread")
				.contains("blocked virtual thread")
				.contains("carrier platform thread");
		assertThat(synchronizedConcern)
				.as("The Java 24 improvement matters because synchronized code is common in existing libraries")
				.contains("synchronized code")
				.contains("existing libraries")
				.contains("scalability");
		assertThat(java24Improvement)
				.as("Java 24 improves synchronized blocking behavior for virtual threads")
				.contains("Java 24")
				.contains("synchronized code")
				.contains("avoid pinning");
		assertThat(benefit)
				.as("Existing synchronized code can work better with virtual threads")
				.contains("existing synchronized code")
				.contains("virtual threads");
		assertThat(schedulerBoundary)
				.as("The example should explain why one carrier thread makes the runtime boundary visible")
				.contains("one virtual-thread carrier")
				.contains("blocked synchronized virtual threads");
	}
}
