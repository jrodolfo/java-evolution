package net.jrodolfo.java_evolution.java04;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LoggingExamplesTest {

	private final LoggingExamples examples = new LoggingExamples();

	@Test
	void javaUtilLoggingCanPublishToCustomHandler() {
		assertThat(examples.captureLogMessage())
				.as("java.util.logging should route records to an attached handler")
				.isEqualTo("java util logging");
	}
}
