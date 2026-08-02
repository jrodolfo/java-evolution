package net.jrodolfo.java_evolution.java25;

/**
 * Explains module import declarations, finalized in Java 25.
 *
 * <p>
 * Java imports usually name packages or individual types. Module import
 * declarations let source code import the public packages exported by a module
 * with one declaration, which can reduce ceremony in small programs and
 * learning examples.
 * </p>
 */
public class ModuleImportDeclarationsNotes {
	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "import the public packages exported by a module with one declaration";
	}

	/**
	 * Shows the declaration shape.
	 *
	 * @return sample module import declaration
	 */
	public String example() {
		return "import module java.base;";
	}
}
