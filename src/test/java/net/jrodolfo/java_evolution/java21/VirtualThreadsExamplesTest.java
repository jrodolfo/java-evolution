package net.jrodolfo.java_evolution.java21;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

class VirtualThreadsExamplesTest {

	private final VirtualThreadsExamples examples = new VirtualThreadsExamples();

	@Test
	void taskRunsInsideVirtualThread() throws ExecutionException, InterruptedException {
		assertThat(examples.taskRunsInVirtualThread())
				.as("Java 21 virtual threads should report isVirtual as true")
				.isTrue();
	}

	@Test
	void canStartMultipleNamedVirtualThreads() throws InterruptedException {
		assertThat(examples.startNamedVirtualThreads())
				.as("Virtual thread builders can name started threads")
				.hasSize(3)
				.allMatch(name -> name.startsWith("worker-"));
	}

	@Test
	void manyVirtualThreadsCanBlockAndStillComplete() throws InterruptedException {
		// Given
		int numberOfTasks = 500;

		// When
		int completedTasks = examples.runManyBlockingVirtualThreadTasks(numberOfTasks);

		// Then
		assertThat(completedTasks)
				.as("Virtual threads should support many simple blocking tasks without platform-thread-per-task cost")
				.isEqualTo(numberOfTasks);
	}
}
