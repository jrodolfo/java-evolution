package net.jrodolfo.java_evolution.java07;

import static net.jrodolfo.java_evolution.java07.ExceptionHandlingExamples.FailureMode.IO;
import static net.jrodolfo.java_evolution.java07.ExceptionHandlingExamples.FailureMode.NONE;
import static net.jrodolfo.java_evolution.java07.ExceptionHandlingExamples.FailureMode.SQL;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ExceptionHandlingExamplesTest {

	private final ExceptionHandlingExamples examples = new ExceptionHandlingExamples();

	@Test
	void multiCatchSharesRecoveryForDifferentCheckedExceptions() {
		assertThat(examples.recoverWithMultiCatch(IO))
				.as("One multi-catch block should handle IOException")
				.isEqualTo("recovered from IOException");
		assertThat(examples.recoverWithMultiCatch(SQL))
				.as("The same multi-catch block should handle SQLException")
				.isEqualTo("recovered from SQLException");
	}

	@Test
	void preciseRethrowKeepsCheckedExceptionTypesVisible() throws IOException, SQLException {
		examples.rethrowPrecisely(NONE);

		Exception ioFailure = captureRethrow(IO);
		Exception sqlFailure = captureRethrow(SQL);

		assertThat(ioFailure)
				.as("Precise rethrow should allow IOException to remain part of the method contract")
				.isInstanceOf(IOException.class);

		assertThat(sqlFailure)
				.as("Precise rethrow should allow SQLException to remain part of the method contract")
				.isInstanceOf(SQLException.class);
	}

	private Exception captureRethrow(ExceptionHandlingExamples.FailureMode failure) {
		try {
			examples.rethrowPrecisely(failure);
		}
		catch (Exception exception) {
			return exception;
		}
		return null;
	}
}
