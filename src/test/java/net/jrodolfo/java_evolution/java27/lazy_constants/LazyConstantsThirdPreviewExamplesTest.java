package net.jrodolfo.java_evolution.java27.lazy_constants;

import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LazyConstantsThirdPreviewExamplesTest {

	@Test
	void childProcessDemonstratesLazyConstantReuse(@TempDir Path workspace) throws Exception {
		LazyConstantsThirdPreviewExamples examples = new LazyConstantsThirdPreviewExamples();
		var result = examples.run(workspace);
		assertThat(result.exitCode()).isEqualTo(0);
		assertThat(result.output()).contains("value=java-27", "same=true", "evaluations=1");
		assertThat(examples.status()).contains("third preview").contains("Java 27");
	}

	@Test
	void sourceUsesLazyConstantPreviewApi() {
		assertThat(new LazyConstantsThirdPreviewExamples().probeSource()).contains("LazyConstant.of", "value.get()");
	}
}
