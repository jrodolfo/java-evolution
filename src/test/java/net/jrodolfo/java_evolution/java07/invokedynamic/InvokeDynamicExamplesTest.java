package net.jrodolfo.java_evolution.java07.invokedynamic;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class InvokeDynamicExamplesTest {

	private final InvokeDynamicExamples examples = new InvokeDynamicExamples();

	@Test
	void constantCallSiteInvokesBootstrapLinkedTarget() throws Throwable {
		String result = examples.invokeConstantCallSite("hello", "Java");
		MethodType type = examples.constantCallSiteType();

		assertThat(result)
				.as("A constant call site should invoke the method-handle target selected by bootstrap-like linkage")
				.isEqualTo("hello, Java");
		assertThat(type)
				.as("The call site should preserve the requested method type")
				.isEqualTo(MethodType.methodType(String.class, String.class, String.class));
	}

	@Test
	void mutableCallSiteCanBeRetargeted() throws Throwable {
		InvokeDynamicExamples.RetargetResult result = examples.retargetMutableCallSite("MiXeD");

		assertThat(result.before())
				.as("The mutable call site should initially call the uppercase target")
				.isEqualTo("MIXED");
		assertThat(result.after())
				.as("After retargeting, the same dynamic invoker should call the lowercase target")
				.isEqualTo("mixed");
	}

	@Test
	void incompatibleCallSiteTargetFailsTypeCheck() throws Exception {
		WrongMethodTypeException exception = examples.incompatibleTargetFailure();

		assertThat(exception)
				.as("Call-site targets are dynamically selected but still strongly typed")
				.isInstanceOf(WrongMethodTypeException.class);
	}

	@Test
	void compiledLambdaBytecodeContainsInvokedynamicInstruction(@TempDir Path workspace) throws Exception {
		Path sourceFile = examples.createLambdaSource(workspace);
		Path outputDirectory = workspace.resolve("classes");

		InvokeDynamicExamples.CommandResult compile = examples.compile(sourceFile, outputDirectory);
		InvokeDynamicExamples.CommandResult run = examples.runClass(outputDirectory, "LambdaBytecode");
		InvokeDynamicExamples.CommandResult javap = examples.inspectBytecode(outputDirectory, "LambdaBytecode");

		assertThat(compile.exitCode())
				.as("The generated lambda source should compile; output was: %s", compile.output())
				.isZero();
		assertThat(run.exitCode())
				.as("The generated lambda class should run; output was: %s", run.output())
				.isZero();
		assertThat(run.output())
				.as("The lambda source is ordinary Java code whose bytecode uses invokedynamic")
				.contains("hello, Java");
		assertThat(javap.exitCode())
				.as("javap should inspect the generated class; output was: %s", javap.output())
				.isZero();
		assertThat(javap.output())
				.as("javap output should show the actual invokedynamic instruction and bootstrap metadata")
				.contains("invokedynamic")
				.contains("BootstrapMethods")
				.contains("LambdaMetafactory");
	}
}
