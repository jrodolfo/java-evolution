package net.jrodolfo.java_evolution.java27.pem_encodings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import net.jrodolfo.java_evolution.java27.Java27ChildProcess;

/** Demonstrates the third preview of PEM encodings from JEP 538. */
public class PemEncodingsThirdPreviewExamples {

	/** Compiles and runs an in-memory PEM round trip. */
	public Java27ChildProcess.Result run(Path workspace) throws IOException, InterruptedException {
		Path source = workspace.resolve("PemProbe.java");
		Path classes = workspace.resolve("classes");
		Files.createDirectories(classes);
		Files.writeString(source, probeSource(), StandardCharsets.UTF_8);
		Java27ChildProcess.Result compilation = Java27ChildProcess.run(Java27ChildProcess.tool("javac"),
				"--enable-preview", "--release", "27", "-d", classes.toString(), source.toString());
		if (compilation.exitCode() != 0) return compilation;
		return Java27ChildProcess.run(Java27ChildProcess.tool("java"), "--enable-preview", "-cp",
				classes.toString(), "PemProbe");
	}

	/** Returns source using the Java 27 PEM encoder and decoder. */
	public String probeSource() {
		return """
				import java.security.PEM;
				import java.security.PEMDecoder;
				import java.security.PEMEncoder;

				public class PemProbe {
				    public static void main(String[] args) {
				        byte[] der = {1, 2, 3, 4};
				        PEM original = new PEM("LEARNING OBJECT", java.util.Base64.getEncoder().encodeToString(der));
				        String text = PEMEncoder.of().encodeToString(original);
				        PEM decoded = PEMDecoder.of().decode(text, PEM.class);
				        System.out.println("label=" + decoded.type());
				        System.out.println("payload=" + java.util.Arrays.equals(der, decoded.decode()));
				        System.out.println("boundaries=" + text.contains("-----BEGIN LEARNING OBJECT-----"));
				    }
				}
				""";
	}

	/** Explains the preview boundary and deterministic scope. */
	public String boundary() {
		return "Java 27 PEM APIs are compiled and run in an isolated child JVM with --enable-preview using only in-memory bytes";
	}
}
