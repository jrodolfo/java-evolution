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
.\scripts\use-java-26-windows.ps1
```

If PowerShell blocks local scripts, allow them for the current session only:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
.\scripts\use-java-26-windows.ps1
```

## Custom JDK Locations

The Linux helper searches `JAVA26_HOME`, `JDK26_HOME`, common JDK directories, and SDKMAN candidates. Pass a JDK path explicitly when needed:

```bash
source scripts/use-java-26-linux.sh /usr/lib/jvm/jdk-26.0.2.1
```

The Windows helpers search `JAVA26_HOME`, `JDK26_HOME`, `C:\dev\apps`, and common `Program Files` locations:

```bash
source scripts/use-java-26-windows.sh /c/dev/apps/jdk-26.0.2.1
```

```powershell
.\scripts\use-java-26-windows.ps1 -JavaHome C:\dev\apps\jdk-26.0.2.1
```

## Verify

```bash
java --version
javac --version
mvn --version
```

## Optional Tools

- GNU Make runs repository-level convenience targets.
- Node.js runs documentation audits.
- [`lychee`](https://github.com/lycheeverse/lychee) runs Markdown and HTML link checks.

Use `make release-check` for the complete local validation gate.
