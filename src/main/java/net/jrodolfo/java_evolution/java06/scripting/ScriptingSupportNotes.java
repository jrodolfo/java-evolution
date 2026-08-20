package net.jrodolfo.java_evolution.java06.scripting;

/**
 * Explains Java 6 scripting support through JSR 223.
 */
public class ScriptingSupportNotes {

	/**
	 * @return the problem Java 6 scripting support addressed
	 */
	public String problemSolved() {
		return "Java applications needed a standard way to host scripting languages without binding to one engine-specific API";
	}

	/**
	 * @return the core Java 6 API package
	 */
	public String apiPackage() {
		return "javax.script provides ScriptEngineManager, ScriptEngine, and bindings for hosted scripts";
	}

	/**
	 * @return why script engine demos are environment-sensitive on modern JDKs
	 */
	public String modernJdkCaveat() {
		return "JDK 25 still has javax.script, but it does not guarantee the old bundled JavaScript engine from Java 6";
	}

	/**
	 * @return the practical lesson for modern code
	 */
	public String modernPractice() {
		return "treat a script engine as an explicit dependency and check whether ScriptEngineManager can find it";
	}
}
