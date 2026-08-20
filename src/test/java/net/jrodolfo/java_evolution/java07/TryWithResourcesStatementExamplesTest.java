package net.jrodolfo.java_evolution.java07;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class TryWithResourcesStatementExamplesTest {

	private final TryWithResourcesStatementExamples examples = new TryWithResourcesStatementExamples();

	@Test
	void resourceDeclaredInTryHeaderIsClosedAutomatically() throws IOException {
		String firstLine = examples.readFirstLine("first\nsecond");

		assertThat(firstLine)
				.as("Java 7 try-with-resources should keep resource handling in the try header")
				.isEqualTo("first");
	}

	@Test
	void closeFailureIsStoredAsSuppressedException() {
		Exception thrown = null;
		try {
			examples.failWithSuppressedCloseFailure();
		}
		catch (Exception exception) {
			thrown = exception;
		}

		assertThat(thrown)
				.as("The operation failure should remain primary while close failure is suppressed")
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("operation failed");
		assertThat(thrown.getSuppressed())
				.singleElement()
				.isInstanceOf(IOException.class);
		assertThat(thrown.getSuppressed()[0].getMessage())
				.isEqualTo("close failed");
	}
}
