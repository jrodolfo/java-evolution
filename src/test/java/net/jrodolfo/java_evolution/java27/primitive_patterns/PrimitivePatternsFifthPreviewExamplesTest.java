package net.jrodolfo.java_evolution.java27.primitive_patterns;

import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PrimitivePatternsFifthPreviewExamplesTest {

	@Test
	void childProcessDemonstratesPrimitivePatterns(@TempDir Path workspace) throws Exception {
		PrimitivePatternsFifthPreviewExamples examples = new PrimitivePatternsFifthPreviewExamples();
		var result = examples.run(workspace);
		assertThat(result.exitCode()).isEqualTo(0);
		assertThat(result.output()).contains("fits=byte:42", "outside=outside:1000", "switch=byte:42");
		assertThat(examples.boundary()).contains("Java 27").contains("--enable-preview");
	}
}
