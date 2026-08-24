package net.jrodolfo.java_evolution.java25.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StructuredConcurrencyFifthPreviewExamplesTest {

	private final StructuredConcurrencyFifthPreviewExamples examples =
			new StructuredConcurrencyFifthPreviewExamples();

	@Test
	void childProcessDemonstratesStructuredScopeSuccessAndFailure(@TempDir Path workspace) throws Exception {
		StructuredConcurrencyFifthPreviewExamples.StructuredConcurrencyWorkflowResult result =
				examples.runStructuredConcurrencyWorkflow(workspace);

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
				.as("a successful scope should join related subtasks and expose successful results")
				.contains("joined-count=2")
				.contains("user-state=SUCCESS")
				.contains("order-state=SUCCESS")
				.contains("combined=user:ana,order:42")
				.contains("success-cancelled=false");
		assertThat(result.execution().output())
				.as("a failing scope should report failure at the scope boundary")
				.contains("join-result=failed-as-unit")
				.contains("failure-cause=IllegalStateException")
				.contains("failure-message=missing-user")
				.contains("failed-state=FAILED")
				.contains("failed-exception=IllegalStateException");
	}

	@Test
	void probeSourceUsesTheRealJava25StructuredConcurrencyApi() {
		assertThat(examples.probeSource())
				.as("the child source should demonstrate the Java 25 preview StructuredTaskScope API directly")
				.contains("java.util.concurrent.StructuredTaskScope")
				.contains("StructuredTaskScope.open")
				.contains("StructuredTaskScope.Joiner.<String>allSuccessfulOrThrow")
				.contains("StructuredTaskScope.Joiner.<String>awaitAllSuccessfulOrThrow")
				.contains("scope.fork")
				.contains("scope.join")
				.contains("StructuredTaskScope.FailedException");
	}

	@Test
	void exampleExplainsScopeAndPreviewBoundary() {
		assertThat(examples.problem())
				.as("Structured concurrency should explain the scattered-subtask problem")
				.contains("started")
				.contains("joined")
				.contains("cancelled");
		assertThat(examples.commonAlternative())
				.as("The example should name common pre-structured-concurrency tools")
				.contains("ExecutorService")
				.contains("Future");
		assertThat(examples.java25Idea())
				.as("The example should frame StructuredTaskScope as one unit of related work")
				.contains("StructuredTaskScope")
				.contains("unit of work");
		assertThat(examples.previewBoundary())
				.as("The example should explain why preview code is isolated from the main Maven build")
				.contains("--enable-preview")
				.contains("child JVM")
				.contains("main Maven build");
		assertThat(examples.testBoundary())
				.as("The example should avoid race-sensitive scheduling claims")
				.contains("success and failure semantics")
				.contains("not scheduler timing")
				.contains("cancellation races");
	}
}
