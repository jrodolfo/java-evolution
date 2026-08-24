package net.jrodolfo.java_evolution.java18.inet_address_resolution;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the Internet-Address Resolution SPI introduced in Java 18.
 */
public class InetAddressResolutionExamples {

	public CommandResult runResolverProbe(Path workspace) throws IOException, InterruptedException {
		Path sourceDirectory = workspace.resolve("src");
		Path providerSource = sourceDirectory.resolve("demo").resolve("FixedResolverProvider.java");
		Path probeSource = sourceDirectory.resolve("Probe.java");
		Path classesDirectory = workspace.resolve("classes");
		Path serviceFile = classesDirectory.resolve(serviceProviderFileName());

		Files.createDirectories(providerSource.getParent());
		Files.createDirectories(serviceFile.getParent());
		Files.write(providerSource, providerSource().getBytes(StandardCharsets.UTF_8));
		Files.write(probeSource, probeSource().getBytes(StandardCharsets.UTF_8));
		Files.write(serviceFile, "demo.FixedResolverProvider\n".getBytes(StandardCharsets.UTF_8));

		CommandResult compilation = run(javacCommand(), "-d", classesDirectory.toString(), providerSource.toString(),
				probeSource.toString());
		if (compilation.exitCode() != 0) {
			return compilation;
		}

		return run(javaCommand(), "-cp", classesDirectory.toString(), "Probe");
	}

	public String providerSource() {
		return """
				package demo;

				import java.net.InetAddress;
				import java.net.UnknownHostException;
				import java.net.spi.InetAddressResolver;
				import java.net.spi.InetAddressResolverProvider;
				import java.util.stream.Stream;

				public class FixedResolverProvider extends InetAddressResolverProvider {
				    @Override
				    public String name() {
				        return "fixed-demo";
				    }

				    @Override
				    public InetAddressResolver get(Configuration configuration) {
				        return new InetAddressResolver() {
				            @Override
				            public Stream<InetAddress> lookupByName(String host, LookupPolicy policy)
				                    throws UnknownHostException {
				                if (host.equals("demo.internal")) {
				                    return Stream.of(InetAddress.getByAddress(host, new byte[] { 10, 0, 0, 42 }));
				                }
				                throw new UnknownHostException("provider rejected " + host);
				            }

				            @Override
				            public String lookupByAddress(byte[] address) throws UnknownHostException {
				                if (address.length == 4 && address[0] == 10 && address[1] == 0
				                        && address[2] == 0 && address[3] == 42) {
				                    return "demo.internal";
				                }
				                throw new UnknownHostException("provider rejected reverse lookup");
				            }
				        };
				    }
				}
				""";
	}

	public String probeSource() {
		return """
				import java.net.InetAddress;
				import java.net.UnknownHostException;

				public class Probe {
				    public static void main(String[] args) throws Exception {
				        InetAddress address = InetAddress.getByName("demo.internal");
				        System.out.println("host=" + address.getHostName());
				        System.out.println("address=" + address.getHostAddress());
				        System.out.println("canonical=" + address.getCanonicalHostName());

				        try {
				            InetAddress.getByName("outside.internal");
				        } catch (UnknownHostException exception) {
				            System.out.println("unknown=" + exception.getMessage());
				        }
				    }
				}
				""";
	}

	public String serviceProviderFileName() {
		return "META-INF/services/java.net.spi.InetAddressResolverProvider";
	}

	public String extensionPoint() {
		return "InetAddressResolverProvider service-provider interface";
	}

	public String useCase() {
		return "custom DNS or address resolution for advanced networking environments";
	}

	public String processWideCaveat() {
		return "a resolver provider changes InetAddress behavior for the whole process, so tests isolate it in a child JVM";
	}

	private String javacCommand() {
		String executable = isWindows() ? "javac.exe" : "javac";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private String javaCommand() {
		String executable = isWindows() ? "java.exe" : "java";
		return new File(new File(System.getProperty("java.home"), "bin"), executable).getPath();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
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
