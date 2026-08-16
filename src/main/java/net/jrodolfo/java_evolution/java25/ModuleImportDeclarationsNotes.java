package net.jrodolfo.java_evolution.java25;

/**
 * Explains module import declarations, finalized in Java 25.
 *
 * <p>
 * Java imports usually name packages or individual types. Module import
 * declarations let source code import the public top-level classes and
 * interfaces from packages exported by a named module. This can reduce ceremony
 * in small programs and learning examples that naturally use types from several
 * packages in the same module.
 * </p>
 *
 * <p>
 * For example, code that uses {@code List}, {@code Map}, and {@code Stream}
 * might otherwise need imports from {@code java.util} and
 * {@code java.util.stream}. In Java 25, {@code import module java.base;} imports
 * the public top-level types exported by {@code java.base}, including those
 * packages.
 * </p>
 */
public class ModuleImportDeclarationsNotes {
	/**
	 * Explains the feature goal.
	 *
	 * @return a short explanation
	 */
	public String purpose() {
		return "reduce import ceremony when a source file uses public top-level types from several packages exported by one module";
	}

	/**
	 * Explains what a module import declaration makes available.
	 *
	 * @return a short semantic explanation
	 */
	public String semantics() {
		return "import module makes public top-level classes and interfaces from exported packages of the named module available on demand";
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
