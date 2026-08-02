package net.jrodolfo.java_evolution.java21;

/**
 * Demonstrates pattern matching for switch, finalized in Java 21.
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

	public sealed interface Command permits Start, Stop, Restart {
	}

	public record Start(String service) implements Command {
	}

	public record Stop(String service) implements Command {
	}

	public record Restart(String service) implements Command {
	}
}
