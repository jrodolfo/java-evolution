package net.jrodolfo.java_evolution.java04;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ChainedExceptionExamplesTest {

	private final ChainedExceptionExamples examples = new ChainedExceptionExamples();

	@Test
	void wrapperExceptionPreservesOriginalCause() {
		RuntimeException thrown = null;
		try {
			examples.wrapWithCause();
		}
		catch (RuntimeException exception) {
			thrown = exception;
		}

		assertThat(thrown)
				.as("The wrapper should preserve the lower-level exception as its cause")
				.isInstanceOf(ChainedExceptionExamples.ImportException.class)
				.hasMessage("import failed");
		assertThat(thrown.getCause())
				.isInstanceOf(IOException.class)
				.hasMessage("disk failed");
	}
}
