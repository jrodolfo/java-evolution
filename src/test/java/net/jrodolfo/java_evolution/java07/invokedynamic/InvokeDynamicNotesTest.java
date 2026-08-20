package net.jrodolfo.java_evolution.java07.invokedynamic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InvokeDynamicNotesTest {

	private final InvokeDynamicNotes notes = new InvokeDynamicNotes();

	@Test
	void notesExplainDynamicLanguageProblemAndCorePieces() {
		assertThat(notes.problemSolved())
				.as("invokedynamic should be framed as JVM support for dynamic dispatch")
				.contains("dynamic languages")
				.contains("JVM invocation");

		assertThat(notes.corePieces())
				.as("The bytecode and linkage vocabulary should stay visible")
				.contains("invokedynamic")
				.contains("bootstrap methods")
				.contains("dynamic call sites")
				.contains("method handles")
				.contains("java.lang.invoke");
	}

	@Test
	void notesExplainLinkageLifecycleAndRepositoryDecision() {
		assertThat(notes.linkageLifecycle())
				.as("The notes should explain first-execution linkage")
				.contains("unlinked dynamic call site")
				.contains("first execution")
				.contains("method-handle target");

		assertThat(notes.repositoryDecision())
				.as("The repository should avoid pretending Java source directly spells invokedynamic")
				.contains("ordinary Java source")
				.contains("does not directly spell")
				.contains("source-code strings");
	}
}
