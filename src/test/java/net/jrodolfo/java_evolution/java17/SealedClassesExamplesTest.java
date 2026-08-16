package net.jrodolfo.java_evolution.java17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SealedClassesExamplesTest {

	private final SealedClassesExamples examples = new SealedClassesExamples();

	@Test
	void sealedHierarchyAllowsKnownImplementations() {
		// Given
		SealedClassesExamples.Shape circle = new SealedClassesExamples.Circle(2.0);
		SealedClassesExamples.Shape rectangle = new SealedClassesExamples.Rectangle(3.0, 4.0);
		SealedClassesExamples.Shape square = new SealedClassesExamples.Square(5.0);

		// When / Then
		assertThat(examples.area(circle))
				.as("The permitted Circle implementation should be handled")
				.isEqualTo(Math.PI * 4.0);
		assertThat(examples.area(rectangle))
				.as("The permitted Rectangle implementation should be handled")
				.isEqualTo(12.0);
		assertThat(examples.area(square))
				.as("The permitted non-sealed Square implementation should be handled")
				.isEqualTo(25.0);
	}

	@Test
	void sealedParentExposesItsPermittedSubclasses() {
		// When
		var permittedShapeNames = examples.permittedShapeNames();

		// Then
		assertThat(permittedShapeNames)
				.as("The final sealed Shape interface should declare the subtypes allowed by the hierarchy")
				.containsExactlyInAnyOrder("Circle", "Rectangle", "Square");
	}
}
