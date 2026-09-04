package net.jrodolfo.java_evolution.java27.lazy_constants;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import net.jrodolfo.java_evolution.java27.Java27ChildProcess;

/** Demonstrates the third preview of Lazy Constants from JEP 531. */
public class LazyConstantsThirdPreviewExamples {

	/** Compiles and runs the Java 27 preview probe. */
	public Java27ChildProcess.Result run(Path workspace) throws IOException, InterruptedException {
		Path source = workspace.resolve("LazyConstantsProbe.java");
		Path classes = workspace.resolve("classes");
		Files.createDirectories(classes);
		Files.writeString(source, probeSource(), StandardCharsets.UTF_8);
		Java27ChildProcess.Result compilation = Java27ChildProcess.run(Java27ChildProcess.tool("javac"),
				"--enable-preview", "--release", "27", "-d", classes.toString(), source.toString());
		if (compilation.exitCode() != 0) return compilation;
		return Java27ChildProcess.run(Java27ChildProcess.tool("java"), "--enable-preview", "-cp",
				classes.toString(), "LazyConstantsProbe");
	}

	/** Returns the generated source for the preview API. */
	public String probeSource() {
		return """
				import java.lang.LazyConstant;
				import java.util.concurrent.atomic.AtomicInteger;

				public class LazyConstantsProbe {
				    public static void main(String[] args) {
				        AtomicInteger count = new AtomicInteger();
				        LazyConstant<String> value = LazyConstant.of(() -> {
				            count.incrementAndGet();
				            return "java-27";
				        });
				        System.out.println("value=" + value.get());
				        System.out.println("same=" + value.get().equals("java-27"));
				        System.out.println("evaluations=" + count.get());
				    }
				}
				""";
	}

	/** Describes the release progression and preview boundary. */
	public String status() {
		return "Lazy Constants continue as a third preview in Java 27 and require matching preview compiler and runtime flags";
	}
}
