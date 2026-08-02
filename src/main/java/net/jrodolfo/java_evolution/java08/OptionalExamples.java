package net.jrodolfo.java_evolution.java08;

import java.util.Optional;

/**
 * Demonstrates {@link Optional}, introduced in Java 8 as a container for values
 * that may be absent.
 *
 * <p>
 * Before Java 8, many methods returned {@code null} when a value could not be
 * found. That made absence invisible in the method signature, so callers had
 * to remember defensive checks and failures often appeared later as
 * {@link NullPointerException}s.
 * </p>
 *
 * <p>
 * {@code Optional} solves part of that problem by making absence explicit in a
 * return type. A method returning {@code Optional<String>} communicates that a
 * value may be missing and encourages the caller to handle that case with
 * operations such as {@code map}, {@code filter}, {@code orElseGet}, and
 * {@code orElseThrow}. It is most useful as a return type, not as a replacement
 * for every nullable field or parameter.
 * </p>
 */
public class OptionalExamples {

	/**
	 * Wraps a possibly null user email in an {@link Optional}.
	 *
	 * @param user the user to inspect, which may be {@code null}
	 * @return an Optional containing the email when both user and email exist
	 */
	public Optional<String> findEmail(User user) {
		return Optional.ofNullable(user)
				.map(User::email);
	}

	/**
	 * Uses {@link Optional#orElse(Object)} to provide a default value.
	 *
	 * @param user the user to inspect
	 * @return the user's email, or a default message when the email is missing
	 */
	public String displayEmail(User user) {
		return findEmail(user)
				.orElse("email not provided");
	}

	/**
	 * Uses {@link Optional#orElseGet(java.util.function.Supplier)} to create a
	 * fallback only when the Optional is empty.
	 *
	 * @param user the user to inspect
	 * @return the user's email, or a lazily generated fallback message
	 */
	public String displayEmailGeneratedLazily(User user) {
		// orElseGet receives a Supplier, so the fallback is evaluated only if needed.
		return findEmail(user)
				.orElseGet(() -> "email generated only when Optional is empty");
	}

	/**
	 * Uses {@link Optional#orElseThrow(java.util.function.Supplier)} when the
	 * missing value should be treated as an error.
	 *
	 * @param user the user to inspect
	 * @return the required email
	 */
	public String requireEmail(User user) {
		return findEmail(user)
				.orElseThrow(() -> new IllegalArgumentException("email is required"));
	}

	/**
	 * Transforms and validates a value while it is inside an Optional.
	 *
	 * @param user the user to inspect
	 * @return a normalized email when a valid email is present
	 */
	public Optional<String> normalizeEmail(User user) {
		return findEmail(user)
				.map(String::trim)
				.map(String::toLowerCase)
				.filter(email -> email.contains("@"));
	}

	/**
	 * Uses {@link Optional#flatMap(java.util.function.Function)} when the
	 * transformation already returns an Optional.
	 *
	 * @param user the user to inspect
	 * @return the company email domain, excluding personal Gmail addresses
	 */
	public Optional<String> findCompanyDomain(User user) {
		// flatMap avoids creating Optional<Optional<String>>.
		return findEmail(user)
				.flatMap(this::domainFromEmail)
				.filter(domain -> !domain.equals("gmail.com"));
	}

	private Optional<String> domainFromEmail(String email) {
		int atSignIndex = email.indexOf('@');
		if (atSignIndex < 0 || atSignIndex == email.length() - 1) {
			return Optional.empty();
		}
		return Optional.of(email.substring(atSignIndex + 1));
	}

	/**
	 * Small Java 8-style data class used by the Optional examples.
	 *
	 * <p>
	 * This deliberately allows a nullable email so the examples can demonstrate
	 * how Optional represents missing data.
	 * </p>
	 */
	public static class User {
		private final String name;
		private final String email;

		/**
		 * Creates a user. The email is intentionally nullable so the examples can
		 * demonstrate converting legacy nullable data into {@link Optional}.
		 *
		 * @param name the user's display name
		 * @param email the user's email, or {@code null} when it is missing
		 */
		public User(String name, String email) {
			this.name = name;
			this.email = email;
		}

		/**
		 * @return the user's display name
		 */
		public String name() {
			return name;
		}

		/**
		 * @return the user's email, which may be {@code null}
		 */
		public String email() {
			return email;
		}
	}
}
