package net.jrodolfo.java_evolution.java23.module_import_declarations;

/**
 * Explains module import declarations as a Java 23 preview feature.
 *
 * <p>
 * Java imports traditionally name individual types or packages. Module import
 * declarations explore a broader import form: making public top-level types
 * from packages exported by a named module available on demand.
 * </p>
 */
public class ModuleImportDeclarationsPreviewNotes {

	/**
	 * Explains the older import model.
	 *
	 * @return a short explanation
	 */
	public String olderImportModel() {
		return "traditional Java imports name individual types or packages";
	}

	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String whatJavaPreviewed() {
		return "import module makes public top-level types from exported packages of a named module available on demand";
	}

	/**
	 * Explains the educational use case.
	 *
	 * @return a short explanation
	 */
	public String usefulContext() {
		return "module imports can reduce setup noise in small programs, scripts, tutorials, and learning examples";
	}

	/**
	 * Points learners to the final Java 25 notes.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "run ModuleImportDeclarationsExamples in Java 25 for the final executable feature demonstration";
	}

	/**
	 * Describes the feature status across releases.
	 *
	 * @return a short status note
	 */
	public String status() {
		return "preview in Java 23, second preview in Java 24, and final in Java 25";
	}
}
