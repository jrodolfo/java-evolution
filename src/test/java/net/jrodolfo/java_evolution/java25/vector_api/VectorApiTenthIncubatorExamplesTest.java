package net.jrodolfo.java_evolution.java25.vector_api;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class VectorApiTenthIncubatorExamplesTest {

	private final VectorApiTenthIncubatorExamples examples = new VectorApiTenthIncubatorExamples();

	@Test
	void childProcessRunsVectorApiArrayAddition(@TempDir Path workspace) throws Exception {
		VectorApiTenthIncubatorExamples.VectorWorkflowResult result = examples.runVectorWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the generated source should compile when the incubator vector module is added")
				.isZero();
		assertThat(Files.exists(result.sourceFile()))
				.as("the example should write the vector source into the temporary workspace")
				.isTrue();
		assertThat(Files.exists(result.classesDirectory()))
				.as("the child compilation should produce a classes directory")
				.isTrue();

		assertThat(result.execution().exitCode())
				.as("the child JVM should run successfully with the incubator vector module")
				.isZero();
		assertThat(result.execution().output())
				.as("the vector workflow should report species metadata and scalar-tail handling")
				.contains("WARNING: Using incubator modules: jdk.incubator.vector")
				.contains("species-length=")
				.contains("species-bits=")
				.contains("loop-bound=")
				.contains("tail-elements=");
		assertThat(result.execution().output())
				.as("lane-wise vector addition plus scalar tail should produce the same result as scalar addition")
				.contains("result=[11, 22, 33, 44, 55, 66, 77, 88, 99, 110]");
	}

	@Test
	void probeSourceUsesTheRealIncubatorVectorApi() {
		assertThat(examples.probeSource())
				.as("the child source should demonstrate the Java 25 incubator Vector API directly")
				.contains("jdk.incubator.vector.IntVector")
				.contains("jdk.incubator.vector.VectorSpecies")
				.contains("IntVector.SPECIES_PREFERRED")
				.contains("SPECIES.loopBound")
				.contains("IntVector.fromArray")
				.contains(".add(")
				.contains("intoArray");
	}

	@Test
	void exampleExplainsVectorLearningBoundary() {
		assertThat(examples.problem())
				.as("The Vector API should be introduced as a response to scalar one-value-at-a-time loops")
				.contains("scalar loops")
				.contains("one value at a time");
		assertThat(examples.java25Idea())
				.as("The example should explain lane-wise vector computation and JVM compilation")
				.contains("lane-wise")
				.contains("JVM")
				.contains("CPU vector instructions");
		assertThat(examples.terminology())
				.as("The example should define SIMD for learners")
				.contains("Single Instruction, Multiple Data")
				.contains("vector lanes");
		assertThat(examples.incubatorBoundary())
				.as("The example should explain why incubator code is isolated from the main Maven build")
				.contains("--add-modules")
				.contains("jdk.incubator.vector")
				.contains("child JVM");
		assertThat(examples.testBoundary())
				.as("The example should avoid pretending to prove hardware vectorization or performance")
				.contains("numeric correctness")
				.contains("not hardware SIMD")
				.contains("speed");
	}
}
