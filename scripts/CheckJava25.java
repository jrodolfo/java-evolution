import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

final class CheckJava25 {
    private static final Pattern JAVA_25 = Pattern.compile("^(java|openjdk)\\s+25([\\s.]|$)");
    private static final Pattern MAVEN_JAVA_25 = Pattern.compile("^25([\\s.]|$).*");

    public static void main(String[] args) throws IOException, InterruptedException {
        CommandResult java = run(List.of("java", "--version"));
        String javaVersion = firstLine(java.output());

        if (java.exitCode() != 0 || !JAVA_25.matcher(javaVersion).find()) {
            System.err.println("java 25 is required, but the active java is not java 25.");
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

        if (maven.exitCode() != 0 || !MAVEN_JAVA_25.matcher(mavenJavaVersion).find()) {
            System.err.println("java 25 is required, but maven is not using java 25.");
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
        byte[] output = process.getInputStream().readAllBytes();
        int exitCode = process.waitFor();
        return new CommandResult(exitCode, new String(output, StandardCharsets.UTF_8).trim());
    }

    private static String firstLine(String output) {
        return output.lines()
                .filter(line -> !line.isBlank())
                .findFirst()
                .map(String::trim)
                .orElse("");
    }

    private static String mavenCommand() {
        return System.getProperty("os.name", "").toLowerCase().contains("win") ? "mvn.cmd" : "mvn";
    }

    private static void printHelperInstructions() {
        System.err.println("run one of:");
        System.err.println("  source scripts/use-java-25-mac.sh");
        System.err.println("  source scripts/use-java-25-windows.sh");
        System.err.println("  . .\\scripts\\use-java-25-windows.ps1");
        System.err.println();
        System.err.println("then verify:");
        System.err.println("  java --version");
        System.err.println("  mvn --version");
    }

    private record CommandResult(int exitCode, String output) {
    }
}
