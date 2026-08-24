package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PemEncodingsPreviewExamplesTest {

	private final PemEncodingsPreviewExamples examples = new PemEncodingsPreviewExamples();

	@Test
	void childProcessRoundTripsPublicKeyThroughPemText(@TempDir Path workspace) throws Exception {
		PemEncodingsPreviewExamples.PemWorkflowResult result = examples.runPemWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the generated source should compile only when Java 25 preview APIs are enabled")
				.isZero();
		assertThat(result.compilation().output())
				.as("javac should identify the child source as preview API usage")
				.contains("uses preview features of Java SE 25");
		assertThat(Files.exists(result.sourceFile()))
				.as("the example should write the preview source into the temporary workspace")
				.isTrue();
		assertThat(Files.exists(result.classesDirectory()))
				.as("the child compilation should produce a classes directory")
				.isTrue();

		assertThat(result.execution().exitCode())
				.as("the child JVM should run successfully with --enable-preview")
				.isZero();
		assertThat(result.execution().output())
				.as("PEMEncoder and PEMDecoder should round-trip a real generated public key")
				.contains("pem-header=-----BEGIN PUBLIC KEY-----")
				.contains("decoded-algorithm=RSA")
				.contains("encoded-bytes-match=true");
		assertThat(result.execution().output())
				.as("PEMRecord should cover PEM object types without a dedicated Java security class")
				.contains("record-header=-----BEGIN TRAINING OBJECT-----")
				.contains("record-type=TRAINING OBJECT")
				.contains("record-content=AQIDBAU=");
	}

	@Test
	void probeSourceUsesTheRealJava25PemApi() {
		assertThat(examples.probeSource())
				.as("the child source should demonstrate the Java 25 preview PEM API directly")
				.contains("java.security.PEMEncoder")
				.contains("java.security.PEMDecoder")
				.contains("java.security.PEMRecord")
				.contains("KeyPairGenerator.getInstance(\"RSA\")");
	}

	@Test
	void exampleExplainsWhyPemApiMattersAndWhyPreviewIsIsolated() {
		assertThat(examples.manualParsingProblem())
				.as("the example should explain what developers had to do before the API")
				.contains("Base64")
				.contains("headers")
				.contains("parsing code by hand");
		assertThat(examples.java25Idea())
				.as("the example should name the Java 25 encoder and decoder abstractions")
				.contains("PEMEncoder")
				.contains("PEMDecoder")
				.contains("cryptographic objects");
		assertThat(examples.previewBoundary())
				.as("the example should explain why preview code is isolated from the main Maven build")
				.contains("--enable-preview")
				.contains("child JVM")
				.contains("main Maven build");
	}
}
