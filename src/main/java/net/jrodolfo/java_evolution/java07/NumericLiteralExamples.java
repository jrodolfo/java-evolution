package net.jrodolfo.java_evolution.java07;

/**
 * Demonstrates binary literals and underscores in numeric literals, introduced
 * in Java 7.
 */
public class NumericLiteralExamples {

	/**
	 * Uses a binary literal for bit flags.
	 *
	 * @return read and execute permission bits
	 */
	public int readAndExecuteMask() {
		return 0b101;
	}

	/**
	 * Uses underscores to group digits in a large numeric literal.
	 *
	 * @return a readable large number
	 */
	public long groupedLargeNumber() {
		return 1_000_000_000L;
	}
}
