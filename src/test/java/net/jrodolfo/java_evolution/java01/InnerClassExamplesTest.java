package net.jrodolfo.java_evolution.java01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InnerClassExamplesTest {

	private final InnerClassExamples examples = new InnerClassExamples("release");

	@Test
	void innerClassCanAccessEnclosingInstanceState() {
		assertThat(examples.formatWithInnerClass("java01"))
				.as("An inner class should be able to use state from the enclosing object")
				.isEqualTo("release: java01");
	}

	@Test
	void anonymousInnerClassShowsPreLambdaCallbackShape() {
		assertThat(examples.transformWithAnonymousClass("java"))
				.as("Anonymous inner classes were the common pre-lambda callback shape")
				.isEqualTo("JAVA");
	}
}
