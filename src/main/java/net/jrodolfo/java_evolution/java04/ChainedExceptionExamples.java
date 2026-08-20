package net.jrodolfo.java_evolution.java04;

import java.io.IOException;

/**
 * Demonstrates chained exceptions, standardized in J2SE 1.4.
 */
public class ChainedExceptionExamples {

	/**
	 * Wraps a low-level checked exception while preserving it as the cause.
	 */
	public void wrapWithCause() {
		try {
			throw new IOException("disk failed");
		}
		catch (IOException exception) {
			throw new ImportException("import failed", exception);
		}
	}

	static class ImportException extends RuntimeException {
		ImportException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
