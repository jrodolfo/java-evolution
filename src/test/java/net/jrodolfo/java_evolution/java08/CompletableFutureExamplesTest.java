package net.jrodolfo.java_evolution.java08;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.Executor;

import org.junit.jupiter.api.Test;

class CompletableFutureExamplesTest {

	// The examples accept an Executor so tests can run deterministically. Real
	// production code would normally use a real executor or the common pool.
	private final Executor sameThreadExecutor = Runnable::run;
	private final CompletableFutureExamples examples = new CompletableFutureExamples(sameThreadExecutor);

	@Test
	void supplyAsyncCreatesAFutureValue() {
		// When
		String name = examples.fetchNameAsync().join();

		// Then
		assertThat(name)
				.as("supplyAsync should create a CompletableFuture containing the supplied value")
				.isEqualTo("rodolfo");
	}

	@Test
	void thenApplyTransformsTheFutureValue() {
		// When
		String uppercaseName = examples.fetchUppercaseNameAsync().join();

		// Then
		assertThat(uppercaseName)
				.as("thenApply should transform the completed value")
				.isEqualTo("RODOLFO");
	}

	@Test
	void thenCombineMergesTwoIndependentFutureValues() {
		// When
		String fullName = examples.fetchFullNameAsync().join();

		// Then
		assertThat(fullName)
				.as("thenCombine should wait for both futures and combine their values")
				.isEqualTo("Rodolfo Neto");
	}

	@Test
	void exceptionallyRecoversFromFailure() {
		// When
		String recoveredValue = examples.recoverFromFailureAsync().join();

		// Then
		assertThat(recoveredValue)
				.as("exceptionally should replace a failure with a fallback value")
				.isEqualTo("fallback value");
	}

	@Test
	void joinWaitsForTheComposedFutureResult() {
		// When
		String result = examples.fetchUppercaseNameBlocking();

		// Then
		assertThat(result)
				.as("join should return the final value from the asynchronous pipeline")
				.isEqualTo("RODOLFO");
	}
}
