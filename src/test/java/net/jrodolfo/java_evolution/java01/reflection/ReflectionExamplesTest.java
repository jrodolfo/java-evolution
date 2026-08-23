package net.jrodolfo.java_evolution.java01.reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ReflectionExamplesTest {

	private final ReflectionExamples examples = new ReflectionExamples();

	@Test
	void classMetadataCanBeInspectedAtRuntime() {
		ReflectionExamples.PluginCommand command = new ReflectionExamples.PluginCommand("deploy");

		assertThat(examples.runtimeTypeName(command))
				.as("Reflection starts from runtime class metadata rather than only compile-time variable types")
				.endsWith("ReflectionExamples$PluginCommand");
	}

	@Test
	void declaredFieldsCanBeDiscoveredByName() throws NoSuchFieldException {
		assertThat(examples.declaredFieldDescription())
				.as("Reflection can inspect field metadata even when normal code does not use the field directly")
				.isEqualTo("name: String");
	}

	@Test
	void constructorCanCreateObjectFromRuntimeClassName() throws Exception {
		Object command = examples.createCommandByClassName(
				"net.jrodolfo.java_evolution.java01.reflection.ReflectionExamples$PluginCommand", "deploy");

		assertThat(command)
				.as("Reflection can create an object when the concrete class is selected at runtime")
				.isInstanceOf(ReflectionExamples.PluginCommand.class);
	}

	@Test
	void methodCanBeInvokedByName() throws Exception {
		Object command = examples.createCommandByClassName(
				"net.jrodolfo.java_evolution.java01.reflection.ReflectionExamples$PluginCommand", "deploy");

		assertThat(examples.invokeMethodByName(command, "execute", "production"))
				.as("Reflection can invoke behavior selected by method name at runtime")
				.isEqualTo("deploy executed in production");
	}

	@Test
	void missingMethodFailsAtRuntime() {
		ReflectionExamples.PluginCommand command = new ReflectionExamples.PluginCommand("deploy");

		assertThatThrownBy(() -> examples.invokeMethodByName(command, "publish", "production"))
				.as("Reflection trades compile-time method checking for runtime discovery and runtime failures")
				.isInstanceOf(NoSuchMethodException.class);
	}
}
