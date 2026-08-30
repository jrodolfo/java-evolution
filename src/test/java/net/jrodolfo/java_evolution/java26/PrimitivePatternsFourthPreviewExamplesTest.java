package net.jrodolfo.java_evolution.java26;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PrimitivePatternsFourthPreviewExamplesTest {

	private final PrimitivePatternsFourthPreviewExamples examples = new PrimitivePatternsFourthPreviewExamples();

	@Test
	void childProcessDemonstratesPrimitivePatternMatching(@TempDir Path workspace) throws Exception {
		PrimitivePatternsFourthPreviewExamples.PrimitivePatternsWorkflowResult result =
				examples.runPrimitivePatternsWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the generated source should compile when Java 26 preview syntax is enabled")
				.isZero();
		assertThat(result.compilation().output())
				.as("javac should identify the child source as preview syntax usage")
				.contains("uses preview features of Java SE 26");
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
				.as("primitive instanceof patterns should bind only after safe conversion")
				.contains("small-byte=fits:42")
				.contains("large-byte=does-not-fit:1000")
				.contains("exact-int=fits:42")
				.contains("too-large-int=does-not-fit:1000000000000");
		assertThat(result.execution().output())
				.as("primitive switch patterns should classify by the first safe primitive match")
				.contains("byte-case=byte:42")
				.contains("short-case=short:1000")
				.contains("int-case=int:1000000")
				.contains("long-case=long:1000000000000");
		assertThat(result.execution().output())
				.as("guarded primitive patterns should refine an already successful primitive match")
				.contains("guard-positive=non-negative-byte:42")
				.contains("guard-negative=negative-byte:-1")
				.contains("guard-outside=outside-byte:1000");
	}

	@Test
	void probeSourceUsesRealJava26PrimitivePatternSyntax() {
		assertThat(examples.probeSource())
				.as("the child source should demonstrate Java 26 preview primitive-pattern syntax directly")
				.contains("instanceof byte")
				.contains("instanceof int")
				.contains("case byte")
				.contains("case short")
				.contains("case int")
				.contains("when");
	}

	@Test
	void exampleExplainsSafetyAndPreviewBoundary() {
		assertThat(examples.purpose())
				.as("Primitive patterns should be framed as uniform pattern matching")
				.contains("uniform")
				.contains("primitive types")
				.contains("pattern contexts");
		assertThat(examples.safetyGoal())
				.as("Primitive patterns should explain safe conversion before binding")
				.contains("conversion is safe")
				.contains("binding");
		assertThat(examples.previewBoundary())
				.as("The example should explain why preview code is isolated from the main Maven build")
				.contains("--enable-preview")
				.contains("child JVM")
				.contains("main Maven build");
		assertThat(examples.testBoundary())
				.as("The example should avoid presenting preview syntax as final")
				.contains("Java 26 preview")
				.contains("not the future final syntax");
	}
}
