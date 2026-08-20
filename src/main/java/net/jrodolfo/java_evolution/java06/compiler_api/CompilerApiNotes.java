package net.jrodolfo.java_evolution.java06.compiler_api;

/**
 * Explains the Java Compiler API introduced in Java 6.
 */
public class CompilerApiNotes {

	/**
	 * @return the pre-Java-6 compiler invocation problem
	 */
	public String problemSolved() {
		return "tools often had to launch javac externally or use implementation-specific compiler classes";
	}

	/**
	 * @return the central API type
	 */
	public String centralType() {
		return "javax.tools.JavaCompiler is obtained through ToolProvider and invoked with file managers and diagnostics";
	}

	/**
	 * @return the role of diagnostics
	 */
	public String diagnosticsRole() {
		return "DiagnosticListener lets tools collect structured compiler errors and warnings";
	}

	/**
	 * @return why this feature is represented as notes
	 */
	public String repositoryDecision() {
		return "a faithful compiler API demo is a small tool with source files and class output, not a source-code string";
	}
}
