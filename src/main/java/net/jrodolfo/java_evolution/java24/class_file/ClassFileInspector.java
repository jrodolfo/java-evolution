package net.jrodolfo.java_evolution.java24.class_file;

import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;

/**
 * Demonstrates parsing a compiled Java class with the Java 24 Class-File API.
 *
 * <p>
 * The example accepts class-file bytes, parses them with
 * {@link ClassFile#parse(byte[])}, and returns a small summary of the class.
 * Tools can use the same API family to parse, generate, and transform class
 * files without depending on third-party bytecode libraries for every use case.
 * </p>
 */
public class ClassFileInspector {

	/**
	 * Parses compiled class-file bytes.
	 *
	 * @param classFileBytes the bytes from a {@code .class} file
	 * @return a learner-friendly summary of the class file
	 */
	public ClassFileSummary inspect(byte[] classFileBytes) {
		var model = ClassFile.of().parse(classFileBytes);
		var className = dottedClassName(model);
		var methodNames = model.methods().stream()
				.map(method -> method.methodName().stringValue())
				.sorted()
				.toList();
		var fieldNames = model.fields().stream()
				.map(field -> field.fieldName().stringValue())
				.sorted()
				.toList();
		var accessFlags = model.flags().flags();

		return new ClassFileSummary(
				className,
				model.majorVersion(),
				model.minorVersion(),
				methodNames,
				fieldNames,
				accessFlags);
	}

	private String dottedClassName(ClassModel model) {
		return model.thisClass().asInternalName().replace('/', '.');
	}
}
