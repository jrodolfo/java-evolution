package net.jrodolfo.java_evolution.java12;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Demonstrates compact number formatting, introduced in Java 12.
 *
 * <p>
 * Before Java 12, displaying large numbers in user-friendly forms such as
 * {@code 1K}, {@code 1M}, or localized long text usually required custom
 * formatting logic or a third-party library.
 * </p>
 *
 * <p>
 * Java 12 added compact number formatting to {@link NumberFormat}. It solves
 * this presentation problem with locale-aware short and long styles.
 * </p>
 */
public class CompactNumberFormatExamples {

	/**
	 * Formats a number with the short compact style.
	 *
	 * @param number the number to format
	 * @param locale the locale that controls formatting rules
	 * @return compact formatted text
	 */
	public String shortCompactNumber(long number, Locale locale) {
		NumberFormat formatter = NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.SHORT);
		return formatter.format(number);
	}

	/**
	 * Formats a number with the long compact style.
	 *
	 * @param number the number to format
	 * @param locale the locale that controls formatting rules
	 * @return compact formatted text
	 */
	public String longCompactNumber(long number, Locale locale) {
		NumberFormat formatter = NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.LONG);
		return formatter.format(number);
	}
}
