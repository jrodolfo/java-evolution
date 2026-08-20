package net.jrodolfo.java_evolution.java07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demonstrates the diamond operator, introduced in Java 7.
 */
public class DiamondOperatorExamples {

	/**
	 * Creates a nested generic map using diamond inference on the right side.
	 *
	 * @return map from release name to feature names
	 */
	public Map<String, List<String>> featuresByRelease() {
		Map<String, List<String>> features = new HashMap<>();
		List<String> javaSevenFeatures = new ArrayList<>();
		javaSevenFeatures.add("try-with-resources");
		javaSevenFeatures.add("diamond operator");
		features.put("java07", javaSevenFeatures);
		return features;
	}
}
