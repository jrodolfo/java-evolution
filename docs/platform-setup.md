# Platform Setup

This project uses JDK 26, Maven 3.9+, and a local Maven installation. It does not use the Maven wrapper.

The helper scripts select JDK 26 for the current terminal session only. They do not change the machine's global Java default.

## Select JDK 26

macOS:

```bash
source scripts/use-java-26-mac.sh
```

Linux:

```bash
source scripts/use-java-26-linux.sh
```

Windows with Git Bash:

```bash
source scripts/use-java-26-windows.sh
```

Windows with PowerShell:

```powershell
. .\scripts\use-java-26-windows.ps1
```

If PowerShell blocks local scripts, allow them for the current session only:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
. .\scripts\use-java-26-windows.ps1
```

## Custom JDK Locations

On macOS, the helper normally selects a registered JDK 26 through
`/usr/libexec/java_home`. Pass a JDK home explicitly when it is installed in a
custom location:

```bash
source scripts/use-java-26-mac.sh /opt/jdks/jdk-26.0.2.1/Contents/Home
```

The Linux helper searches `JAVA26_HOME`, `JDK26_HOME`, common JDK directories, and SDKMAN candidates. Pass a JDK path explicitly when needed:

```bash
source scripts/use-java-26-linux.sh /usr/lib/jvm/jdk-26.0.2.1
```

The Windows helpers search `JAVA26_HOME`, `JDK26_HOME`, `C:\dev\apps`, and common `Program Files` locations. Both Windows helpers also accept an explicit JDK path when needed:

```bash
source scripts/use-java-26-windows.sh /c/dev/apps/jdk-26.0.2.1
```

```powershell
. .\scripts\use-java-26-windows.ps1 -JavaHome C:\dev\apps\jdk-26.0.2.1
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
