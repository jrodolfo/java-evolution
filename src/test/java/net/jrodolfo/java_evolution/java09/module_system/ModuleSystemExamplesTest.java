package net.jrodolfo.java_evolution.java09.module_system;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ModuleSystemExamplesTest {

	private final ModuleSystemExamples examples = new ModuleSystemExamples();

	@Test
	void modulesCompileAndRunThroughTheModulePath(@TempDir Path workspace) throws Exception {
		Path sourceRoot = workspace.resolve("src");
		Path moduleOutputDirectory = workspace.resolve("mods");
		examples.createModularSourceTree(sourceRoot);

		ModuleSystemExamples.CommandResult compile = examples.compileModules(sourceRoot, moduleOutputDirectory);
		ModuleSystemExamples.CommandResult run = examples.runApplicationModule(moduleOutputDirectory);

		assertThat(compile.exitCode())
				.as("javac should compile real module-info.java descriptors with --module-source-path; output was: %s",
						compile.output())
				.isZero();
		assertThat(Files.exists(moduleOutputDirectory.resolve("com.example.greetings/module-info.class")))
				.as("The greetings module descriptor should compile into module output")
				.isTrue();
		assertThat(Files.exists(moduleOutputDirectory.resolve("com.example.app/module-info.class")))
				.as("The app module descriptor should compile into module output")
				.isTrue();
		assertThat(run.exitCode())
				.as("java should run the app module from --module-path; output was: %s", run.output())
				.isZero();
		assertThat(run.output())
				.as("The app module should call an exported service from the greetings module")
				.contains("hello from module");
	}

	@Test
	void moduleCompilationRejectsAccessToUnexportedInternalPackage(@TempDir Path workspace) throws Exception {
		Path sourceRoot = workspace.resolve("src");
		Path moduleOutputDirectory = workspace.resolve("mods");
		examples.createSourceTreeWithInternalPackageAccess(sourceRoot);

		ModuleSystemExamples.CommandResult compile = examples.compileModules(sourceRoot, moduleOutputDirectory);

		assertThat(compile.exitCode())
				.as("javac should reject code that imports an unexported package")
				.isNotZero();
		assertThat(compile.output())
				.as("The compiler diagnostic should point at the internal package boundary")
				.contains("com.example.greetings.internal")
				.contains("does not export");
	}
}
