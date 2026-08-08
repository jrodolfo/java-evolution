package net.jrodolfo.java_evolution.java18.simple_web_server;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SimpleStaticFileServerTest {

	@TempDir
	private Path rootDirectory;

	@Test
	void servesStaticFileFromConfiguredDirectory() throws IOException, InterruptedException {
		Files.writeString(rootDirectory.resolve("index.html"), "<h1>Java 18 Simple Web Server</h1>");

		try (var server = SimpleStaticFileServer.start(rootDirectory)) {
			var response = server.get("/index.html");

			assertThat(response.isSuccessful())
					.as("SimpleFileServer should serve files from the configured directory")
					.isTrue();
			assertThat(response.body())
					.as("The response body should contain the static file content")
					.contains("Java 18 Simple Web Server");
			assertThat(server.rootDirectory())
					.as("The example normalizes the directory being served")
					.isEqualTo(rootDirectory.toAbsolutePath().normalize());
		}
	}

	@Test
	void returnsNotFoundForMissingStaticFile() throws IOException, InterruptedException {
		try (var server = SimpleStaticFileServer.start(rootDirectory)) {
			var response = server.get("/missing.html");

			assertThat(response.isNotFound())
					.as("A static-file server should not invent content for unknown paths")
					.isTrue();
		}
	}

	@Test
	void doesNotExposeFilesOutsideServedDirectory() throws IOException, InterruptedException {
		try (var server = SimpleStaticFileServer.start(rootDirectory)) {
			var response = server.get("/%2e%2e/pom.xml");

			assertThat(response.isSuccessful())
					.as("Encoded parent-directory traversal must not expose files outside the served root")
					.isFalse();
			assertThat(response.body())
					.as("The response should not contain project files outside the served directory")
					.doesNotContain("<artifactId>java-evolution</artifactId>");
		}
	}
}
