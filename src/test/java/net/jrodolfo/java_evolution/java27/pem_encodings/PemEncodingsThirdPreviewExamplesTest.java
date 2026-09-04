package net.jrodolfo.java_evolution.java27.pem_encodings;

import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PemEncodingsThirdPreviewExamplesTest {

	@Test
	void childProcessDemonstratesInMemoryPemRoundTrip(@TempDir Path workspace) throws Exception {
		PemEncodingsThirdPreviewExamples examples = new PemEncodingsThirdPreviewExamples();
		var result = examples.run(workspace);
		assertThat(result.exitCode()).isEqualTo(0);
		assertThat(result.output()).contains("label=LEARNING OBJECT", "payload=true", "boundaries=true");
		assertThat(examples.boundary()).contains("Java 27").contains("in-memory bytes");
	}
}
