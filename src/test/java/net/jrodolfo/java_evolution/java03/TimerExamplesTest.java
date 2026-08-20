package net.jrodolfo.java_evolution.java03;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TimerExamplesTest {

	private final TimerExamples examples = new TimerExamples();

	@Test
	void timerRunsDelayedTask() throws InterruptedException {
		assertThat(examples.runDelayedTask())
				.as("Timer should execute the scheduled TimerTask")
				.isEqualTo(1);
	}
}
