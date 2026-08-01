package net.jrodolfo.java_evolution.java11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * Demonstrates the standard HTTP Client API finalized in Java 11.
 *
 * <p>
 * The examples build clients and requests without sending real network traffic,
 * keeping tests deterministic and offline.
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
}
