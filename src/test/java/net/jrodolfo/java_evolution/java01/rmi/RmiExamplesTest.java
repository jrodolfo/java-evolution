package net.jrodolfo.java_evolution.java01.rmi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.net.SocketException;

import org.junit.jupiter.api.Test;

class RmiExamplesTest {

	private final RmiExamples examples = new RmiExamples();

	@Test
	void remoteObjectCanBeBoundLookedUpAndInvokedThroughRegistry() throws Exception {
		RmiExamples.RmiGreetingEndpoint endpoint = startOrSkip();
		try {
			RmiExamples.GreetingService service = endpoint.lookupGreetingService();

			String greeting = service.greet(new RmiExamples.GreetingRequest("learner"));

			assertThat(greeting)
					.as("RMI should let a client call a remote interface through a registry lookup")
					.isEqualTo("hello, learner");
			assertThat(service)
					.as("The client should receive a stub implementing the remote interface")
					.isInstanceOf(RmiExamples.GreetingService.class);
		}
		finally {
			endpoint.close();
		}
	}

	@Test
	void remoteArgumentsAreSerializedByValue() throws Exception {
		RmiExamples.RmiGreetingEndpoint endpoint = startOrSkip();
		try {
			RmiExamples.GreetingService service = endpoint.lookupGreetingService();
			RmiExamples.GreetingRequest request = new RmiExamples.GreetingRequest("serialized argument");

			service.greet(request);

			assertThat(endpoint.lastReceivedRequest())
					.as("RMI should deserialize a copy of the serializable argument on the remote-object side")
					.isNotSameAs(request);
			assertThat(endpoint.lastReceivedRequest().name())
					.as("The copied argument should preserve serializable state")
					.isEqualTo("serialized argument");
		}
		finally {
			endpoint.close();
		}
	}

	private RmiExamples.RmiGreetingEndpoint startOrSkip() throws IOException {
		try {
			return examples.startGreetingService();
		}
		catch (IOException exception) {
			assumeTrue(!hasCause(exception, SocketException.class),
					"local RMI socket binding is not permitted in this environment");
			throw exception;
		}
	}

	private boolean hasCause(Throwable throwable, Class<? extends Throwable> causeType) {
		Throwable current = throwable;
		while (current != null) {
			if (causeType.isInstance(current)) {
				return true;
			}
			current = current.getCause();
		}
		return false;
	}
}
