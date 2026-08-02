package net.jrodolfo.java_evolution.java23;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Java23NotesTest {

	@Test
	void markdownDocumentationCommentsMakeSourceDocumentationLighter() {
		assertThat(new MarkdownDocumentationCommentsNotes().purpose())
				.as("Markdown comments solve the problem of HTML-heavy source documentation")
				.contains("Markdown");
	}

	@Test
	void primitivePatternsWerePreviewAndStillEvolvingAfterJava23() {
		assertThat(new PrimitivePatternsPreviewNotes().status())
				.as("The note should prevent learners from treating Java 23 primitive patterns as final")
				.contains("preview in Java 23")
				.contains("still preview in Java 25");
	}

	@Test
	void moduleImportDeclarationsWerePreviewBeforeBecomingFinal() {
		assertThat(new ModuleImportDeclarationsPreviewNotes().status())
				.as("The Java 23 note should connect the preview to its Java 25 final release")
				.contains("final in Java 25");
	}

	@Test
	void flexibleConstructorBodiesContinuedTowardFinalConstructorValidation() {
		assertThat(new FlexibleConstructorBodiesSecondPreviewNotes().purpose())
				.as("The note should explain why constructor validation before delegation matters")
				.contains("constructor");
	}

	@Test
	void streamGatherersAndClassFileApiWerePreviewApisThatBecameFinalInJava24() {
		assertThat(new StreamGatherersSecondPreviewNotes().status())
				.as("Gatherers should be documented as a Java 23 preview, not a final Java 23 API")
				.contains("final in Java 24");
		assertThat(new ClassFileApiSecondPreviewNotes().purpose())
				.as("The Class-File API note should identify bytecode/class-file tooling as the problem space")
				.contains("class files");
	}

	@Test
	void scopedValuesAndStructuredConcurrencyDocumentContextAndTaskScope() {
		assertThat(new ScopedValuesThirdPreviewNotes().purpose())
				.as("Scoped values should be explained as bounded context, not global mutable state")
				.contains("bounded scope");
		assertThat(new StructuredConcurrencyThirdPreviewNotes().status())
				.as("Structured concurrency should remain marked as preview in this release range")
				.contains("fifth preview");
	}

	@Test
	void unsafeMemoryAndZgcNotesExplainRuntimeMigrationTopics() {
		assertThat(new UnsafeMemoryAccessDeprecationNotes().replacementDirection())
				.as("Unsafe memory access should point learners toward supported replacement APIs")
				.contains("Foreign Function and Memory API");
		assertThat(new ZgcGenerationalModeNotes().purpose())
				.as("The ZGC note should document the runtime default change")
				.contains("generational mode");
	}
}
