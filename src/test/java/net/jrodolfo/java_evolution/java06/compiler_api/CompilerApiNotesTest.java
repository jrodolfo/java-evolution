package net.jrodolfo.java_evolution.java06.compiler_api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompilerApiNotesTest {

	private final CompilerApiNotes notes = new CompilerApiNotes();

	@Test
	void notesExplainCompilerApiPurposeAndTypes() {
		assertThat(notes.problemSolved())
				.as("JSR 199 should be framed as replacing external or implementation-specific compiler invocation")
				.contains("javac")
				.contains("implementation-specific");

		assertThat(notes.centralType())
				.as("The central compiler API type should be named")
				.contains("javax.tools.JavaCompiler")
				.contains("ToolProvider")
				.contains("diagnostics");
	}

	@Test
	void notesExplainDiagnosticsAndRepositoryDecision() {
		assertThat(notes.diagnosticsRole())
				.as("Compiler diagnostics should be part of the learning point")
				.contains("DiagnosticListener")
				.contains("errors")
				.contains("warnings");

		assertThat(notes.repositoryDecision())
				.as("The notes should avoid source-code-string demos")
				.contains("source files")
				.contains("not a source-code string");
	}
}
