package net.jrodolfo.java_evolution.java02.strict_floating_point;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StrictFloatingPointExamplesTest {

	private final StrictFloatingPointExamples examples = new StrictFloatingPointExamples();

	@Test
	void exampleExplainsTheHistoricalProblemAndModernContext() {
		assertThat(examples.problemSolved())
				.as("Java 2 strictfp addressed wider intermediate precision on some processors")
				.contains("floating-point")
				.contains("wider intermediate precision");

		assertThat(examples.featureShape())
				.as("strictfp was a declaration modifier, not a library class")
				.contains("strictfp")
				.contains("classes")
				.contains("methods");

		assertThat(examples.modernContext())
				.as("modern Java keeps the historical keyword but no longer needs it for strict evaluation")
				.contains("Java 17")
				.contains("modern JDKs")
				.contains("unnecessary");
	}

	@Test
	void strictfpMethodStillRunsLikeNormalFloatingPointCode() {
		assertThat(examples.strictAverage(10.0d, 14.0d))
				.as("strictfp code remains ordinary Java code that returns deterministic double values")
				.isEqualTo(12.0d);
	}

	@Test
	void probeSourceShowsTheActualStrictfpModifier() {
		assertThat(examples.strictfpProbeSource())
				.as("the learning module should show real strictfp syntax")
				.contains("public strictfp class StrictfpProbe")
				.contains("public strictfp double average");
	}

	@Test
	void compilingStrictfpOnJdk26ShowsThatJava17RestoredAlwaysStrictSemantics(@TempDir Path workspace)
			throws Exception {
		StrictFloatingPointExamples.CompilationResult result = examples.compileStrictfpProbe(workspace);

		assertThat(result.exitCode())
				.as("strictfp remains legal source code on the JDK 26 baseline")
				.isZero();
		assertThat(result.output())
				.as("javac should explain that strictfp is historical after Java 17")
				.contains("strictfp")
				.contains("release 17")
				.contains("not required");
	}
}
