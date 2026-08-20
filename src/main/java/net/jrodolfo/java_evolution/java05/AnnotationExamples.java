package net.jrodolfo.java_evolution.java05;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Demonstrates annotations, introduced in Java 5.
 */
public class AnnotationExamples {

	/**
	 * Reads runtime annotation metadata from a method.
	 *
	 * @param methodName method to inspect
	 * @return the example label stored in the annotation
	 * @throws NoSuchMethodException if the method is missing
	 */
	public String labelFor(String methodName) throws NoSuchMethodException {
		Method method = AnnotatedService.class.getDeclaredMethod(methodName);
		ExampleFeature feature = method.getAnnotation(ExampleFeature.class);
		return feature.label();
	}

	static class AnnotatedService {

		@ExampleFeature(label = "metadata lives with the declaration")
		void process() {
		}
	}

	/**
	 * Runtime-retained marker used by the example.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface ExampleFeature {
		/**
		 * @return a descriptive label for the annotated method
		 */
		String label();
	}
}
