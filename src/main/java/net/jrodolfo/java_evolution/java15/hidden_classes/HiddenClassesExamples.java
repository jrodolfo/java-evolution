package net.jrodolfo.java_evolution.java15.hidden_classes;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Demonstrates hidden classes, introduced in Java 15 by JEP 371.
 */
public class HiddenClassesExamples {

	/**
	 * Defines a hidden class from the compiled bytes of the nested template class.
	 *
	 * @return hidden class metadata
	 * @throws IOException when the template class bytes cannot be read
	 * @throws IllegalAccessException when the lookup cannot define the hidden class
	 */
	public Class<?> defineHiddenTemplateClass() throws IOException, IllegalAccessException {
		byte[] classBytes = templateClassBytes();
		MethodHandles.Lookup hiddenLookup = MethodHandles.lookup().defineHiddenClass(classBytes, true);
		return hiddenLookup.lookupClass();
	}

	/**
	 * Reports whether a class is hidden.
	 *
	 * @param type class to inspect
	 * @return whether the class is hidden
	 */
	public boolean isHidden(Class<?> type) {
		return type.isHidden();
	}

	/**
	 * Invokes behavior on an instance of the hidden class.
	 *
	 * @param hiddenClass hidden class to instantiate
	 * @return message returned by the hidden instance
	 * @throws NoSuchMethodException when the expected constructor or method is missing
	 * @throws InvocationTargetException when the constructor or method throws an exception
	 * @throws InstantiationException when the hidden class cannot be instantiated
	 * @throws IllegalAccessException when access checks reject construction or invocation
	 */
	public String invokeHiddenClassMethod(Class<?> hiddenClass)
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		Constructor<?> constructor = hiddenClass.getDeclaredConstructor();
		Object instance = constructor.newInstance();
		return (String) hiddenClass.getMethod("message").invoke(instance);
	}

	/**
	 * Checks whether normal class-name lookup can discover a hidden class.
	 *
	 * @param hiddenClass hidden class to look up by name
	 * @return whether {@link Class#forName(String)} finds the hidden class
	 */
	public boolean canFindByName(Class<?> hiddenClass) {
		try {
			Class.forName(hiddenClass.getName());
			return true;
		}
		catch (ClassNotFoundException exception) {
			return false;
		}
	}

	private byte[] templateClassBytes() throws IOException {
		String resourceName = HiddenClassesExamples.class.getPackageName().replace('.', '/')
				+ "/HiddenClassesExamples$Template.class";
		try (InputStream input = HiddenClassesExamples.class.getClassLoader().getResourceAsStream(resourceName)) {
			if (input == null) {
				throw new IOException("Missing template class bytes: " + resourceName);
			}
			return input.readAllBytes();
		}
	}

	/**
	 * Compiled template used as class-file bytes for the hidden class definition.
	 */
	public static class Template {
		public String message() {
			return "hidden implementation";
		}
	}
}
