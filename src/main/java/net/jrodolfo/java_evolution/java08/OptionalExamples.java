package net.jrodolfo.java_evolution.java08;

import java.util.Optional;

public class OptionalExamples {

	public Optional<String> findEmail(User user) {
		return Optional.ofNullable(user)
				.map(User::email);
	}

	public String displayEmail(User user) {
		return findEmail(user)
				.orElse("email not provided");
	}

	public String displayEmailGeneratedLazily(User user) {
		return findEmail(user)
				.orElseGet(() -> "email generated only when Optional is empty");
	}

	public String requireEmail(User user) {
		return findEmail(user)
				.orElseThrow(() -> new IllegalArgumentException("email is required"));
	}

	public Optional<String> normalizeEmail(User user) {
		return findEmail(user)
				.map(String::trim)
				.map(String::toLowerCase)
				.filter(email -> email.contains("@"));
	}

	public Optional<String> findCompanyDomain(User user) {
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

	public static class User {
		private final String name;
		private final String email;

		public User(String name, String email) {
			this.name = name;
			this.email = email;
		}

		public String name() {
			return name;
		}

		public String email() {
			return email;
		}
	}
}
