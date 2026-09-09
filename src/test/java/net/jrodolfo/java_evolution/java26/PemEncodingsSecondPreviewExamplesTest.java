package net.jrodolfo.java_evolution.java26;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PemEncodingsSecondPreviewExamplesTest {

	private final PemEncodingsSecondPreviewExamples examples = new PemEncodingsSecondPreviewExamples();

	@Test
	void childProcessDemonstratesInMemoryPemRoundTrip(@TempDir Path workspace) throws Exception {
		assumeTrue(Runtime.version().feature() == 26,
				"Java 26 preview APIs require a matching JDK 26 preview compiler");
		PemEncodingsSecondPreviewExamples.PemWorkflowResult result = examples.runPemWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the generated source should compile when Java 26 preview APIs are enabled")
				.isZero();
		assertThat(result.compilation().output())
				.as("javac should identify the child source as preview API usage")
				.contains("uses preview features of Java SE 26");
		assertThat(Files.exists(result.sourceFile()))
				.as("the example should write preview source into the temporary workspace")
				.isTrue();
		assertThat(Files.exists(result.classesDirectory()))
				.as("the child compilation should produce a classes directory")
				.isTrue();

		assertThat(result.execution().exitCode())
				.as("the child JVM should run successfully with --enable-preview")
				.isZero();
		assertThat(result.execution().output())
				.as("the PEM API should preserve the label and decoded payload")
				.contains("label=LEARNING OBJECT")
				.contains("payload-matches=true")
				.contains("has-boundaries=true");
	}

	@Test
	void probeSourceUsesRealPemPreviewApi() {
		assertThat(examples.probeSource())
				.as("the child source should use the Java 26 PEM encoder and decoder APIs")
				.contains("import java.security.PEM;")
				.contains("PEMEncoder.of()")
				.contains("PEMDecoder.of()")
				.contains("encodeToString")
				.contains("decode");
	}

	@Test
	void exampleExplainsPreviewBoundaryAndScope() {
		assertThat(examples.purpose())
				.as("PEM examples should explain standardized cryptographic text encoding support")
				.contains("cryptographic objects")
				.contains("PEM");
		assertThat(examples.status())
				.as("PEM support should be marked as second preview in Java 26")
				.contains("second preview")
				.contains("Java 26")
				.contains("Java 25");
		assertThat(examples.previewBoundary())
				.as("the preview API should be isolated from the main Maven build")
				.contains("child JVM")
				.contains("--enable-preview");
		assertThat(examples.exampleScope())
				.as("the example should keep cryptographic scope intentionally small")
				.contains("in-memory PEM envelope")
				.contains("without parsing cryptographic formats")
				.contains("external files");
	}
}
