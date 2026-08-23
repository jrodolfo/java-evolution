package net.jrodolfo.java_evolution.java06.compiler_api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 * Demonstrates the Java 6 Compiler API with real source files and structured
 * diagnostics.
 */
public class CompilerApiExamples {

	/**
	 * Checks whether the current runtime image provides the standard system
	 * compiler.
	 *
	 * @return {@code true} when {@link ToolProvider#getSystemJavaCompiler()} can
	 *         locate a compiler
	 */
	public boolean systemCompilerIsAvailable() {
		return ToolProvider.getSystemJavaCompiler() != null;
	}

	/**
	 * Compiles Java source files into a class-output directory.
	 *
	 * @param sourceFiles Java source files to compile
	 * @param outputDirectory directory where generated {@code .class} files should
	 *        be written
	 * @return the compilation outcome and collected diagnostics
	 * @throws IOException when the file manager cannot access the supplied files
	 * @throws IllegalStateException when the code is running without a system
	 *         compiler
	 */
	public CompilationResult compile(List<File> sourceFiles, File outputDirectory) throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new IllegalStateException("No system Java compiler is available. Run this example on a JDK.");
		}

		if (!outputDirectory.exists() && !outputDirectory.mkdirs()) {
			throw new IOException("Could not create compiler output directory: " + outputDirectory);
		}
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, Locale.ROOT, null);
		try {
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(outputDirectory));
			Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjectsFromFiles(sourceFiles);
			JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics,
					Collections.<String>emptyList(), null, units);
			boolean successful = task.call();

			return new CompilationResult(successful, diagnostics.getDiagnostics());
		} finally {
			fileManager.close();
		}
	}

	/**
	 * Immutable result returned by a compiler invocation.
	 */
	public static final class CompilationResult {

		private final boolean successful;
		private final List<Diagnostic<? extends JavaFileObject>> diagnostics;

		private CompilationResult(boolean successful, List<Diagnostic<? extends JavaFileObject>> diagnostics) {
			this.successful = successful;
			this.diagnostics = Collections.unmodifiableList(
					new ArrayList<Diagnostic<? extends JavaFileObject>>(diagnostics));
		}

		/**
		 * @return {@code true} when the compiler accepted the supplied source files
		 */
		public boolean successful() {
			return successful;
		}

		/**
		 * @return all diagnostics reported by the compiler
		 */
		public List<Diagnostic<? extends JavaFileObject>> diagnostics() {
			return diagnostics;
		}

		/**
		 * @return diagnostics whose kind is {@link Diagnostic.Kind#ERROR}
		 */
		public List<Diagnostic<? extends JavaFileObject>> errors() {
			List<Diagnostic<? extends JavaFileObject>> errors = new ArrayList<Diagnostic<? extends JavaFileObject>>();
			for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
				if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
					errors.add(diagnostic);
				}
			}
			return errors;
		}
	}
}
