package net.jrodolfo.java_evolution.java24.class_file;

import java.lang.reflect.AccessFlag;
import java.util.List;
import java.util.Set;

/**
 * Summarizes information parsed from a Java {@code .class} file.
 *
 * <p>
 * The Class-File API exposes class-file structure directly. This record keeps a
 * small learner-friendly subset: class name, class-file version, methods,
 * fields, and access flags.
 * </p>
 *
 * @param className the dotted Java class name
 * @param majorVersion the class-file major version
 * @param minorVersion the class-file minor version
 * @param methodNames method names declared in the class file
 * @param fieldNames field names declared in the class file
 * @param accessFlags class-level access flags
 */
public record ClassFileSummary(
		String className,
		int majorVersion,
		int minorVersion,
		List<String> methodNames,
		List<String> fieldNames,
		Set<AccessFlag> accessFlags) {

	/**
	 * Creates an immutable summary.
	 */
	public ClassFileSummary {
		methodNames = List.copyOf(methodNames);
		fieldNames = List.copyOf(fieldNames);
		accessFlags = Set.copyOf(accessFlags);
	}

	/**
	 * Checks whether the class file declares a method.
	 *
	 * @param methodName method name to find
	 * @return {@code true} when the method exists in the class file
	 */
	public boolean hasMethod(String methodName) {
		return methodNames.contains(methodName);
	}

	/**
	 * Checks whether the class file declares a field.
	 *
	 * @param fieldName field name to find
	 * @return {@code true} when the field exists in the class file
	 */
	public boolean hasField(String fieldName) {
		return fieldNames.contains(fieldName);
	}

	/**
	 * Checks whether the class file was compiled for Java 24 or newer.
	 *
	 * @return {@code true} when the class-file major version is at least Java 24
	 */
	public boolean isJava24OrNewer() {
		return majorVersion >= 68;
	}

	/**
	 * Checks whether the class has a specific class-level access flag.
	 *
	 * @param accessFlag flag to find
	 * @return {@code true} when the flag is present
	 */
	public boolean hasAccessFlag(AccessFlag accessFlag) {
		return accessFlags.contains(accessFlag);
	}
}
