package net.jrodolfo.java_evolution.java24.class_file;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.lang.classfile.ClassFile;
import java.lang.reflect.AccessFlag;

import org.junit.jupiter.api.Test;

import net.jrodolfo.java_evolution.java24.StreamGatherersExamples;

class ClassFileInspectorTest {

	private final ClassFileInspector inspector = new ClassFileInspector();

	@Test
	void inspectsCompiledProjectClass() throws IOException {
		Class<?> inspectedType = StreamGatherersExamples.class;
		byte[] compiledClassBytes = classBytes(inspectedType);
		ClassFileSummary summary = inspector.inspect(compiledClassBytes);

		assertThat(summary.className())
				.as("the Class-File API exposes the class declared by the bytes")
				.isEqualTo("net.jrodolfo.java_evolution.java24.StreamGatherersExamples");
		assertThat(summary.majorVersion())
				.as("the project compiles with JDK 25")
				.isEqualTo(ClassFile.JAVA_25_VERSION);
		assertThat(summary.isJava24OrNewer())
				.as("the final API can inspect modern class-file versions")
				.isTrue();
		assertThat(summary.hasMethod("<init>"))
				.as("constructors appear in class files as <init>")
				.isTrue();
		assertThat(summary.hasMethod("fixedWindows"))
				.as("declared Java methods are visible in the class-file model")
				.isTrue();
		assertThat(summary.hasMethod("runningSum"))
				.as("multiple declared methods can be inspected")
				.isTrue();
		assertThat(summary.fieldNames())
				.as("the summary distinguishes classes with no declared fields")
				.isEmpty();
		assertThat(summary.hasAccessFlag(AccessFlag.PUBLIC))
				.as("class-level access flags are part of the class-file structure")
				.isTrue();
	}

	private byte[] classBytes(Class<?> type) throws IOException {
		var resourceName = type.getSimpleName() + ".class";
		try (var inputStream = type.getResourceAsStream(resourceName)) {
			if (inputStream == null) {
				throw new IOException("class resource not found: " + resourceName);
			}
			return inputStream.readAllBytes();
		}
	}
}
