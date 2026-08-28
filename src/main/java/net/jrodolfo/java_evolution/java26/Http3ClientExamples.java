package net.jrodolfo.java_evolution.java26;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpOption;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates Java 26 HTTP/3 support in the HTTP Client API.
 *
 * <p>
 * Java 11 standardized the HTTP Client API. Java 26 extends that client with
 * HTTP/3 support, letting applications request the newer protocol through the
 * same client model instead of switching to a separate networking library.
 * </p>
 *
 * <p>
 * This example deliberately does not send a live HTTP/3 request. Real protocol
 * negotiation depends on the target server, TLS configuration, QUIC/UDP
 * reachability, proxies, firewalls, and network policy. The deterministic
 * teaching point is the Java 26 API shape: applications can opt in to HTTP/3 at
 * the client level or request level, and can provide an HTTP/3 discovery hint.
 * </p>
 */
public class Http3ClientExamples {

	/**
	 * Explains the problem addressed by HTTP/3 support.
	 *
	 * @return a short problem statement
	 */
	public String problem() {
		return "applications using Java's standard HTTP Client previously had no built-in HTTP/3 protocol option";
	}

	/**
	 * Shows the default preferred protocol version for a standard client.
	 *
	 * @return the default preferred protocol version
	 */
	public HttpClient.Version defaultClientVersion() {
		return HttpClient.newHttpClient().version();
	}

	/**
	 * Builds an HTTP client whose preferred protocol version is HTTP/3.
	 *
	 * @return the preferred protocol version configured on the client
	 */
	public HttpClient.Version http3PreferredClientVersion() {
		HttpClient client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_3)
				.build();

		return client.version();
	}

	/**
	 * Builds one request whose preferred protocol version is HTTP/3.
	 *
	 * @param uri target URI used only to construct the request
	 * @return the preferred protocol version configured on the request
	 */
	public HttpClient.Version http3PreferredRequestVersion(URI uri) {
		HttpRequest request = HttpRequest.newBuilder(uri)
				.version(HttpClient.Version.HTTP_3)
				.GET()
				.build();

		return request.version().orElseThrow();
	}

	/**
	 * Lists the protocol versions exposed by the Java 26 HTTP Client API.
	 *
	 * @return fixed-size list of protocol versions
	 */
	public List<HttpClient.Version> supportedVersionConstants() {
		return Arrays.asList(HttpClient.Version.values());
	}

	/**
	 * Returns the standard HTTP/3 discovery option added with Java 26.
	 *
	 * @return the HTTP/3 discovery option
	 */
	public HttpOption<HttpOption.Http3DiscoveryMode> http3DiscoveryOption() {
		return HttpOption.H3_DISCOVERY;
	}

	/**
	 * Builds one HTTP/3 request with an explicit discovery hint.
	 *
	 * @param uri target URI used only to construct the request
	 * @param mode HTTP/3 discovery mode to attach to the request
	 * @return the HTTP/3 discovery mode configured on the request
	 */
	public HttpOption.Http3DiscoveryMode configuredDiscoveryMode(
			URI uri, HttpOption.Http3DiscoveryMode mode) {
		HttpRequest request = HttpRequest.newBuilder(uri)
				.version(HttpClient.Version.HTTP_3)
				.setOption(HttpOption.H3_DISCOVERY, mode)
				.GET()
				.build();

		return request.getOption(HttpOption.H3_DISCOVERY).orElseThrow();
	}

	/**
	 * Lists the HTTP/3 discovery modes understood by the JDK HTTP Client.
	 *
	 * @return fixed-size list of HTTP/3 discovery modes
	 */
	public List<HttpOption.Http3DiscoveryMode> discoveryModes() {
		return Arrays.asList(HttpOption.Http3DiscoveryMode.values());
	}

	/**
	 * Describes the protocol relationship.
	 *
	 * @return a short protocol explanation
	 */
	public String protocolContext() {
		return "HTTP/3 is the HTTP mapping that runs over QUIC rather than TCP";
	}

	/**
	 * Explains the executable boundary for this repository example.
	 *
	 * @return the executable boundary
	 */
	public String executableBoundary() {
		return "this example verifies Java 26 HTTP/3 API configuration without relying on live QUIC networking";
	}
}
