package net.jrodolfo.java_evolution.java01;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ExceptionHandlingBasicsExamplesTest {

	private final ExceptionHandlingBasicsExamples examples = new ExceptionHandlingBasicsExamples();

	@Test
	void checkedExceptionMustBeHandledOrDeclared() throws IOException {
		assertThat(examples.loadRequiredValue(true))
				.as("The successful path should return the loaded value")
				.isEqualTo("loaded");
	}

	@Test
	void checkedExceptionCanBeWrappedWithDomainFailure() {
		RuntimeException thrown = null;
		try {
			examples.loadOrThrowDomainFailure();
		}
		catch (RuntimeException exception) {
			thrown = exception;
		}

		assertThat(thrown)
				.as("The Java 1 style wrapper should expose the domain failure without Java 1.4 cause chaining")
				.isInstanceOf(ExceptionHandlingBasicsExamples.ConfigurationException.class)
				.hasMessage("configuration could not be loaded");
		assertThat(thrown.getCause())
				.as("Throwable cause chaining is demonstrated later as a Java 4 feature")
				.isNull();
	}
}
