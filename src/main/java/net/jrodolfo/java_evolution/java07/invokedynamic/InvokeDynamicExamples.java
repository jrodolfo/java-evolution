package net.jrodolfo.java_evolution.java07.invokedynamic;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.WrongMethodTypeException;

/**
 * Demonstrates Java 7 {@code invokedynamic} linkage support.
 *
 * <p>
 * Ordinary Java source does not directly spell an {@code invokedynamic}
 * instruction. This example demonstrates the Java 7 linkage building blocks in
 * {@code java.lang.invoke} building blocks. Later Java features such as lambdas
 * can use this JVM linkage infrastructure, but they are not part of this Java
 * 7 example.
 * </p>
 */
public class InvokeDynamicExamples {

	/**
	 * Creates a bootstrap-like constant call site and invokes it.
	 *
	 * @param prefix greeting prefix
	 * @param name name to greet
	 * @return greeting produced through the call site's dynamic invoker
	 * @throws Throwable when method-handle lookup or invocation fails
	 */
	public String invokeConstantCallSite(String prefix, String name) throws Throwable {
		MethodType type = MethodType.methodType(String.class, String.class, String.class);
		CallSite callSite = bootstrapGreeting(MethodHandles.lookup(), "dynamicGreeting", type);
		MethodHandle invoker = callSite.dynamicInvoker();
		return (String) invoker.invokeExact(prefix, name);
	}

	/**
	 * Returns the method type associated with the bootstrap-linked call site.
	 *
	 * @return method type exposed by the call site
	 * @throws NoSuchMethodException when the target method cannot be found
	 * @throws IllegalAccessException when the target method is not accessible
	 */
	public MethodType constantCallSiteType() throws NoSuchMethodException, IllegalAccessException {
		MethodType type = MethodType.methodType(String.class, String.class, String.class);
		return bootstrapGreeting(MethodHandles.lookup(), "dynamicGreeting", type).type();
	}

	/**
	 * Demonstrates a mutable call site whose target changes at runtime.
	 *
	 * @param value value to transform
	 * @return results before and after retargeting
	 * @throws Throwable when method-handle lookup or invocation fails
	 */
	public RetargetResult retargetMutableCallSite(String value) throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType type = MethodType.methodType(String.class, String.class);
		MethodHandle uppercase = lookup.findStatic(InvokeDynamicExamples.class, "uppercase", type);
		MethodHandle lowercase = lookup.findStatic(InvokeDynamicExamples.class, "lowercase", type);

		MutableCallSite callSite = new MutableCallSite(uppercase);
		MethodHandle invoker = callSite.dynamicInvoker();
		String before = (String) invoker.invokeExact(value);

		callSite.setTarget(lowercase);
		MutableCallSite.syncAll(new MutableCallSite[] { callSite });
		String after = (String) invoker.invokeExact(value);

		return new RetargetResult(before, after);
	}

	/**
	 * Attempts to retarget a mutable call site to an incompatible method type.
	 *
	 * @return the exception raised by the call-site type check
	 * @throws NoSuchMethodException when the target method cannot be found
	 * @throws IllegalAccessException when the target method is not accessible
	 */
	public WrongMethodTypeException incompatibleTargetFailure()
			throws NoSuchMethodException, IllegalAccessException {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType stringToString = MethodType.methodType(String.class, String.class);
		MethodHandle uppercase = lookup.findStatic(InvokeDynamicExamples.class, "uppercase", stringToString);
		MutableCallSite callSite = new MutableCallSite(uppercase);

		MethodHandle length = lookup.findStatic(
				InvokeDynamicExamples.class,
				"length",
				MethodType.methodType(int.class, String.class));
		try {
			callSite.setTarget(length);
			throw new IllegalStateException("expected WrongMethodTypeException");
		}
		catch (WrongMethodTypeException exception) {
			return exception;
		}
	}

	private CallSite bootstrapGreeting(MethodHandles.Lookup lookup, String name, MethodType requestedType)
			throws NoSuchMethodException, IllegalAccessException {
		MethodHandle target = lookup.findStatic(InvokeDynamicExamples.class, "join", requestedType);
		return new ConstantCallSite(target);
	}

	/**
	 * Target used by the constant call-site example.
	 *
	 * @param prefix greeting prefix
	 * @param name name to greet
	 * @return joined greeting
	 */
	public static String join(String prefix, String name) {
		return prefix + ", " + name;
	}

	/**
	 * Target used before mutable call-site retargeting.
	 *
	 * @param value value to transform
	 * @return uppercase value
	 */
	public static String uppercase(String value) {
		return value.toUpperCase();
	}

	/**
	 * Target used after mutable call-site retargeting.
	 *
	 * @param value value to transform
	 * @return lowercase value
	 */
	public static String lowercase(String value) {
		return value.toLowerCase();
	}

	/**
	 * Incompatible target used to demonstrate call-site type checks.
	 *
	 * @param value value to measure
	 * @return value length
	 */
	public static int length(String value) {
		return value.length();
	}

	/**
	 * Captures mutable call-site behavior before and after retargeting.
	 */
	public static final class RetargetResult {

		private final String before;
		private final String after;

		private RetargetResult(String before, String after) {
			this.before = before;
			this.after = after;
		}

		/**
		 * @return result before retargeting
		 */
		public String before() {
			return before;
		}

		/**
		 * @return result after retargeting
		 */
		public String after() {
			return after;
		}
	}

}
