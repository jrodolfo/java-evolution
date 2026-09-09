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

- [GNU Make](https://www.gnu.org/software/make/) runs the repository-level
  targets such as `make test`, `make docs`, `make demos`, and
  `make release-check`. Maven remains usable without Make.
- [Node.js](https://nodejs.org/en/download/package-manager) runs the local
  documentation navigation audit used by `make docs-audit`, `make docs-check`,
  and `make release-check`.
- [`lychee`](https://github.com/lycheeverse/lychee#installation) checks Markdown
  and HTML links for `make links`, `make docs-check`, and
  `make release-check`.

Install each tool using your operating system's package manager or the linked
official installation instructions. Verify the optional tools with:

```bash
make --version
node --version
lychee --version
```

Use `make release-check` for the complete local validation gate.
