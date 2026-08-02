package net.jrodolfo.java_evolution.java19;

/**
 * Demonstrates the continued preview of pattern matching for switch in Java 19.
 *
 * <p>
 * Pattern matching for switch became final later, in Java 21. This example uses
 * the final syntax available in JDK 25.
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

	public sealed interface Event permits Login, Logout {
	}

	public record Login(String username) implements Event {
	}

	public record Logout(String username) implements Event {
	}
}
