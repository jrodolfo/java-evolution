package net.jrodolfo.java_evolution.java05;

import java.util.Locale;

/**
 * Demonstrates formatted output APIs introduced in Java 5.
 */
public class FormattingExamples {

	/**
	 * Formats a currency-like amount with grouping and two decimal places.
	 *
	 * @param amount amount to format
	 * @return formatted amount
	 */
	public String formatAmount(double amount) {
		return String.format(Locale.US, "%,.2f", amount);
	}

	/**
	 * Reorders arguments with explicit argument indexes.
	 *
	 * @param first first value
	 * @param second second value
	 * @return formatted text with arguments reversed
	 */
	public String reverseOrder(String first, String second) {
		return String.format("%2$s before %1$s", first, second);
	}
}
