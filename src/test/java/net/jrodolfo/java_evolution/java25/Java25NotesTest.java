package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Java25NotesTest {

	@Test
	void notesDescribeJava25Features() {
		assertThat(new ModuleImportDeclarationsNotes().example()).contains("import module");
		assertThat(new CompactSourceFilesNotes().purpose()).contains("without an explicit class");
		assertThat(new KeyDerivationFunctionNotes().purpose()).contains("cryptographic keys");
		assertThat(new PrimitivePatternsThirdPreviewNotes().status()).contains("third preview");
		assertThat(new StableValuesPreviewNotes().purpose()).contains("initialized at most once");
		assertThat(new PemEncodingsPreviewNotes().purpose()).contains("PEM");
		assertThat(new StructuredConcurrencyFifthPreviewNotes().status()).contains("fifth preview");
		assertThat(new VectorApiTenthIncubatorNotes().status()).contains("tenth incubator");
		assertThat(new AotCommandLineErgonomicsNotes().purpose()).contains("ahead-of-time");
		assertThat(new JfrEnhancementsNotes().features()).contains("CPU-time").contains("method timing");
		assertThat(new CompactObjectHeadersNotes().purpose()).contains("memory footprint");
		assertThat(new GenerationalShenandoahNotes().purpose()).contains("Shenandoah");
	}
}
