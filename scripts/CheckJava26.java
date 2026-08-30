import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

final class CheckJava26 {
    private static final Pattern JAVA_26 = Pattern.compile("^(java|openjdk)\\s+26([\\s.]|$)");
    private static final Pattern MAVEN_JAVA_26 = Pattern.compile("^26([\\s.]|$).*");

    public static void main(String[] args) throws IOException, InterruptedException {
        CommandResult java = run(List.of("java", "--version"));
        String javaVersion = firstLine(java.output());

        if (java.exitCode() != 0 || !JAVA_26.matcher(javaVersion).find()) {
            System.err.println("java 26 is required, but the active java is not java 26.");
            System.err.println();
            System.err.println("active java:");
            System.err.println("  " + (javaVersion.isBlank() ? "java command was not available" : javaVersion));
            System.err.println();
            printHelperInstructions();
            System.exit(1);
        }

        CommandResult maven = run(List.of(mavenCommand(), "--version"));
        String mavenJavaVersion = maven.output()
                .lines()
                .filter(line -> line.startsWith("Java version:"))
                .map(line -> line.substring("Java version:".length()).trim())
                .findFirst()
                .orElse("");

        if (maven.exitCode() != 0 || !MAVEN_JAVA_26.matcher(mavenJavaVersion).find()) {
            System.err.println("java 26 is required, but maven is not using java 26.");
            System.err.println();
            System.err.println("maven java:");
            System.err.println("  " + (mavenJavaVersion.isBlank()
                    ? "mvn command was not available"
                    : "Java version: " + mavenJavaVersion));
            System.err.println();
            printHelperInstructions();
            System.exit(1);
        }
    }

    private static CommandResult run(List<String> command) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start();
        CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));
        boolean finished = process.waitFor(10, TimeUnit.SECONDS);
        if (!finished) {
            process.destroyForcibly();
            process.waitFor(5, TimeUnit.SECONDS);
            return new CommandResult(-1, output.join().trim());
        }
        return new CommandResult(process.exitValue(), output.join().trim());
    }

    private static String readOutput(Process process) {
        try {
            return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IllegalStateException("could not read prerequisite command output", exception);
        }
    }

    private static String firstLine(String output) {
        return output.lines()
                .filter(line -> !line.isBlank())
                .findFirst()
                .map(String::trim)
                .orElse("");
    }

	private static String mavenCommand() {
		return System.getProperty("os.name", "").toLowerCase(Locale.ROOT).contains("win") ? "mvn.cmd" : "mvn";
	}

    private static void printHelperInstructions() {
        System.err.println("run one of:");
        System.err.println("  source scripts/use-java-26-mac.sh");
        System.err.println("  source scripts/use-java-26-linux.sh");
        System.err.println("  source scripts/use-java-26-windows.sh");
        System.err.println("  . .\\scripts\\use-java-26-windows.ps1");
        System.err.println();
        System.err.println("then verify:");
        System.err.println("  java --version");
        System.err.println("  mvn --version");
    }

    private record CommandResult(int exitCode, String output) {
    }
}
