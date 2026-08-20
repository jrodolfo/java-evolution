package net.jrodolfo.java_evolution.java07;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class DiamondOperatorExamplesTest {

	private final DiamondOperatorExamples examples = new DiamondOperatorExamples();

	@Test
	void diamondOperatorKeepsNestedGenericTypeFromTarget() {
		Map<String, List<String>> features = examples.featuresByRelease();

		assertThat(features)
				.as("Diamond inference should preserve the target Map<String, List<String>> type")
				.containsKey("java07");
		assertThat(features.get("java07"))
				.containsExactly("try-with-resources", "diamond operator");
	}
}
