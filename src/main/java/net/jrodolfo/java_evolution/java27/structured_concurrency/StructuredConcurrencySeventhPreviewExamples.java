package net.jrodolfo.java_evolution.java27.structured_concurrency;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import net.jrodolfo.java_evolution.java27.Java27ChildProcess;

/** Demonstrates the seventh preview of Structured Concurrency from JEP 533. */
public class StructuredConcurrencySeventhPreviewExamples {

	/** Compiles and runs a deterministic successful structured scope. */
	public Java27ChildProcess.Result run(Path workspace) throws IOException, InterruptedException {
		Path source = workspace.resolve("StructuredConcurrencyProbe.java");
		Path classes = workspace.resolve("classes");
		Files.createDirectories(classes);
		Files.writeString(source, probeSource(), StandardCharsets.UTF_8);
		Java27ChildProcess.Result compilation = Java27ChildProcess.run(Java27ChildProcess.tool("javac"),
				"--enable-preview", "--release", "27", "-d", classes.toString(), source.toString());
		if (compilation.exitCode() != 0) return compilation;
		return Java27ChildProcess.run(Java27ChildProcess.tool("java"), "--enable-preview", "-cp",
				classes.toString(), "StructuredConcurrencyProbe");
	}

	/** Returns source using fork, join, and a structured scope. */
	public String probeSource() {
		return """
				import java.util.concurrent.ExecutionException;
				import java.util.concurrent.StructuredTaskScope;

				public class StructuredConcurrencyProbe {
				    public static void main(String[] args) throws Exception {
				        try (StructuredTaskScope<String, Void, ExecutionException> scope =
				                StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>awaitAllSuccessfulOrThrow())) {
				            StructuredTaskScope.Subtask<String> left = scope.fork(() -> "left");
				            StructuredTaskScope.Subtask<String> right = scope.fork(() -> "right");
				            scope.join();
				            System.out.println("joined=" + left.get() + "+" + right.get());
				        }
				    }
				}
				""";
	}

	/** Explains the scoped task-lifetime boundary. */
	public String boundary() {
		return "Java 27 Structured Concurrency preview code runs in a child JVM so fork and join use matching preview flags";
	}
}
