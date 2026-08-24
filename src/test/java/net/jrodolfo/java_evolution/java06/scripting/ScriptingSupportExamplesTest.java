package net.jrodolfo.java_evolution.java06.scripting;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ScriptingSupportExamplesTest {

	private final ScriptingSupportExamples examples = new ScriptingSupportExamples();

	@Test
	void customEngineEvaluatesScriptWithBindings() throws Exception {
		Object result = examples.evaluateWithCustomEngine("hello ${name}", "Java 6");

		assertThat(result)
				.as("JSR 223 lets Java pass values into an engine through bindings")
				.isEqualTo("hello Java 6");
	}

	@Test
	void javascriptEngineLookupIsOptionalOnModernJdks() {
		assertThat(examples.javascriptEngineLookupResult())
				.as("the example should check for a JavaScript engine instead of assuming Nashorn or Rhino exists")
				.contains("ScriptEngineManager")
				.contains("JavaScript");
		assertThat(examples.modernPractice())
				.as("modern applications should declare their script engine explicitly")
				.contains("explicit dependency")
				.contains("ScriptEngineManager");
	}

	@Test
	void childJvmDiscoversScriptEngineFactoryThroughServiceProviderFile(@TempDir Path workspace) throws Exception {
		ScriptingSupportExamples.CommandResult result = examples.runServiceProviderProbe(workspace);

		assertThat(result.exitCode())
				.as("the generated engine factory, engine, and probe should compile and run")
				.isZero();
		assertThat(result.output())
				.as("the child JVM should discover the custom JSR 223 engine through META-INF/services")
				.contains("factories=1")
				.contains("engine=Echo Script Engine")
				.contains("result=hello Java 6")
				.contains("javascript=null");
	}

	@Test
	void sourceShowsTheRealJsr223TypesAndServiceFile() {
		assertThat(examples.engineFactorySource())
				.as("the generated factory should use the real Java 6 service-provider type")
				.contains("implements ScriptEngineFactory")
				.contains("getScriptEngine")
				.contains("Echo Script Engine");
		assertThat(examples.engineSource())
				.as("the generated engine should use the real Java 6 abstract base class and bindings")
				.contains("extends AbstractScriptEngine")
				.contains("Bindings")
				.contains("eval(String script, ScriptContext context)");
		assertThat(examples.serviceProviderFileName())
				.as("JSR 223 engine discovery uses the standard service-provider path")
				.isEqualTo("META-INF/services/javax.script.ScriptEngineFactory");
	}

	@Test
	void exampleExplainsTheApiShapeAndEngineCaveat() {
		assertThat(examples.problemSolved())
				.as("the example should start with the standard hosting problem")
				.contains("standard")
				.contains("scripting languages");
		assertThat(examples.apiPackage())
				.as("the Java 6 API package and central types should stay visible")
				.contains("javax.script")
				.contains("ScriptEngineManager")
				.contains("ScriptEngineFactory")
				.contains("bindings");
	}
}
