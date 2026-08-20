package net.jrodolfo.java_evolution.java01;

import java.io.IOException;

/**
 * Refreshes checked exceptions and basic exception wrapping.
 */
public class ExceptionHandlingBasicsExamples {

	/**
	 * Declares a checked exception for callers to handle or propagate.
	 *
	 * @param available whether the resource is available
	 * @return loaded value
	 * @throws IOException when the resource is unavailable
	 */
	public String loadRequiredValue(boolean available) throws IOException {
		if (!available) {
			throw new IOException("value is unavailable");
		}
		return "loaded";
	}

	/**
	 * Converts a checked exception into a domain-specific unchecked exception.
	 *
	 * @return loaded value
	 */
	public String loadOrThrowDomainFailure() {
		try {
			return loadRequiredValue(false);
		}
		catch (IOException exception) {
			throw new ConfigurationException("configuration could not be loaded");
		}
	}

	static class ConfigurationException extends RuntimeException {
		ConfigurationException(String message) {
			super(message);
		}
	}
}
