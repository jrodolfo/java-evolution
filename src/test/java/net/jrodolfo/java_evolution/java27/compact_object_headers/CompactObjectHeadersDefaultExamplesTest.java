package net.jrodolfo.java_evolution.java27.compact_object_headers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompactObjectHeadersDefaultExamplesTest {

	@Test
	void java27EnablesCompactObjectHeadersByDefault() throws Exception {
		CompactObjectHeadersDefaultExamples examples = new CompactObjectHeadersDefaultExamples();
		var result = examples.inspectDefaultFlag();
		assertThat(result.exitCode()).isEqualTo(0);
		assertThat(result.output())
				.containsPattern("UseCompactObjectHeaders\\s+=\\s+true\\s+.*\\{default\\}");
		assertThat(examples.boundary()).contains("without claiming to measure");
	}
}
