package net.jrodolfo.java_evolution.java15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SealedClassesPreviewExamplesTest {

	private final SealedClassesPreviewExamples examples = new SealedClassesPreviewExamples();

	@Test
	void sealedHierarchyRestrictsKnownShapeImplementations() {
		// Given
		SealedClassesPreviewExamples.Shape circle = new SealedClassesPreviewExamples.Circle(2.0);
		SealedClassesPreviewExamples.Shape rectangle = new SealedClassesPreviewExamples.Rectangle(3.0, 4.0);

		// When / Then
		assertThat(examples.area(circle))
				.as("The permitted Circle implementation should be handled explicitly")
				.isEqualTo(Math.PI * 4.0);
		assertThat(examples.area(rectangle))
				.as("The permitted Rectangle implementation should be handled explicitly")
				.isEqualTo(12.0);
	}
}
