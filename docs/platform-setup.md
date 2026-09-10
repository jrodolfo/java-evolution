# Platform Setup

The repository uses JDK 27 and Maven 3.9+. Maven can be run through the
repository's Maven Wrapper (`mvnw` or `mvnw.cmd`), so a separate Maven
installation is optional. The helper scripts select JDK 27 for the current
terminal session only.

They do not change the machine's global Java default.

## Select JDK 27

macOS:

```bash
source scripts/use-java-27-mac.sh
```

Linux (Zsh or Bash; source the helper in your current shell):

```bash
source scripts/use-java-27-linux.sh
```

The switch applies to this shell session and its child processes; changing directories does not undo it. The SDKMAN default stays unchanged. Open a fresh terminal to use your default JDK again.

Windows with Git Bash:

```bash
source scripts/use-java-27-windows.sh
```

Windows with PowerShell:

```powershell
. .\scripts\use-java-27-windows.ps1
```

If PowerShell blocks local scripts, allow them for the current session only:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
. .\scripts\use-java-27-windows.ps1
```

## Custom JDK Locations

On macOS, the helper normally selects a registered JDK 27 through
`/usr/libexec/java_home`. Pass a JDK home explicitly when it is installed in a
custom location:

```bash
source scripts/use-java-27-mac.sh /Users/jrodolfo/Library/Java/JavaVirtualMachines/jdk-27.jdk/Contents/Home
```

The Linux helper searches `JAVA27_HOME`, `JDK27_HOME`, common JDK directories, and SDKMAN candidates. Pass a JDK path explicitly when needed:

```bash
source scripts/use-java-27-linux.sh /usr/lib/jvm/jdk-27
```

The Windows helpers search `JAVA27_HOME`, `JDK27_HOME`, `C:\dev\apps`, and common `Program Files` locations. Both Windows helpers also accept an explicit JDK path when needed:

```bash
source scripts/use-java-27-windows.sh /c/dev/apps/jdk-27
```

```powershell
. .\scripts\use-java-27-windows.ps1 -JavaHome C:\dev\apps\jdk-27
```

## Verify

```bash
java --version
javac --version
mvn --version
```

## Optional Tools

These tools are optional if you run Maven commands directly, but they are
needed for the repository-level convenience and documentation targets:

- <a target="_blank" rel="noopener noreferrer" href="https://www.gnu.org/software/make/">GNU Make</a> runs the repository-level
  targets such as `make run-tests`, `make generate-docs`, `make run-demos`, and
  `make check-release`. Maven remains usable without Make.
- <a target="_blank" rel="noopener noreferrer" href="https://nodejs.org/en/download/package-manager">Node.js</a> runs the local
  documentation navigation audit used by `make audit-docs`, `make check-docs`,
  and `make check-release`.
- <a target="_blank" rel="noopener noreferrer" href="https://github.com/lycheeverse/lychee#installation"><code>lychee</code></a> checks Markdown
  and HTML links for `make check-links`, `make check-docs`, and
  `make check-release`.

Install each tool using your operating system's package manager or the linked
official installation instructions. Verify the optional tools with:

```bash
make --version
node --version
lychee --version
```

Use `make check-release` for the complete local validation gate.
