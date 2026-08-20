package net.jrodolfo.java_evolution.java18.simple_web_server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

/**
 * Demonstrates the Java 18 Simple Web Server API.
 *
 * <p>
 * Java 18 introduced the {@code jwebserver} command for quickly serving static
 * files from a directory. The same JEP also introduced
 * {@link SimpleFileServer}, which lets Java code create the same kind of local
 * static-file server. This class wraps that API in a tiny lifecycle-friendly
 * example for tests and study.
 * </p>
 */
public final class SimpleStaticFileServer implements AutoCloseable {

	private final Path rootDirectory;
	private final HttpServer server;
	private final HttpClient httpClient;

	private SimpleStaticFileServer(Path rootDirectory, HttpServer server, HttpClient httpClient) {
		this.rootDirectory = rootDirectory;
		this.server = server;
		this.httpClient = httpClient;
	}

	/**
	 * Starts a local static-file server on an ephemeral loopback port.
	 *
	 * <p>
	 * Port {@code 0} asks the operating system to choose a free port, which keeps
	 * tests deterministic and avoids hard-coded local port conflicts.
	 * </p>
	 *
	 * @param rootDirectory the directory to serve
	 * @return a started server
	 * @throws IOException when the server cannot be created
	 */
	public static SimpleStaticFileServer start(Path rootDirectory) throws IOException {
		var normalizedRoot = rootDirectory.toAbsolutePath().normalize();
		var address = new InetSocketAddress(InetAddress.getLoopbackAddress(), 0);
		var server = SimpleFileServer.createFileServer(address, normalizedRoot, SimpleFileServer.OutputLevel.NONE);
		var httpClient = HttpClient.newHttpClient();
		server.start();
		return new SimpleStaticFileServer(normalizedRoot, server, httpClient);
	}

	/**
	 * Returns the directory being served.
	 *
	 * @return the normalized root directory
	 */
	public Path rootDirectory() {
		return rootDirectory;
	}

	/**
	 * Returns the selected local port.
	 *
	 * @return the port chosen for this server
	 */
	public int port() {
		return server.getAddress().getPort();
	}

	/**
	 * Builds a URI for a file path on the local server.
	 *
	 * @param path the request path, with or without a leading slash
	 * @return URI pointing at this local server
	 */
	public URI uri(String path) {
		var normalizedPath = path.startsWith("/") ? path : "/" + path;
		var host = server.getAddress().getAddress().getHostAddress();
		try {
			return new URI("http", null, host, port(), normalizedPath, null, null);
		}
		catch (URISyntaxException exception) {
			throw new IllegalArgumentException("invalid request path: " + path, exception);
		}
	}

	/**
	 * Requests a static file from the server.
	 *
	 * @param path the request path, with or without a leading slash
	 * @return status code and body returned by the server
	 * @throws IOException when the HTTP request fails
	 * @throws InterruptedException when the request is interrupted
	 */
	public StaticFileResponse get(String path) throws IOException, InterruptedException {
		var request = HttpRequest.newBuilder(uri(path))
				.GET()
				.build();
		var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		return new StaticFileResponse(response.statusCode(), response.body());
	}

	/**
	 * Stops the server immediately.
	 */
	@Override
	public void close() {
		server.stop(0);
	}
}
