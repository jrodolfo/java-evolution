package net.jrodolfo.java_evolution.java08;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Demonstrates {@link CompletableFuture}, introduced in Java 8 for composing
 * asynchronous computations.
 *
 * <p>
 * The examples use a caller-provided {@link Executor} so tests can run
 * deterministically without relying on timing or background thread scheduling.
 * </p>
 */
public class CompletableFutureExamples {

	private final Executor executor;

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
