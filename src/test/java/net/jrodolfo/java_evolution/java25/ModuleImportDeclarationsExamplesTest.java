package net.jrodolfo.java_evolution.java25;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ModuleImportDeclarationsExamplesTest {

	private final ModuleImportDeclarationsExamples examples = new ModuleImportDeclarationsExamples();

	@Test
	void javaBaseModuleImportMakesTypesFromExportedPackagesAvailable(@TempDir Path workspace) throws Exception {
		Path sourceFile = examples.createJavaBaseModuleImportSource(workspace);

		ModuleImportDeclarationsExamples.CommandResult compile = examples.compile(sourceFile, workspace.resolve("out"));
		ModuleImportDeclarationsExamples.CommandResult launch = examples.launch(sourceFile);

		assertThat(compile.exitCode())
				.as("javac should accept import module java.base; output was: %s", compile.output())
				.isZero();
		assertThat(launch.exitCode())
				.as("The source launcher should run a source file with import module java.base; output was: %s",
						launch.output())
				.isZero();
		assertThat(launch.output())
				.as("Module imports should make List, Optional, Stream, and Collectors usable without ordinary imports")
				.contains("JAVA|MODULE|IMPORTS")
				.contains("module");
		assertThat(Files.readString(sourceFile))
				.as("The source should use a module import rather than package or type imports")
				.contains("import module java.base;")
				.doesNotContain("import java.util");
	}

	@Test
	void sameSourceShapeWithoutImportsFailsToCompile(@TempDir Path workspace) throws Exception {
		Path sourceFile = examples.createSourceWithoutImports(workspace);

		ModuleImportDeclarationsExamples.CommandResult result = examples.compile(sourceFile, workspace.resolve("out"));

		assertThat(result.exitCode())
				.as("Source using List and Collectors without imports should fail")
				.isNotZero();
		assertThat(result.output())
				.as("The compiler should explain that unqualified types are unavailable without imports")
				.contains("cannot find symbol")
				.contains("List")
				.contains("Collectors");
	}

	@Test
	void multipleModuleImportsCanCoverSeveralModuleSurfaces(@TempDir Path workspace) throws Exception {
		Path sourceFile = examples.createHttpModuleImportSource(workspace);

		ModuleImportDeclarationsExamples.CommandResult compile = examples.compile(sourceFile, workspace.resolve("out"));
		ModuleImportDeclarationsExamples.CommandResult launch = examples.launch(sourceFile);

		assertThat(compile.exitCode())
				.as("javac should accept combined module imports; output was: %s", compile.output())
				.isZero();
		assertThat(launch.exitCode())
				.as("The source launcher should run combined module imports without network traffic; output was: %s",
						launch.output())
				.isZero();
		assertThat(launch.output())
				.as("The example should build HTTP API objects while URI comes from java.base")
				.contains("GET")
				.contains("example.com")
				.contains("true");
		assertThat(Files.readString(sourceFile))
				.as("The source should show that more than one module can be imported")
				.contains("import module java.base;")
				.contains("import module java.net.http;");
	}

	@Test
	void broadModuleImportsCanCreateSimpleNameAmbiguity(@TempDir Path workspace) throws Exception {
		Path sourceFile = examples.createAmbiguousDateSource(workspace);

		ModuleImportDeclarationsExamples.CommandResult result = examples.compile(sourceFile, workspace.resolve("out"));

		assertThat(result.exitCode())
				.as("Broad module imports should not hide simple-name conflicts")
				.isNotZero();
		assertThat(result.output())
				.as("The compiler should report Date as ambiguous between java.util and java.sql")
				.contains("reference to Date is ambiguous")
				.contains("java.sql.Date")
				.contains("java.util.Date");
	}
}
