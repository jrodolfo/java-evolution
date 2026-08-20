package net.jrodolfo.java_evolution.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Demonstrates the standard HTTP Client API finalized in Java 11.
 *
 * <p>
 * Before Java 11, the JDK's main built-in HTTP API was
 * {@code HttpURLConnection}, which was old and awkward for modern HTTP usage.
 * Many applications reached for third-party clients to get a fluent API,
 * asynchronous calls, and HTTP/2 support.
 * </p>
 *
 * <p>
 * The Java 11 {@link HttpClient} solves this by providing a standard client
 * with builders, synchronous and asynchronous execution, and modern protocol
 * support. The examples include request creation and synchronous execution.
 * Tests use a local HTTP server on an ephemeral port, so the request and
 * response flow is real while remaining deterministic and offline.
 * </p>
 */
public class HttpClientExamples {

	/**
	 * Creates an HTTP client using the Java 11 standard API.
	 *
	 * @return a configured HTTP client
	 */
	public HttpClient clientWithTimeout() {
		return HttpClient.newBuilder()
				.connectTimeout(Duration.ofSeconds(5))
				.build();
	}

	/**
	 * Builds a GET request.
	 *
	 * @param uri the request URI
	 * @return an HTTP GET request
	 */
	public HttpRequest getRequest(URI uri) {
		return HttpRequest.newBuilder(uri)
				.timeout(Duration.ofSeconds(10))
				.GET()
				.build();
	}

	/**
	 * Builds a POST request with a String body.
	 *
	 * @param uri the request URI
	 * @param jsonBody the JSON body
	 * @return an HTTP POST request
	 */
	public HttpRequest postJsonRequest(URI uri, String jsonBody) {
		return HttpRequest.newBuilder(uri)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();
	}

	/**
	 * Sends a GET request synchronously and returns the response body.
	 *
	 * <p>
	 * The {@link HttpClient#send(HttpRequest, HttpResponse.BodyHandler)} method is
	 * the central Java 11 operation for blocking HTTP calls. The client is
	 * provided by the caller so tests can use a local deterministic server instead
	 * of relying on external network access.
	 * </p>
	 *
	 * @param client the client used to execute the request
	 * @param uri the request URI
	 * @return the response body
	 * @throws IOException when the client cannot read the response
	 * @throws InterruptedException when the sending thread is interrupted
	 */
	public String sendGetRequest(HttpClient client, URI uri) throws IOException, InterruptedException {
		HttpRequest request = getRequest(uri);
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
}
