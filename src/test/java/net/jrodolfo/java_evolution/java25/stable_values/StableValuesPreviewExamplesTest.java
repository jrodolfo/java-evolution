package net.jrodolfo.java_evolution.java25.stable_values;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StableValuesPreviewExamplesTest {

	private final StableValuesPreviewExamples examples = new StableValuesPreviewExamples();

	@Test
	void childProcessDemonstratesStableValueSemantics(@TempDir Path workspace) throws Exception {
		assumeTrue(Runtime.version().feature() == 25,
				"Java 25 preview APIs must be compiled with a JDK 25 preview compiler");

		StableValuesPreviewExamples.StableValuesWorkflowResult result = examples.runStableValuesWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the generated source should compile when Java 25 preview APIs are enabled")
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
				.as("orElseSet should compute the holder content once and reject later mutation")
				.contains("first=computed-1")
				.contains("second=computed-1")
				.contains("holder-computations=1")
				.contains("is-set=true")
				.contains("try-set-after-set=false")
				.contains("or-else-throw=computed-1");
		assertThat(result.execution().output())
				.as("StableValue factories should memoize supplier, list, and map elements")
				.contains("supplier-values=supplier-1,supplier-1")
				.contains("supplier-computations=1")
				.contains("list-values=item-1-1,item-1-1,item-2-2")
				.contains("list-computations=2")
				.contains("map-alpha=alpha-1,alpha-1")
				.contains("map-computations=1");
	}

	@Test
	void probeSourceUsesTheRealJava25StableValueApi() {
		assertThat(examples.probeSource())
				.as("the child source should demonstrate the Java 25 preview StableValue API directly")
				.contains("java.lang.StableValue")
				.contains("StableValue.of()")
				.contains("orElseSet")
				.contains("StableValue.supplier")
				.contains("StableValue.list")
				.contains("StableValue.map");
	}

	@Test
	void exampleExplainsPreviewAndOptimizationBoundary() {
		assertThat(examples.problem())
				.as("Stable Values should be introduced as lazy initialization that becomes immutable")
				.contains("initialized lazily")
				.contains("immutable");
		assertThat(examples.java25Idea())
				.as("The example should explain deferred immutability and at-most-once initialization")
				.contains("deferred immutability")
				.contains("at most once");
		assertThat(examples.previewBoundary())
				.as("The example should explain why preview code is isolated from the main Maven build")
				.contains("--enable-preview")
				.contains("child JVM")
				.contains("main Maven build");
		assertThat(examples.testBoundary())
				.as("The example should avoid pretending to prove JVM optimizations")
				.contains("API semantics")
				.contains("not JVM optimization");
	}
}
