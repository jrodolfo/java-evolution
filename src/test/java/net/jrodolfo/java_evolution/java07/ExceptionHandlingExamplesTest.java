package net.jrodolfo.java_evolution.java07;

import static net.jrodolfo.java_evolution.java07.ExceptionHandlingExamples.FailureMode.IO;
import static net.jrodolfo.java_evolution.java07.ExceptionHandlingExamples.FailureMode.NONE;
import static net.jrodolfo.java_evolution.java07.ExceptionHandlingExamples.FailureMode.SQL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
	void preciseRethrowKeepsIOExceptionVisible() {
		assertThatThrownBy(() -> examples.rethrowPrecisely(IO))
				.as("Precise rethrow should allow IOException to remain part of the method contract")
				.isInstanceOf(IOException.class);
	}

	@Test
	void preciseRethrowKeepsSqlExceptionVisible() {
		assertThatThrownBy(() -> examples.rethrowPrecisely(SQL))
				.as("Precise rethrow should allow SQLException to remain part of the method contract")
				.isInstanceOf(SQLException.class);
	}

	@Test
	void preciseRethrowAllowsSuccessPathWithoutWideningTheContract() {
		assertThatNoException()
				.as("The precise rethrow example should still allow the non-failing path")
				.isThrownBy(() -> examples.rethrowPrecisely(NONE));
	}
}
