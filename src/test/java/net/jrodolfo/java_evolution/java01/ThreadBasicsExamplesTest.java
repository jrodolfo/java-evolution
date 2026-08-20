package net.jrodolfo.java_evolution.java01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ThreadBasicsExamplesTest {

	private final ThreadBasicsExamples examples = new ThreadBasicsExamples();

	@Test
	void threadRunsRunnableAndJoinWaitsForCompletion() throws InterruptedException {
		assertThat(examples.runRunnableInThread())
				.as("Thread.join should wait until the Runnable changes the shared value")
				.isEqualTo(42);
	}

	@Test
	void synchronizedMethodProtectsSharedCounterAcrossThreads() throws InterruptedException {
		assertThat(examples.synchronizedCounter())
				.as("Synchronized methods should serialize concurrent access to the counter state")
				.isEqualTo(2000);
	}
}
