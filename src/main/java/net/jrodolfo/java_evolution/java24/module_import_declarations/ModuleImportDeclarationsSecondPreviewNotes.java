package net.jrodolfo.java_evolution.java24.module_import_declarations;

/**
 * Explains module import declarations second preview in Java 24.
 *
 * <p>
 * Module import declarations let source code import public top-level types from
 * packages exported by a named module. Java 24 kept the feature in preview
 * before finalization in Java 25.
 * </p>
 *
 * <p>
 * This repository keeps the Java 24 step as notes because the feature became
 * final in Java 25, where the final notes explain the stable form.
 * </p>
 */
public class ModuleImportDeclarationsSecondPreviewNotes {
	/**
	 * Explains the import problem.
	 *
	 * @return a short explanation
	 */
	public String problemSolved() {
		return "source files can need many ordinary imports when they use types from several packages in the same module";
	}

	/**
	 * Explains the older import model.
	 *
	 * @return a short explanation
	 */
	public String olderImportModel() {
		return "ordinary imports name one type or one package, such as java.util.List or java.util.*";
	}

	/**
	 * Explains what a module import declaration makes available.
	 *
	 * @return a short semantic explanation
	 */
	public String moduleImportModel() {
		return "import module makes public top-level classes and interfaces from exported packages of the named module available on demand";
	}

	/**
	 * Shows the declaration shape.
	 *
	 * @return sample module import declaration
	 */
	public String syntaxShape() {
		return "import module java.base;";
	}

	/**
	 * Explains why Java 24 keeps this entry as a bridge note.
	 *
	 * @return a short explanation
	 */
	public String previewStep() {
		return "Java 24 was the second preview, after Java 23 and before finalization in Java 25";
	}

	/**
	 * Describes the Java 24 maturity level.
	 *
	 * @return a short status note
	 */
	public String secondPreviewStatus() {
		return "module import declarations were in second preview in Java 24 and final in Java 25";
	}

	/**
	 * Points learners to the final Java 25 notes.
	 *
	 * @return a short next-step note
	 */
	public String nextStep() {
		return "run ModuleImportDeclarationsExamples in Java 25 for the final executable feature demonstration";
	}
}
