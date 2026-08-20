package net.jrodolfo.java_evolution.java07;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ForkJoinExamplesTest {

	private final ForkJoinExamples examples = new ForkJoinExamples();

	@Test
	void forkJoinTaskRecursivelySplitsAndJoinsResults() {
		int sum = examples.parallelSum(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });

		assertThat(sum)
				.as("Fork/join should combine results from recursively split subtasks")
				.isEqualTo(36);
	}
}
