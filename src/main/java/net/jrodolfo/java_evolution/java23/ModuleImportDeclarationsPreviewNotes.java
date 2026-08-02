package net.jrodolfo.java_evolution.java23;

/**
 * Explains module import declarations as a Java 23 preview feature.
 *
 * <p>
 * Java imports traditionally name packages or individual types. Module import
 * declarations explore a broader import form: importing the public packages
 * exported by a module. This is useful when source code intentionally uses a
 * broad module surface, especially in small programs and learning examples.
 * </p>
 */
public class ModuleImportDeclarationsPreviewNotes {

	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "import all packages exported by a module with one module import declaration";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "preview in Java 23 and final in Java 25";
	}
}
