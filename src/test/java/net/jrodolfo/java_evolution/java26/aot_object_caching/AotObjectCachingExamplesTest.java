package net.jrodolfo.java_evolution.java26.aot_object_caching;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AotObjectCachingExamplesTest {

	private final AotObjectCachingExamples examples = new AotObjectCachingExamples();

	@Test
	void childJvmsCreateAndReuseAnAotObjectCache(@TempDir Path workspace) throws Exception {
		AotObjectCachingExamples.AotObjectCachingWorkflowResult result =
				examples.runAotObjectCachingWorkflow(workspace);

		assertThat(Files.exists(result.sourceFile()))
				.as("The example should create the temporary source-launcher program")
				.isTrue();
		assertThat(result.cacheCreation().exitCode())
				.as("The Java 26 child JVM should create the AOT object cache")
				.isZero();
		assertThat(result.cacheSize())
				.as("AOT cache creation should leave a non-empty cache file")
				.isPositive();
		assertThat(result.cacheCreation().output())
				.as("The creation run should report the AOT cache workflow")
				.contains("AOTCache creation is complete")
				.contains("phase=creation")
				.contains("message=aot-object-cache");
		assertThat(result.cacheUsage().exitCode())
				.as("The second Java 26 child JVM should run with the generated cache")
				.isZero();
		assertThat(result.cacheUsage().output())
				.as("The usage run should show that the AOT cache was opened and used")
				.contains("Opened AOT cache")
				.contains("Using AOT-linked classes: true")
				.contains("phase=usage")
				.contains("message=aot-object-cache");
	}

	@Test
	void probeSourceExplainsThatTheWorkflowIsAotObjectCaching() {
		assertThat(examples.probeSource())
				.as("The child source should expose stable output for cache workflow assertions")
				.contains("AotObjectProbe")
				.contains("aot-object-cache")
				.contains("aot.phase");
	}

	@Test
	void documentationExplainsTheRuntimeBoundary() {
		assertThat(examples.problem())
				.as("The example should explain repeated startup initialization work")
				.contains("startup")
				.contains("initialization");
		assertThat(examples.java26Idea())
				.as("The Java 26 explanation should mention initialized objects and any GC")
				.contains("initialized objects")
				.contains("any GC");
		assertThat(examples.testBoundary())
				.as("The test boundary should exclude unreliable startup benchmarks")
				.contains("cache creation")
				.contains("not startup performance");
	}
}
