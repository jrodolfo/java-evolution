package net.jrodolfo.java_evolution.java11;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;

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
		URI uri = URI.create("https://example.com/java11");
		FakeHttpClient client = new FakeHttpClient("java 11 http response");

		// When
		String body = examples.sendGetRequest(client, uri);

		// Then
		assertThat(body)
				.as("send should execute the request and return the response body")
				.isEqualTo("java 11 http response");
		assertThat(client.lastRequest().method())
				.as("The example should send a GET request")
				.isEqualTo("GET");
		assertThat(client.lastRequest().uri())
				.as("The example should send the request to the provided URI")
				.isEqualTo(uri);
	}

	private static final class FakeHttpClient extends HttpClient {
		private final String body;
		private HttpRequest lastRequest;

		private FakeHttpClient(String body) {
			this.body = body;
		}

		private HttpRequest lastRequest() {
			return lastRequest;
		}

		@Override
		public Optional<CookieHandler> cookieHandler() {
			return Optional.empty();
		}

		@Override
		public Optional<Duration> connectTimeout() {
			return Optional.of(Duration.ofSeconds(1));
		}

		@Override
		public Redirect followRedirects() {
			return Redirect.NEVER;
		}

		@Override
		public Optional<ProxySelector> proxy() {
			return Optional.empty();
		}

		@Override
		public SSLContext sslContext() {
			return null;
		}

		@Override
		public SSLParameters sslParameters() {
			return new SSLParameters();
		}

		@Override
		public Optional<Authenticator> authenticator() {
			return Optional.empty();
		}

		@Override
		public HttpClient.Version version() {
			return HttpClient.Version.HTTP_2;
		}

		@Override
		public Optional<Executor> executor() {
			return Optional.empty();
		}

		@Override
		public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler)
				throws IOException, InterruptedException {
			lastRequest = request;
			HttpResponse.BodySubscriber<T> subscriber = responseBodyHandler.apply(responseInfo());
			subscriber.onSubscribe(new CompletedSubscription());
			subscriber.onNext(java.util.List.of(StandardCharsets.UTF_8.encode(body)));
			subscriber.onComplete();
			return new FakeHttpResponse<>(request, subscriber.getBody().toCompletableFuture().join());
		}

		@Override
		public <T> CompletableFuture<HttpResponse<T>> sendAsync(
				HttpRequest request,
				HttpResponse.BodyHandler<T> responseBodyHandler) {
			throw new UnsupportedOperationException("async execution is not needed for this test");
		}

		@Override
		public <T> CompletableFuture<HttpResponse<T>> sendAsync(
				HttpRequest request,
				HttpResponse.BodyHandler<T> responseBodyHandler,
				HttpResponse.PushPromiseHandler<T> pushPromiseHandler) {
			throw new UnsupportedOperationException("async execution is not needed for this test");
		}

		@Override
		public WebSocket.Builder newWebSocketBuilder() {
			throw new UnsupportedOperationException("web sockets are not needed for this test");
		}

		private HttpResponse.ResponseInfo responseInfo() {
			return new HttpResponse.ResponseInfo() {
				@Override
				public int statusCode() {
					return 200;
				}

				@Override
				public HttpHeaders headers() {
					return HttpHeaders.of(java.util.Map.of(), (first, second) -> true);
				}

				@Override
				public HttpClient.Version version() {
					return HttpClient.Version.HTTP_2;
				}
			};
		}
	}

	private static final class CompletedSubscription implements java.util.concurrent.Flow.Subscription {
		@Override
		public void request(long numberOfItems) {
			// The fake response sends the entire body immediately.
		}

		@Override
		public void cancel() {
			// Nothing to cancel in the fake response.
		}
	}

	private record FakeHttpResponse<T>(HttpRequest request, T body) implements HttpResponse<T> {
		@Override
		public int statusCode() {
			return 200;
		}

		@Override
		public Optional<HttpResponse<T>> previousResponse() {
			return Optional.empty();
		}

		@Override
		public HttpHeaders headers() {
			return HttpHeaders.of(java.util.Map.of(), (first, second) -> true);
		}

		@Override
		public URI uri() {
			return request.uri();
		}

		@Override
		public HttpClient.Version version() {
			return HttpClient.Version.HTTP_2;
		}

		@Override
		public Optional<SSLSession> sslSession() {
			return Optional.empty();
		}
	}
}
