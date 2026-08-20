package net.jrodolfo.java_evolution.java05;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AnnotationExamplesTest {

	private final AnnotationExamples examples = new AnnotationExamples();

	@Test
	void runtimeAnnotationKeepsMetadataOnDeclaration() throws NoSuchMethodException {
		String label = examples.labelFor("process");

		assertThat(label)
				.as("A runtime annotation should let reflection read metadata from the method declaration")
				.isEqualTo("metadata lives with the declaration");
	}
}
