package net.jrodolfo.java_evolution.java07.invokedynamic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.WrongMethodTypeException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 7 {@code invokedynamic} linkage support.
 *
 * <p>
 * Ordinary Java source does not directly spell an {@code invokedynamic}
 * instruction. This example demonstrates the Java 7 linkage building blocks in
 * {@code java.lang.invoke}, then compiles a later Java lambda source file and
 * inspects its bytecode to show a real {@code invokedynamic} instruction.
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

	/**
	 * Creates a small Java source file whose lambda expression compiles to
	 * {@code invokedynamic} bytecode.
	 *
	 * @param directory directory where the source file should be written
	 * @return path to the source file
	 * @throws IOException when the source file cannot be written
	 */
	public Path createLambdaSource(Path directory) throws IOException {
		Files.createDirectories(directory);
		Path sourceFile = directory.resolve("LambdaBytecode.java");
		String source = ""
				+ "import java.util.function.Function;\n"
				+ "\n"
				+ "public class LambdaBytecode {\n"
				+ "    public static void main(String[] args) {\n"
				+ "        Function<String, String> greeting = name -> \"hello, \" + name;\n"
				+ "        System.out.println(greeting.apply(\"Java\"));\n"
				+ "    }\n"
				+ "}\n";
		Files.write(sourceFile, source.getBytes(StandardCharsets.UTF_8));
		return sourceFile;
	}

	/**
	 * Compiles a generated source file.
	 *
	 * @param sourceFile source file to compile
	 * @param outputDirectory destination for compiled classes
	 * @return command result
	 * @throws IOException when the compiler cannot be started
	 * @throws InterruptedException when interrupted while waiting for the compiler
	 */
	public CommandResult compile(Path sourceFile, Path outputDirectory) throws IOException, InterruptedException {
		Files.createDirectories(outputDirectory);
		List<String> command = new ArrayList<String>();
		command.add(javacExecutable());
		command.add("-d");
		command.add(outputDirectory.toString());
		command.add(sourceFile.toString());
		return run(command, sourceFile.getParent());
	}

	/**
	 * Runs a compiled class.
	 *
	 * @param classOutputDirectory directory containing compiled classes
	 * @param mainClass main class name
	 * @return command result
	 * @throws IOException when the launcher cannot be started
	 * @throws InterruptedException when interrupted while waiting for the launcher
	 */
	public CommandResult runClass(Path classOutputDirectory, String mainClass)
			throws IOException, InterruptedException {
		List<String> command = new ArrayList<String>();
		command.add(javaExecutable());
		command.add("-cp");
		command.add(classOutputDirectory.toString());
		command.add(mainClass);
		return run(command, classOutputDirectory);
	}

	/**
	 * Inspects a compiled class with {@code javap -c -v}.
	 *
	 * @param classOutputDirectory directory containing compiled classes
	 * @param className class to inspect
	 * @return command result
	 * @throws IOException when {@code javap} cannot be started
	 * @throws InterruptedException when interrupted while waiting for {@code javap}
	 */
	public CommandResult inspectBytecode(Path classOutputDirectory, String className)
			throws IOException, InterruptedException {
		List<String> command = new ArrayList<String>();
		command.add(javapExecutable());
		command.add("-classpath");
		command.add(classOutputDirectory.toString());
		command.add("-c");
		command.add("-v");
		command.add(className);
		return run(command, classOutputDirectory);
	}

	private CallSite bootstrapGreeting(MethodHandles.Lookup lookup, String name, MethodType requestedType)
			throws NoSuchMethodException, IllegalAccessException {
		MethodHandle target = lookup.findStatic(InvokeDynamicExamples.class, "join", requestedType);
		return new ConstantCallSite(target);
	}

	private CommandResult run(List<String> command, Path workingDirectory) throws IOException, InterruptedException {
		Process process = new ProcessBuilder(command)
				.directory(workingDirectory.toFile())
				.redirectErrorStream(true)
				.start();
		CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process.getInputStream()));

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			return new CommandResult(-1, output.join());
		}

		return new CommandResult(process.exitValue(), output.join());
	}

	private String readOutput(InputStream inputStream) {
		try {
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			int read;
			while ((read = inputStream.read(buffer)) != -1) {
				output.write(buffer, 0, read);
			}
			return new String(output.toByteArray(), StandardCharsets.UTF_8);
		}
		catch (IOException exception) {
			throw new IllegalStateException("could not read child process output", exception);
		}
	}

	private String javaExecutable() {
		return toolExecutable("java");
	}

	private String javacExecutable() {
		return toolExecutable("javac");
	}

	private String javapExecutable() {
		return toolExecutable("javap");
	}

	private String toolExecutable(String tool) {
		String executable = isWindows() ? tool + ".exe" : tool;
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
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

	/**
	 * Captures JDK tool output.
	 */
	public static final class CommandResult {

		private final int exitCode;
		private final String output;

		private CommandResult(int exitCode, String output) {
			this.exitCode = exitCode;
			this.output = output;
		}

		/**
		 * @return process exit code
		 */
		public int exitCode() {
			return exitCode;
		}

		/**
		 * @return merged standard output and standard error
		 */
		public String output() {
			return output;
		}
	}
}
