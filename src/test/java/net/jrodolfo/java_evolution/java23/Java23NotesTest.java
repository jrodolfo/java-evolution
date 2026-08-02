package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Java23NotesTest {

	@Test
	void notesDescribeJava23Features() {
		assertThat(new MarkdownDocumentationCommentsNotes().purpose()).contains("Markdown");
		assertThat(new PrimitivePatternsPreviewNotes().status()).contains("preview in Java 23");
		assertThat(new ModuleImportDeclarationsPreviewNotes().status()).contains("final in Java 25");
		assertThat(new FlexibleConstructorBodiesSecondPreviewNotes().purpose()).contains("constructor");
		assertThat(new StreamGatherersSecondPreviewNotes().status()).contains("final in Java 24");
		assertThat(new ClassFileApiSecondPreviewNotes().purpose()).contains("class files");
		assertThat(new ScopedValuesThirdPreviewNotes().purpose()).contains("bounded scope");
		assertThat(new StructuredConcurrencyThirdPreviewNotes().status()).contains("fifth preview");
		assertThat(new UnsafeMemoryAccessDeprecationNotes().replacementDirection()).contains("Foreign Function and Memory API");
		assertThat(new ZgcGenerationalModeNotes().purpose()).contains("generational mode");
	}
}
