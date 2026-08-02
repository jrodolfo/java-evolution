package net.jrodolfo.java_evolution.java24;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class StreamGatherersExamplesTest {

	private final StreamGatherersExamples examples = new StreamGatherersExamples();

	@Test
	void windowFixedCreatesFixedSizeWindows() {
		assertThat(examples.fixedWindows(List.of(1, 2, 3, 4, 5), 2))
				.as("windowFixed should create intermediate stream windows, keeping a smaller final window")
				.containsExactly(List.of(1, 2), List.of(3, 4), List.of(5));
	}

	@Test
	void scanCreatesRunningSum() {
		assertThat(examples.runningSum(List.of(1, 2, 3, 4)))
				.as("scan should emit the running state after each input value")
				.containsExactly(1, 3, 6, 10);
	}
}
