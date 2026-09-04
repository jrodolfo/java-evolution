package net.jrodolfo.java_evolution.java27.primitive_patterns;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import net.jrodolfo.java_evolution.java27.Java27ChildProcess;

/** Demonstrates the fifth preview of primitive patterns from JEP 532. */
public class PrimitivePatternsFifthPreviewExamples {

	/** Compiles and runs a Java 27 primitive-pattern probe. */
	public Java27ChildProcess.Result run(Path workspace) throws IOException, InterruptedException {
		Path source = workspace.resolve("PrimitivePatternsProbe.java");
		Path classes = workspace.resolve("classes");
		Files.createDirectories(classes);
		Files.writeString(source, probeSource(), StandardCharsets.UTF_8);
		Java27ChildProcess.Result compilation = Java27ChildProcess.run(Java27ChildProcess.tool("javac"),
				"--enable-preview", "--release", "27", "-d", classes.toString(), source.toString());
		if (compilation.exitCode() != 0) return compilation;
		return Java27ChildProcess.run(Java27ChildProcess.tool("java"), "--enable-preview", "-cp",
				classes.toString(), "PrimitivePatternsProbe");
	}

	/** Returns source using primitive patterns in instanceof and switch. */
	public String probeSource() {
		return """
				public class PrimitivePatternsProbe {
				    public static void main(String[] args) {
				        System.out.println("fits=" + fitsByte(42));
				        System.out.println("outside=" + fitsByte(1000));
				        System.out.println("switch=" + classify(42L));
				    }
				    static String fitsByte(int value) {
				        return value instanceof byte narrowed ? "byte:" + narrowed : "outside:" + value;
				    }
				    static String classify(long value) {
				        return switch (value) {
				            case byte b -> "byte:" + b;
				            case int i -> "int:" + i;
				            default -> "long:" + value;
				        };
				    }
				}
				""";
	}

	/** Explains why preview syntax is isolated from Maven compilation. */
	public String boundary() {
		return "Java 27 primitive-pattern syntax is compiled and run in an isolated child JVM with --enable-preview";
	}
}
