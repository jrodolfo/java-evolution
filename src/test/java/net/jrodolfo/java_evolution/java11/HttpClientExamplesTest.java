package net.jrodolfo.java_evolution.java11;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
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

	@Test
	void getRequestCanBeSentSynchronouslyThroughTheStandardClientApi() throws Exception {
		// Given
		String responseBody = "java 11 http response";
		AtomicReference<String> requestMethod = new AtomicReference<>();
		HttpServer server = localServerReturningOrSkip(responseBody, requestMethod);
		server.start();

		try {
			URI uri = URI.create("http://localhost:" + server.getAddress().getPort() + "/java11");

			// When
			String body = examples.sendGetRequest(HttpClient.newHttpClient(), uri);

			// Then
			assertThat(body)
					.as("send should execute a real local GET request and return the response body")
					.isEqualTo(responseBody);
			assertThat(requestMethod.get())
					.as("The Java 11 HttpClient example should issue a GET request")
					.isEqualTo("GET");
		} finally {
			server.stop(0);
		}
	}

	private HttpServer localServerReturningOrSkip(String responseBody, AtomicReference<String> requestMethod)
			throws IOException {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
			server.createContext("/java11", exchange -> respondWithText(exchange, responseBody, requestMethod));
			return server;
		} catch (SocketException exception) {
			assumeTrue(false, "local HTTP server binding is not permitted in this environment");
			throw exception;
		}
	}

	private void respondWithText(
			HttpExchange exchange,
			String responseBody,
			AtomicReference<String> requestMethod) throws IOException {
		requestMethod.set(exchange.getRequestMethod());
		byte[] responseBytes = responseBody.getBytes(StandardCharsets.UTF_8);
		exchange.sendResponseHeaders(200, responseBytes.length);
		try (OutputStream response = exchange.getResponseBody()) {
			response.write(responseBytes);
		}
	}
}
