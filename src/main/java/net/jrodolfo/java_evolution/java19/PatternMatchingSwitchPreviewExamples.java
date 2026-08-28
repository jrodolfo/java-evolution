package net.jrodolfo.java_evolution.java19;

/**
 * Demonstrates the continued preview of pattern matching for switch in Java 19.
 *
 * <p>
 * Type-based dispatch used to be written with chains of {@code instanceof}
 * checks and casts. Pattern matching for {@code switch} lets the switch itself
 * express the type patterns, making this style of branching more compact and
 * easier to audit.
 * </p>
 *
 * <p>
 * Pattern matching for switch became final later, in Java 21. This example uses
 * the final syntax available in JDK 26.
 * </p>
 */
public class PatternMatchingSwitchPreviewExamples {

	/**
	 * Switches over a sealed hierarchy with type patterns.
	 *
	 * @param event the event to describe
	 * @return a type-specific event description
	 */
	public String describe(Event event) {
		return switch (event) {
			case Login login -> "login by " + login.username();
			case Logout logout -> "logout by " + logout.username();
		};
	}

	/**
	 * Closed event hierarchy used to demonstrate exhaustive switch handling.
	 */
	public sealed interface Event permits Login, Logout {
	}

	/**
	 * Event representing a user login.
	 *
	 * @param username the user name
	 */
	public record Login(String username) implements Event {
	}

	/**
	 * Event representing a user logout.
	 *
	 * @param username the user name
	 */
	public record Logout(String username) implements Event {
	}
}
