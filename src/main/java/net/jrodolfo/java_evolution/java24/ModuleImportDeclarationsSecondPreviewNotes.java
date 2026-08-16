package net.jrodolfo.java_evolution.java24;

/**
 * Explains module import declarations second preview in Java 24.
 *
 * <p>
 * Module import declarations allow source code to import the public packages
 * exported by a module with one declaration. Java 24 refined the preview before
 * finalization in Java 25.
 * </p>
 */
public class ModuleImportDeclarationsSecondPreviewNotes {
	/**
	 * Explains the import problem being refined by the preview.
	 *
	 * @return a short feature goal
	 */
	public String featureGoal() {
		return "import public top-level types from exported packages of a module with one declaration";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "second preview in Java 24 and final in Java 25";
	}

	/**
	 * Points learners to the final Java 25 notes.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "read ModuleImportDeclarationsNotes in Java 25 for the final feature explanation";
	}
}
