package net.jrodolfo.java_evolution.java07;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Demonstrates Java 7 exception-handling improvements from Project Coin.
 */
public class ExceptionHandlingExamples {

	/**
	 * Handles two checked exception types with one catch block.
	 *
	 * @param failure failure mode
	 * @return shared recovery message
	 */
	public String recoverWithMultiCatch(FailureMode failure) {
		try {
			fail(failure);
			return "ok";
		}
		catch (IOException | SQLException exception) {
			return "recovered from " + exception.getClass().getSimpleName();
		}
	}

	/**
	 * Rethrows an exception parameter without widening the declared throws clause
	 * beyond the precise checked exceptions that can be thrown.
	 *
	 * @param failure failure mode
	 * @throws IOException when I/O fails
	 * @throws SQLException when database work fails
	 */
	public void rethrowPrecisely(FailureMode failure) throws IOException, SQLException {
		try {
			fail(failure);
		}
		catch (Exception exception) {
			throw exception;
		}
	}

	private void fail(FailureMode failure) throws IOException, SQLException {
		if (failure == FailureMode.IO) {
			throw new IOException("disk");
		}
		if (failure == FailureMode.SQL) {
			throw new SQLException("database");
		}
	}

	/**
	 * Failure modes used by the example.
	 */
	public enum FailureMode {
		NONE,
		IO,
		SQL
	}
}
