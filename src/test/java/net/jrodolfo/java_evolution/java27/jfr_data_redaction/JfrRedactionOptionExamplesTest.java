package net.jrodolfo.java_evolution.java27.jfr_data_redaction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class JfrRedactionOptionExamplesTest {

	@Test
	void jfrOptionHelpExposesArgumentRedactionConfiguration() throws Exception {
		JfrRedactionOptionExamples examples = new JfrRedactionOptionExamples();
		var result = examples.inspectRedactionOptions();
		assertThat(result.exitCode()).isEqualTo(0);
		assertThat(result.output())
				.contains("redact-argument")
				.contains("[REDACTED]");
		assertThat(examples.purpose()).contains("option help").contains("redacting");
		assertThat(examples.boundary()).contains("without recording events");
	}
}
