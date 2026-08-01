package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class DateTimeApiExamplesTest {

	private final DateTimeApiExamples examples = new DateTimeApiExamples();

	@Test
	void localDateRepresentsADateWithoutTimeOrZone() {
		// When
		LocalDate date = examples.createLocalDate(2026, 8, 1);

		// Then
		assertThat(date)
				.as("LocalDate should hold only year, month, and day")
				.isEqualTo(LocalDate.of(2026, 8, 1));
	}

	@Test
	void dateOperationsReturnNewImmutableInstances() {
		// Given
		LocalDate originalDate = LocalDate.of(2026, 8, 1);

		// When
		LocalDate updatedDate = examples.addDays(originalDate, 10);

		// Then
		assertThat(updatedDate)
				.as("plusDays should return a new date")
				.isEqualTo(LocalDate.of(2026, 8, 11));
		assertThat(originalDate)
				.as("The original LocalDate should remain unchanged")
				.isEqualTo(LocalDate.of(2026, 8, 1));
	}

	@Test
	void periodRepresentsDateBasedAmountOfTime() {
		// Given
		LocalDate start = LocalDate.of(2026, 1, 1);
		LocalDate end = LocalDate.of(2027, 3, 15);

		// When
		Period period = examples.periodBetween(start, end);

		// Then
		assertThat(period)
				.as("Period should represent years, months, and days")
				.isEqualTo(Period.of(1, 2, 14));
	}

	@Test
	void durationRepresentsTimeBasedAmountOfTime() {
		// Given
		LocalDateTime start = LocalDateTime.of(2026, 8, 1, 10, 0);
		LocalDateTime end = LocalDateTime.of(2026, 8, 1, 12, 30);

		// When
		Duration duration = examples.durationBetween(start, end);

		// Then
		assertThat(duration)
				.as("Duration should represent hours, minutes, seconds, and nanos")
				.isEqualTo(Duration.ofMinutes(150));
	}

	@Test
	void formatterConvertsDateToIsoText() {
		// Given
		LocalDate date = LocalDate.of(2026, 8, 1);

		// When
		String formattedDate = examples.formatIsoDate(date);

		// Then
		assertThat(formattedDate)
				.as("ISO_LOCAL_DATE should format the date as yyyy-MM-dd")
				.isEqualTo("2026-08-01");
	}

	@Test
	void formatterParsesCustomDateText() {
		// When
		LocalDate date = examples.parseBrazilianDate("01/08/2026");

		// Then
		assertThat(date)
				.as("The custom formatter should parse dd/MM/yyyy text")
				.isEqualTo(LocalDate.of(2026, 8, 1));
	}

	@Test
	void zonedDateTimeCombinesLocalDateTimeWithZone() {
		// Given
		LocalDateTime localDateTime = LocalDateTime.of(2026, 8, 1, 14, 30);

		// When
		ZonedDateTime zonedDateTime = examples.createZonedDateTime(localDateTime, "America/Sao_Paulo");

		// Then
		assertThat(zonedDateTime.toLocalDateTime())
				.as("The local date-time fields should be preserved")
				.isEqualTo(localDateTime);
		assertThat(zonedDateTime.getZone().getId())
				.as("The zone should identify the region used to interpret the local time")
				.isEqualTo("America/Sao_Paulo");
	}
}
