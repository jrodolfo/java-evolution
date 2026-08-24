package net.jrodolfo.java_evolution.java20.vector_api;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class VectorApiFifthIncubatorExamplesTest {

	private final VectorApiFifthIncubatorExamples examples = new VectorApiFifthIncubatorExamples();

	@Test
	void childProcessRunsJava20VectorApiArrayAddition(@TempDir Path workspace) throws Exception {
		VectorApiFifthIncubatorExamples.VectorWorkflowResult result = examples.runVectorWorkflow(workspace);

		assertThat(result.compilation().exitCode())
				.as("the generated source should compile when the Java 20 incubator vector module is added")
				.isZero();
		assertThat(Files.exists(result.sourceFile()))
				.as("the example should write the vector source into the temporary workspace")
				.isTrue();
		assertThat(Files.exists(result.classesDirectory()))
				.as("the child compilation should produce a classes directory")
				.isTrue();
		assertThat(result.inspection().exitCode())
				.as("javap should inspect the Java 20-targeted class file")
				.isZero();
		assertThat(result.inspection().output())
				.as("compiling with --release 20 should produce Java 20 class-file version 64")
				.contains("major version: 64");

		assertThat(result.execution().exitCode())
				.as("the child JVM should run successfully with the incubator vector module")
				.isZero();
		assertThat(result.execution().output())
				.as("the vector workflow should report fixed species metadata and scalar-tail handling")
				.contains("WARNING: Using incubator modules: jdk.incubator.vector")
				.contains("species-length=4")
				.contains("species-bits=128")
				.contains("loop-bound=4")
				.contains("tail-elements=1");
		assertThat(result.execution().output())
				.as("lane-wise vector addition plus scalar tail should produce the same result as scalar addition")
				.contains("result=[11, 22, 33, 44, 55]");
	}

	@Test
	void probeSourceUsesTheRealIncubatorVectorApi() {
		assertThat(examples.probeSource())
				.as("the child source should demonstrate the Java 20 incubator Vector API directly")
				.contains("jdk.incubator.vector.IntVector")
				.contains("jdk.incubator.vector.VectorSpecies")
				.contains("IntVector.SPECIES_128")
				.contains("SPECIES.loopBound")
				.contains("IntVector.fromArray")
				.contains(".add(")
				.contains("intoArray");
	}

	@Test
	void exampleExplainsVectorApiIncubatorStatus() {
		assertThat(examples.problemSolved())
				.as("The example should explain the scalar-loop problem")
				.contains("many values")
				.contains("scalar value");
		assertThat(examples.oldApproachProblem())
				.as("The example should name the older indirect/native approaches")
				.contains("JIT auto-vectorization")
				.contains("native libraries")
				.contains("CPU vector instructions");
		assertThat(examples.incubatorIdea())
				.as("The example should preserve the direct SIMD-style Java API idea")
				.contains("Vector API")
				.contains("SIMD")
				.contains("lane-wise");
		assertThat(examples.realUseCases())
				.as("The example should ground the feature in realistic performance-sensitive domains")
				.contains("image processing")
				.contains("audio processing")
				.contains("machine-learning");
		assertThat(examples.incubatorStatus())
				.as("The example should identify Java 20 as the fifth incubator round")
				.contains("fifth incubator")
				.contains("Java 20");
		assertThat(examples.incubatorBoundary())
				.as("The example should explain the child-process incubator-module boundary")
				.contains("--release 20")
				.contains("--add-modules")
				.contains("jdk.incubator.vector");
		assertThat(examples.testBoundary())
				.as("The example should avoid pretending to prove hardware vectorization or performance")
				.contains("Java 20 bytecode")
				.contains("not hardware SIMD")
				.contains("speed");
		assertThat(examples.nextStep())
				.as("The Java 20 module should point to the later Java 25 Vector API guide")
				.contains("Java 25")
				.contains("vector_api")
				.contains("tenth-incubator");
	}
}
