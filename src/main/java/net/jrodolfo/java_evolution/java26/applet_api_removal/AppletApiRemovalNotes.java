package net.jrodolfo.java_evolution.java26.applet_api_removal;

/**
 * Explains removal of the Applet API in Java 26.
 */
public class AppletApiRemovalNotes {

	/**
	 * Explains what applets represented historically.
	 *
	 * @return a short historical note
	 */
	public String historicalContext() {
		return "applets were browser-embedded Java programs from the plugin era of the web";
	}

	/**
	 * Describes the Java 26 change.
	 *
	 * @return a short removal note
	 */
	public String java26Change() {
		return "Java 26 removes the Applet API after a long deprecation-for-removal period";
	}

	/**
	 * Explains the learning value.
	 *
	 * @return a short migration note
	 */
	public String migrationLesson() {
		return "removed APIs show why deprecation-for-removal warnings should be treated as real migration signals";
	}
}
