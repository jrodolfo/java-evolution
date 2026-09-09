package net.jrodolfo.java_evolution.java26.applet_api_removal;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AppletApiRemovalExamplesTest {

	private final AppletApiRemovalExamples examples = new AppletApiRemovalExamples();

	@Test
	void childCompilerShowsAppletApiIsRemoved(@TempDir Path workspace) throws Exception {
		AppletApiRemovalExamples.CompilationResult result = examples.compileLegacyApplet(workspace);

		assertThat(Files.exists(result.sourceFile()))
				.as("The example should write obsolete applet source into a temporary workspace")
				.isTrue();
		assertThat(result.exitCode())
				.as("JDK 26 should reject source that imports the removed java.applet package")
				.isNotZero();
		assertThat(result.output())
				.as("The compiler output should make the Applet API removal visible")
				.contains("package java.applet does not exist")
				.containsAnyOf("cannot find symbol", "Applet")
				.contains("Applet");
	}

	@Test
	void legacySourceUsesTheRemovedAppletApiDirectly() {
		assertThat(examples.legacyAppletSource())
				.as("The generated source should show the old API boundary directly")
				.contains("import java.applet.Applet")
				.contains("extends Applet");
	}

	@Test
	void examplesExplainAppletRemovalAsHistoricalMigration() {
		assertThat(examples.historicalContext())
				.as("Applet examples should place applets in the browser plugin era")
				.contains("browser")
				.contains("plugin");
		assertThat(examples.java26Change())
				.as("The examples should identify Java 26 as the API removal release")
				.contains("Java 26")
				.contains("removes");
		assertThat(examples.migrationLesson())
				.as("Removed APIs should teach deprecation-for-removal as a real migration signal")
				.contains("deprecation-for-removal")
				.contains("migration");
	}
}
