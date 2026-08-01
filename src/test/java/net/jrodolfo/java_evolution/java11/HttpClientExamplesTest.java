package net.jrodolfo.java_evolution.java11;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import org.junit.jupiter.api.Test;

class HttpClientExamplesTest {

	private final HttpClientExamples examples = new HttpClientExamples();

	@Test
	void httpClientCanBeBuiltWithStandardJavaApi() {
		// When
		HttpClient client = examples.clientWithTimeout();

		// Then
		assertThat(client.connectTimeout())
				.as("The standard HTTP client should expose the configured timeout")
				.isPresent();
	}

	@Test
	void getRequestCanBeBuiltWithoutSendingNetworkTraffic() {
		// Given
		URI uri = URI.create("https://example.com/java11");

		// When
		HttpRequest request = examples.getRequest(uri);

		// Then
		assertThat(request.method())
				.as("The request should be configured as GET")
				.isEqualTo("GET");
		assertThat(request.uri())
				.as("The request should preserve the target URI")
				.isEqualTo(uri);
	}

	@Test
	void postRequestCanDeclareJsonBodyPublisher() {
		// Given
		URI uri = URI.create("https://example.com/java11");

		// When
		HttpRequest request = examples.postJsonRequest(uri, "{\"feature\":\"http client\"}");

		// Then
		assertThat(request.method())
				.as("The request should be configured as POST")
				.isEqualTo("POST");
		assertThat(request.headers().firstValue("Content-Type"))
				.as("The request should include the JSON content type")
				.contains("application/json");
	}
}
