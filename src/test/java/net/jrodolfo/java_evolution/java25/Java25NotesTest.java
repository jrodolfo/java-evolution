package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Java25NotesTest {

	@Test
	void finalLanguageNotesShowReducedCeremonyFeatures() {
		assertThat(new ModuleImportDeclarationsNotes().example())
				.as("Module imports should show the source declaration shape")
				.contains("import module");
		assertThat(new CompactSourceFilesNotes().purpose())
				.as("Compact source files should be framed as removing class/main ceremony")
				.contains("without an explicit class");
	}

	@Test
	void finalCryptographyNotesExplainKdfWithoutProviderSpecificSetup() {
		assertThat(new KeyDerivationFunctionNotes().purpose())
				.as("The KDF note should explain key derivation at a high level")
				.contains("cryptographic keys");
	}

	@Test
	void previewAndIncubatorNotesKeepNewestFeaturesClearlyLabeled() {
		assertThat(new PrimitivePatternsThirdPreviewNotes().status())
				.as("Primitive patterns should not be presented as final in Java 25")
				.contains("third preview");
		assertThat(new StableValuesPreviewNotes().purpose())
				.as("Stable values should explain the initialized-once model")
				.contains("initialized at most once");
		assertThat(new PemEncodingsPreviewNotes().purpose())
				.as("PEM support should be documented as cryptographic text encoding support")
				.contains("PEM");
		assertThat(new StructuredConcurrencyFifthPreviewNotes().status())
				.as("Structured concurrency should stay marked as preview")
				.contains("fifth preview");
		assertThat(new VectorApiTenthIncubatorNotes().status())
				.as("Vector API should stay marked as incubating")
				.contains("tenth incubator");
	}

	@Test
	void runtimeAndDiagnosticsNotesExplainOperationalFeatures() {
		assertThat(new AotCommandLineErgonomicsNotes().purpose())
				.as("AOT ergonomics should be framed as operational startup work")
				.contains("ahead-of-time");
		assertThat(new JfrEnhancementsNotes().features())
				.as("JFR notes should identify profiling and tracing capabilities")
				.contains("CPU-time")
				.contains("method timing");
		assertThat(new CompactObjectHeadersNotes().purpose())
				.as("Compact object headers should be explained as a memory-footprint feature")
				.contains("memory footprint");
		assertThat(new GenerationalShenandoahNotes().purpose())
				.as("Generational Shenandoah should be documented as GC behavior")
				.contains("Shenandoah");
	}
}
