package net.jrodolfo.java_evolution.java03;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DynamicProxyExamplesTest {

	private final DynamicProxyExamples examples = new DynamicProxyExamples();

	@Test
	void dynamicProxyRoutesInterfaceCallThroughInvocationHandler() {
		assertThat(examples.proxyAddsTracing())
				.as("Proxy should implement the interface and route calls through InvocationHandler")
				.isEqualTo("traced: Hello, Java");
	}
}
