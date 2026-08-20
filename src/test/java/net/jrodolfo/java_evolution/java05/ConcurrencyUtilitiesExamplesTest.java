package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ConcurrencyUtilitiesExamplesTest {

	private final ConcurrencyUtilitiesExamples examples = new ConcurrencyUtilitiesExamples();
	private final ExecutorService executor = Executors.newFixedThreadPool(2);

	@AfterEach
	void shutDownExecutor() {
		executor.shutdownNow();
	}

	@Test
	void executorServiceRunsCallableAndFutureReturnsResult() throws Exception {
		String result = examples.runCallable(executor);

		assertThat(result)
				.as("ExecutorService and Future should separate task submission from result retrieval")
				.isEqualTo("computed by executor");
	}

	@Test
	void latchAndAtomicIntegerCoordinateSeveralTasks() throws InterruptedException {
		int completed = examples.countCompletedTasks(executor, 4);

		assertThat(completed)
				.as("CountDownLatch should let the caller wait for related tasks to complete")
				.isEqualTo(4);
	}

	@Test
	void invokeAllCollectsResultsFromRelatedTasks() throws Exception {
		int total = examples.sumTaskResults(executor);

		assertThat(total)
				.as("invokeAll should run each Callable and expose each result through a Future")
				.isEqualTo(60);
	}
}
