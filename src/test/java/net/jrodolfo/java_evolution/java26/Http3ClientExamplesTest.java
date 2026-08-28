package net.jrodolfo.java_evolution.java26;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpOption;

import org.junit.jupiter.api.Test;

class Http3ClientExamplesTest {

	private final Http3ClientExamples examples = new Http3ClientExamples();

	@Test
	void examplesExplainStandardHttpClientProtocolUpgrade() {
		assertThat(examples.problem())
				.as("HTTP/3 should be explained as a standard HTTP Client protocol gap")
				.contains("standard HTTP Client")
				.contains("HTTP/3");
		assertThat(examples.protocolContext())
				.as("HTTP/3 examples should define the QUIC transport relationship")
				.contains("QUIC")
				.contains("TCP");
		assertThat(examples.executableBoundary())
				.as("The example should avoid live HTTP/3 network dependencies")
				.contains("API configuration")
				.contains("without relying on live QUIC networking");
	}

	@Test
	void defaultHttpClientStillPrefersHttp2() {
		assertThat(examples.defaultClientVersion())
				.as("Java 26 adds HTTP/3 without making it the default preferred protocol")
				.isEqualTo(HttpClient.Version.HTTP_2);
	}

	@Test
	void clientCanPreferHttp3() {
		assertThat(examples.http3PreferredClientVersion())
				.as("Java 26 lets an HttpClient opt in to HTTP/3")
				.isEqualTo(HttpClient.Version.HTTP_3);
	}

	@Test
	void requestCanPreferHttp3() {
		assertThat(examples.http3PreferredRequestVersion(URI.create("https://example.com/")))
				.as("Java 26 lets one HttpRequest opt in to HTTP/3")
				.isEqualTo(HttpClient.Version.HTTP_3);
	}

	@Test
	void versionEnumIncludesHttp3() {
		assertThat(examples.supportedVersionConstants())
				.as("The Java 26 protocol-version enum should expose HTTP_3")
				.contains(HttpClient.Version.HTTP_1_1, HttpClient.Version.HTTP_2, HttpClient.Version.HTTP_3);
	}

	@Test
	void http3DiscoveryOptionExposesNameAndValueType() {
		HttpOption<HttpOption.Http3DiscoveryMode> option = examples.http3DiscoveryOption();

		assertThat(option.name())
				.as("The standard request option should be named H3_DISCOVERY")
				.isEqualTo("H3_DISCOVERY");
		assertThat(option.type())
				.as("The discovery option should accept Http3DiscoveryMode values")
				.isEqualTo(HttpOption.Http3DiscoveryMode.class);
	}

	@Test
	void requestCanCarryHttp3DiscoveryHint() {
		assertThat(examples.configuredDiscoveryMode(
				URI.create("https://example.com/"),
				HttpOption.Http3DiscoveryMode.HTTP_3_URI_ONLY))
				.as("Java 26 lets a request carry an explicit HTTP/3 discovery hint")
				.isEqualTo(HttpOption.Http3DiscoveryMode.HTTP_3_URI_ONLY);
	}

	@Test
	void discoveryModesExplainHowHttp3CanBeEstablished() {
		assertThat(examples.discoveryModes())
				.as("The JDK should expose the Java 26 HTTP/3 discovery modes")
				.containsExactly(
						HttpOption.Http3DiscoveryMode.ANY,
						HttpOption.Http3DiscoveryMode.ALT_SVC,
						HttpOption.Http3DiscoveryMode.HTTP_3_URI_ONLY);
	}
}
