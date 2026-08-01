package net.jrodolfo.java_evolution.java09;

import java.lang.StackWalker.StackFrame;

/**
 * Demonstrates {@link StackWalker}, introduced in Java 9.
 *
 * <p>
 * StackWalker provides a lazy and structured way to inspect stack frames. It is
 * often preferable to eagerly creating an array with
 * {@code Thread.currentThread().getStackTrace()}.
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
