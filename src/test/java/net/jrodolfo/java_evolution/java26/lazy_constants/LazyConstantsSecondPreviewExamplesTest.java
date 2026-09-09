package net.jrodolfo.java_evolution.java26.lazy_constants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LazyConstantsSecondPreviewExamplesTest {

	private final LazyConstantsSecondPreviewExamples examples = new LazyConstantsSecondPreviewExamples();

	@Test
	void childProcessDemonstratesDeferredOneTimeInitialization(@TempDir Path workspace) throws Exception {
		assumeTrue(Runtime.version().feature() == 26,
				"Java 26 preview APIs require a matching JDK 26 preview compiler");
		LazyConstantsSecondPreviewExamples.LazyConstantsWorkflowResult result =
				examples.runLazyConstantsWorkflow(workspace);

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
				.as("Lazy Constants should defer, cache, and reuse the supplied value")
				.contains("before-initialized=false")
				.contains("fallback=fallback")
				.contains("after-fallback-initialized=false")
				.contains("first=initialized-once")
				.contains("same-value=true")
				.contains("after-get-initialized=true")
				.contains("evaluations=1");
	}

	@Test
	void probeSourceUsesRealLazyConstantsPreviewApi() {
		assertThat(examples.probeSource())
				.as("the child source should use the Java 26 LazyConstant API directly")
				.contains("import java.lang.LazyConstant;")
				.contains("LazyConstant.of")
				.contains("isInitialized")
				.contains("orElse")
				.contains("get()");
	}

	@Test
	void exampleExplainsProblemAndPreviewBoundary() {
		assertThat(examples.problem())
				.as("Lazy Constants should be introduced as deferred initialization")
				.contains("expensive")
				.contains("first needed");
		assertThat(examples.comparisonWithFinalFields())
				.as("the example should distinguish final field timing from lazy constant timing")
				.contains("final fields")
				.contains("defer initialization")
				.contains("constant-like");
		assertThat(examples.status())
				.as("Lazy Constants should be marked as second preview after Stable Values")
				.contains("second preview")
				.contains("Stable Values");
		assertThat(examples.previewBoundary())
				.as("the preview API should be isolated from the main Maven build")
				.contains("child JVM")
				.contains("--enable-preview");
	}
}
