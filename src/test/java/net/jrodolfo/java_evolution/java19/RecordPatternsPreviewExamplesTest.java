package net.jrodolfo.java_evolution.java19;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RecordPatternsPreviewExamplesTest {

	private final RecordPatternsPreviewExamples examples = new RecordPatternsPreviewExamples();

	@Test
	void recordPatternDeconstructsRecordComponents() {
		// When / Then
		assertThat(examples.describe(new RecordPatternsPreviewExamples.Point(3, 4)))
				.as("A record pattern should bind the record components")
				.isEqualTo("point x=3 y=4");
		assertThat(examples.describe("not a point"))
				.as("Non-matching values should use the fallback branch")
				.isEqualTo("unknown");
	}
}
