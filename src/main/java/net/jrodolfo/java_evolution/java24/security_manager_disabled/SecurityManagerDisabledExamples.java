package net.jrodolfo.java_evolution.java24.security_manager_disabled;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Java 24 permanently disabling the Security Manager.
 */
public class SecurityManagerDisabledExamples {

	public CommandResult runSecurityManagerProbe(Path workspace) throws IOException, InterruptedException {
		Path sourceFile = workspace.resolve("SecurityManagerProbe.java");
		Files.write(sourceFile, probeSource().getBytes(StandardCharsets.UTF_8));

		return run(javaCommand(), sourceFile.toString());
	}

	public String probeSource() {
		return """
				public class SecurityManagerProbe {
				    public static void main(String[] args) {
				        System.out.println("before=" + System.getSecurityManager());
				        try {
				            System.setSecurityManager(new SecurityManager());
				            System.out.println("installed=" + System.getSecurityManager());
				        } catch (Throwable throwable) {
				            System.out.println("thrown=" + throwable.getClass().getName());
				            System.out.println("message=" + throwable.getMessage());
				        }
				    }
				}
				""";
	}

	public String oldModel() {
		return "the Security Manager tried to sandbox less-trusted code inside the same JVM with permission checks";
	}

	public String permissionExamples() {
		return "file access, network access, reflection, and exiting the JVM could be checked by permissions";
	}

	public String java24Impact() {
		return "Java 24 permanently disabled setting a Security Manager as an application sandbox";
	}

	public String modernIsolationAdvice() {
		return "use operating system, container, process, and deployment-level isolation instead";
	}

	private String javaCommand() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

	private CommandResult run(String... command) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();

		boolean finished = process.waitFor(10, TimeUnit.SECONDS);
		if (!finished) {
			process.destroyForcibly();
			process.waitFor(5, TimeUnit.SECONDS);
			String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			return new CommandResult(-1, output);
		}

		String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		return new CommandResult(process.exitValue(), output);
	}

	public static final class CommandResult {

		private final int exitCode;
		private final String output;

		private CommandResult(int exitCode, String output) {
			this.exitCode = exitCode;
			this.output = output;
		}

		public int exitCode() {
			return exitCode;
		}

		public String output() {
			return output;
		}
	}
}
