package net.jrodolfo.java_evolution.java07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Demonstrates try-with-resources, introduced in Java 7.
 */
public class TryWithResourcesStatementExamples {

	/**
	 * Reads the first line from text using a resource declared inside the
	 * try-with-resources header.
	 *
	 * @param text text to read
	 * @return the first line
	 * @throws IOException when reading fails
	 */
	public String readFirstLine(String text) throws IOException {
		try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
			return reader.readLine();
		}
	}

	/**
	 * Shows that cleanup failures are preserved as suppressed exceptions when the
	 * main operation already failed.
	 *
	 * @throws Exception always throws the primary operation failure
	 */
	public void failWithSuppressedCloseFailure() throws Exception {
		try (FailingResource ignored = new FailingResource()) {
			throw new IllegalStateException("operation failed");
		}
	}

	static class FailingResource implements AutoCloseable {
		@Override
		public void close() throws Exception {
			throw new IOException("close failed");
		}
	}
}
