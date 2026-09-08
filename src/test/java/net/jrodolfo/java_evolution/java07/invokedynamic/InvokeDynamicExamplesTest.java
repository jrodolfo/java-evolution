package net.jrodolfo.java_evolution.java07.invokedynamic;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;

import org.junit.jupiter.api.Test;

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

}
