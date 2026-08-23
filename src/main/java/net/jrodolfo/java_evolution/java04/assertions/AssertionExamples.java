package net.jrodolfo.java_evolution.java04.assertions;

/**
 * Demonstrates assertions, introduced in J2SE 1.4.
 */
public class AssertionExamples {

	/**
	 * Shows whether assertions are enabled for this class.
	 *
	 * @return {@code true} when the JVM has enabled assertions for this class
	 */
	public boolean assertionsEnabled() {
		boolean enabled = false;
		assert enabled = true;
		return enabled;
	}

	/**
	 * Uses an assertion for an internal invariant.
	 *
	 * @param available the total capacity available to the component
	 * @param reserved the portion already reserved by earlier internal code
	 * @return remaining capacity
	 */
	public int remainingCapacity(int available, int reserved) {
		assert reserved <= available : "reserved capacity cannot exceed available capacity";
		return available - reserved;
	}

	/**
	 * Uses ordinary validation for public input.
	 *
	 * @param username user-provided text
	 * @return trimmed username
	 */
	public String normalizeUsername(String username) {
		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("username is required");
		}
		return username.trim();
	}
}
