package net.jrodolfo.java_evolution.java07;

/**
 * Demonstrates strings in {@code switch}, introduced in Java 7.
 */
public class StringSwitchExamples {

	/**
	 * Classifies a command using a classic switch statement on {@link String}.
	 *
	 * @param command command text
	 * @return command category
	 */
	public String classifyCommand(String command) {
		switch (command) {
			case "start":
			case "resume":
				return "activates work";
			case "stop":
			case "pause":
				return "halts work";
			default:
				return "unknown command";
		}
	}
}
