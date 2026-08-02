package net.jrodolfo.java_evolution.java21;

/**
 * Demonstrates pattern matching for switch, finalized in Java 21.
 *
 * <p>
 * Before this feature, type-based dispatch usually meant an {@code if} chain
 * with repeated {@code instanceof} checks and casts. Pattern matching for
 * {@code switch} lets the switch expression branch directly on type patterns.
 * </p>
 *
 * <p>
 * The feature is especially strong with sealed hierarchies because the compiler
 * knows the permitted implementations and can help verify exhaustive handling.
 * </p>
 */
public class PatternMatchingSwitchExamples {

	/**
	 * Handles a sealed command hierarchy with switch patterns.
	 *
	 * @param command the command to describe
	 * @return a command description
	 */
	public String describe(Command command) {
		return switch (command) {
			case Start start -> "start " + start.service();
			case Stop stop -> "stop " + stop.service();
			case Restart restart -> "restart " + restart.service();
		};
	}

	/**
	 * Closed command hierarchy used to demonstrate exhaustive switch handling.
	 */
	public sealed interface Command permits Start, Stop, Restart {
	}

	/**
	 * Starts a service.
	 *
	 * @param service service name
	 */
	public record Start(String service) implements Command {
	}

	/**
	 * Stops a service.
	 *
	 * @param service service name
	 */
	public record Stop(String service) implements Command {
	}

	/**
	 * Restarts a service.
	 *
	 * @param service service name
	 */
	public record Restart(String service) implements Command {
	}
}
