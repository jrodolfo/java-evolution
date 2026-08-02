package net.jrodolfo.java_evolution.java08;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Demonstrates {@link CompletableFuture}, introduced in Java 8 to make
 * asynchronous work easier to compose.
 *
 * <p>
 * Before Java 8, {@link java.util.concurrent.Future} could represent a result
 * that would be available later, but it did not provide a fluent way to
 * transform that result, combine it with another future, or recover from
 * failure. Code often had to block with {@code get()} or coordinate callbacks
 * manually.
 * </p>
 *
 * <p>
 * {@code CompletableFuture} solves that problem by treating asynchronous work
 * as a pipeline. A program can start work with {@code supplyAsync}, transform
 * the result with {@code thenApply}, combine independent results with
 * {@code thenCombine}, and handle errors with {@code exceptionally}. The
 * examples use a caller-provided {@link Executor} so tests can run
 * deterministically without depending on timing or background thread
 * scheduling.
 * </p>
 */
public class CompletableFutureExamples {

	private final Executor executor;

	/**
	 * Creates examples that run asynchronous stages on the supplied executor.
	 *
	 * @param executor the executor used by asynchronous examples
	 */
	public CompletableFutureExamples(Executor executor) {
		this.executor = executor;
	}

	/**
	 * Creates an asynchronous computation with
	 * {@link CompletableFuture#supplyAsync(java.util.function.Supplier, Executor)}.
	 *
	 * @return a future that eventually contains a name
	 */
	public CompletableFuture<String> fetchNameAsync() {
		return CompletableFuture.supplyAsync(() -> "rodolfo", executor);
	}

	/**
	 * Chains a transformation with {@link CompletableFuture#thenApply}.
	 *
	 * @return a future containing the uppercase name
	 */
	public CompletableFuture<String> fetchUppercaseNameAsync() {
		return fetchNameAsync()
				.thenApply(String::toUpperCase);
	}

	/**
	 * Combines two independent futures with {@link CompletableFuture#thenCombine}.
	 *
	 * @return a future containing a full name
	 */
	public CompletableFuture<String> fetchFullNameAsync() {
		CompletableFuture<String> firstName = CompletableFuture.supplyAsync(() -> "Rodolfo", executor);
		CompletableFuture<String> lastName = CompletableFuture.supplyAsync(() -> "Neto", executor);

		return firstName.thenCombine(lastName, (first, last) -> first + " " + last);
	}

	/**
	 * Recovers from a failed asynchronous computation with
	 * {@link CompletableFuture#exceptionally}.
	 *
	 * @return a future containing a fallback value
	 */
	public CompletableFuture<String> recoverFromFailureAsync() {
		return CompletableFuture.<String>supplyAsync(() -> {
			throw new IllegalStateException("remote service failed");
		}, executor).exceptionally(exception -> "fallback value");
	}

	/**
	 * Uses {@link CompletableFuture#join()} to wait for a composed future.
	 *
	 * @return the uppercase name after the asynchronous pipeline completes
	 */
	public String fetchUppercaseNameBlocking() {
		return fetchUppercaseNameAsync().join();
	}
}
