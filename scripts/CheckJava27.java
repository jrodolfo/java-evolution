import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

final class CheckJava27 {
    private static final Pattern JAVA_27 = Pattern.compile("^(java|openjdk)\\s+27([\\s.]|$)");
    private static final Pattern MAVEN_JAVA_27 = Pattern.compile("^27([\\s.,]|$).*");

    public static void main(String[] args) throws IOException, InterruptedException {
        CommandResult java = run(List.of("java", "--version"));
        String javaVersion = firstLine(java.output());
        if (java.exitCode() != 0 || !JAVA_27.matcher(javaVersion).find()) {
            fail("java 27 is required, but the active java is not java 27.", javaVersion);
        }

        CommandResult maven = run(List.of(mavenCommand(), "--version"));
        String mavenJavaVersion = maven.output().lines()
                .filter(line -> line.startsWith("Java version:"))
                .map(line -> line.substring("Java version:".length()).trim())
                .findFirst().orElse("");
        if (maven.exitCode() != 0 || !MAVEN_JAVA_27.matcher(mavenJavaVersion).find()) {
            fail("java 27 is required, but maven is not using java 27.", mavenJavaVersion);
        }
    }

    private static void fail(String message, String version) {
        System.err.println(message);
        System.err.println("active version:");
        System.err.println("  " + (version.isBlank() ? "command was not available" : version));
        System.err.println("run one of:");
        System.err.println("  source scripts/use-java-27-mac.sh");
        System.err.println("  source scripts/use-java-27-linux.sh");
        System.err.println("  source scripts/use-java-27-windows.sh");
        System.err.println("  . .\\scripts\\use-java-27-windows.ps1");
        System.exit(1);
    }

    private static CommandResult run(List<String> command) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
        CompletableFuture<String> output = CompletableFuture.supplyAsync(() -> readOutput(process));
        if (!process.waitFor(10, TimeUnit.SECONDS)) {
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
        return output.lines().filter(line -> !line.isBlank()).findFirst().map(String::trim).orElse("");
    }

    private static String mavenCommand() {
        return System.getProperty("os.name", "").toLowerCase(Locale.ROOT).contains("win") ? "mvn.cmd" : "mvn";
    }

    private record CommandResult(int exitCode, String output) {
    }
}
