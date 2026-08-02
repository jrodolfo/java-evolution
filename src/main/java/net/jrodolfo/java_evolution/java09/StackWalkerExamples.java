package net.jrodolfo.java_evolution.java09;

import java.lang.StackWalker.StackFrame;

/**
 * Demonstrates {@link StackWalker}, introduced in Java 9.
 *
 * <p>
 * Before Java 9, stack inspection commonly used
 * {@code Thread.currentThread().getStackTrace()}, which eagerly created an
 * array and gave limited control over how frames were consumed.
 * </p>
 *
 * <p>
 * StackWalker solves this by providing a lazy and structured way to inspect
 * stack frames. It is useful for diagnostics, logging, and framework code that
 * needs caller information without eagerly materializing the whole stack.
 * </p>
 */
public class StackWalkerExamples {

	/**
	 * Finds the method that called this method.
	 *
	 * @return the caller method name
	 */
	public String callerMethodName() {
		return StackWalker.getInstance()
				.walk(frames -> frames
						.skip(1)
						.findFirst()
						.map(StackFrame::getMethodName)
						.orElse(""));
	}

	/**
	 * Finds the class that called this method.
	 *
	 * @return the caller class name
	 */
	public String callerClassName() {
		return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
				.walk(frames -> frames
						.skip(1)
						.findFirst()
						.map(StackFrame::getDeclaringClass)
						.map(Class::getName)
						.orElse(""));
	}
}
