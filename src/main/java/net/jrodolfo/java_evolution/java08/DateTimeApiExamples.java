package net.jrodolfo.java_evolution.java08;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Demonstrates the Date and Time API introduced in Java 8 under
 * {@code java.time}.
 *
 * <p>
 * Before Java 8, date and time code usually relied on {@code java.util.Date},
 * {@code java.util.Calendar}, and {@code java.text.SimpleDateFormat}. Those
 * APIs mixed different concepts, were mutable, and made time-zone handling
 * difficult to reason about.
 * </p>
 *
 * <p>
 * The Java 8 API solves this by giving different time concepts different
 * immutable types. {@link LocalDate} represents a date without a time zone,
 * {@link LocalDateTime} represents a local date and time, {@link ZonedDateTime}
 * adds a zone, {@link Period} represents date-based amounts, and
 * {@link Duration} represents time-based amounts. The clearer types make many
 * bugs visible in the method signature.
 * </p>
 */
public class DateTimeApiExamples {

	/**
	 * Creates a date without a time zone.
	 *
	 * @param year the year
	 * @param month the month from 1 to 12
	 * @param day the day of the month
	 * @return the requested local date
	 */
	public LocalDate createLocalDate(int year, int month, int day) {
		return LocalDate.of(year, month, day);
	}

	/**
	 * Adds days to a date, returning a new instance because java.time types are
	 * immutable.
	 *
	 * @param date the original date
	 * @param days the number of days to add
	 * @return a new date after adding the requested days
	 */
	public LocalDate addDays(LocalDate date, long days) {
		return date.plusDays(days);
	}

	/**
	 * Calculates a date-based amount of time with {@link Period}.
	 *
	 * @param start the start date
	 * @param end the end date
	 * @return the period between both dates
	 */
	public Period periodBetween(LocalDate start, LocalDate end) {
		return Period.between(start, end);
	}

	/**
	 * Calculates a time-based amount of time with {@link Duration}.
	 *
	 * @param start the start date-time
	 * @param end the end date-time
	 * @return the duration between both date-times
	 */
	public Duration durationBetween(LocalDateTime start, LocalDateTime end) {
		return Duration.between(start, end);
	}

	/**
	 * Formats a date with {@link DateTimeFormatter}.
	 *
	 * @param date the date to format
	 * @return the date formatted as {@code yyyy-MM-dd}
	 */
	public String formatIsoDate(LocalDate date) {
		return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	/**
	 * Parses a date using an explicit formatter.
	 *
	 * @param text the date text in {@code dd/MM/yyyy} format
	 * @return the parsed date
	 */
	public LocalDate parseBrazilianDate(String text) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(text, formatter);
	}

	/**
	 * Creates a date-time associated with a time zone.
	 *
	 * @param dateTime the local date-time
	 * @param zoneId the zone identifier, such as {@code America/Sao_Paulo}
	 * @return the zoned date-time
	 */
	public ZonedDateTime createZonedDateTime(LocalDateTime dateTime, String zoneId) {
		return ZonedDateTime.of(dateTime, ZoneId.of(zoneId));
	}
}
