package net.jrodolfo.java_evolution.java27.structured_concurrency;

import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StructuredConcurrencySeventhPreviewExamplesTest {

	@Test
	void childProcessDemonstratesStructuredJoin(@TempDir Path workspace) throws Exception {
		StructuredConcurrencySeventhPreviewExamples examples = new StructuredConcurrencySeventhPreviewExamples();
		var result = examples.run(workspace);
		assertThat(result.exitCode()).isEqualTo(0);
		assertThat(result.output()).contains("joined=left+right");
		assertThat(examples.boundary()).contains("Java 27").contains("child JVM");
	}
}
