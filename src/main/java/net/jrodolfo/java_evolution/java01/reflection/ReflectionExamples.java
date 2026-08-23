package net.jrodolfo.java_evolution.java01.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Demonstrates reflection, introduced in Java 1.1.
 */
public class ReflectionExamples {

	/**
	 * Reads the runtime type from an object reference.
	 *
	 * @param value object to inspect
	 * @return runtime class name
	 */
	public String runtimeTypeName(Object value) {
		Class<?> type = value.getClass();
		return type.getName();
	}

	/**
	 * Inspects a declared field without reading or changing its value.
	 *
	 * @return field name and type
	 * @throws NoSuchFieldException when the field name is wrong
	 */
	public String declaredFieldDescription() throws NoSuchFieldException {
		Field field = PluginCommand.class.getDeclaredField("name");
		return field.getName() + ": " + field.getType().getSimpleName();
	}

	/**
	 * Creates an object through a constructor selected at runtime.
	 *
	 * @param className fully qualified class name to load
	 * @param commandName constructor argument
	 * @return object created by reflection
	 * @throws ClassNotFoundException when the class name cannot be loaded
	 * @throws NoSuchMethodException when the constructor signature is wrong
	 * @throws InvocationTargetException when the constructor throws an exception
	 * @throws InstantiationException when the class cannot be instantiated
	 * @throws IllegalAccessException when access checks reject the call
	 */
	public Object createCommandByClassName(String className, String commandName)
			throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
			IllegalAccessException {
		Class<?> type = Class.forName(className);
		Constructor<?> constructor = type.getConstructor(String.class);
		return constructor.newInstance(commandName);
	}

	/**
	 * Invokes a method selected by name at runtime.
	 *
	 * @param target object receiving the method call
	 * @param methodName method to invoke
	 * @param argument method argument
	 * @return method result
	 * @throws NoSuchMethodException when the method name or signature is wrong
	 * @throws InvocationTargetException when the invoked method throws an exception
	 * @throws IllegalAccessException when access checks reject the call
	 */
	public String invokeMethodByName(Object target, String methodName, String argument)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method method = target.getClass().getMethod(methodName, String.class);
		return (String) method.invoke(target, argument);
	}

	/**
	 * Simple target class used by the reflection examples.
	 */
	public static class PluginCommand {

		private final String name;

		public PluginCommand(String name) {
			this.name = name;
		}

		public String execute(String environment) {
			return name + " executed in " + environment;
		}
	}
}
