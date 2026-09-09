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
				import java.security.KeyPairGenerator;
				import java.security.PEMDecoder;
				import java.security.PEMEncoder;
				import java.security.PublicKey;
				import java.util.Arrays;

				public class PemProbe {
				    public static void main(String[] args) {
				        try {
				            PublicKey original = KeyPairGenerator.getInstance("Ed25519").generateKeyPair().getPublic();
				            String text = PEMEncoder.of().encodeToString(original);
				            PublicKey decoded = PEMDecoder.of().decode(text, PublicKey.class);
				            System.out.println("key=" + Arrays.equals(original.getEncoded(), decoded.getEncoded()));
				            System.out.println("boundaries=" + text.contains("-----BEGIN PUBLIC KEY-----"));
				        }
				        catch (Exception exception) {
				            throw new RuntimeException(exception);
				        }
				    }
				}
				""";
	}

	/** Explains the preview boundary and deterministic scope. */
	public String boundary() {
		return "Java 27 PEM APIs are compiled and run in an isolated child JVM with --enable-preview using only in-memory bytes";
	}
}
