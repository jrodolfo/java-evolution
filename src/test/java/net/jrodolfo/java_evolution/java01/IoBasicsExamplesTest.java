package net.jrodolfo.java_evolution.java01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class IoBasicsExamplesTest {

	private final IoBasicsExamples examples = new IoBasicsExamples();

	@Test
	void inputStreamCanBeCopiedToOutputStream() throws IOException {
		assertThat(examples.copyBytes("classic io"))
				.as("The classic stream model should copy bytes from input to output")
				.isEqualTo("classic io");
	}
}
