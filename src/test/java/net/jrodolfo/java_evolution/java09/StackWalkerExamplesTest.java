package net.jrodolfo.java_evolution.java09;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StackWalkerExamplesTest {

	private final StackWalkerExamples examples = new StackWalkerExamples();

	@Test
	void stackWalkerCanReadCallerMethodName() {
		// When
		String callerMethodName = examples.callerMethodName();

		// Then
		assertThat(callerMethodName)
				.as("StackWalker should expose the method that called the inspected method")
				.isEqualTo("stackWalkerCanReadCallerMethodName");
	}

	@Test
	void stackWalkerCanReadCallerClassName() {
		// When
		String callerClassName = examples.callerClassName();

		// Then
		assertThat(callerClassName)
				.as("StackWalker with RETAIN_CLASS_REFERENCE should expose the caller class")
				.isEqualTo(StackWalkerExamplesTest.class.getName());
	}
}
