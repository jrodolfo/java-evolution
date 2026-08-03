param(
    [string] $JavaHome
)

function Test-Java25Home {
    param(
        [string] $Candidate
    )

    if ([string]::IsNullOrWhiteSpace($Candidate)) {
        return $false
    }

    $expanded = [Environment]::ExpandEnvironmentVariables($Candidate)
    $javaExe = Join-Path $expanded "bin\java.exe"

    if (-not (Test-Path -LiteralPath $javaExe -PathType Leaf)) {
        return $false
    }

    $versionLine = & $javaExe --version 2>&1 | Select-Object -First 1
    return $versionLine -match '^(openjdk|java)\s+25(\s|\.|$)'
}

function Set-Java25Home {
    param(
        [string] $Candidate
    )

    $resolved = (Resolve-Path -LiteralPath $Candidate).Path
    $env:JAVA_HOME = $resolved
    $javaBin = Join-Path $env:JAVA_HOME "bin"

    $pathParts = $env:Path -split ';' | Where-Object {
        -not [string]::IsNullOrWhiteSpace($_) -and
        $_ -ne $javaBin
    }

    $env:Path = (@($javaBin) + $pathParts) -join ';'

    Write-Host "JAVA_HOME=$env:JAVA_HOME"
    java --version
}

$candidates = @()

if ($JavaHome) {
    if (-not (Test-Java25Home -Candidate $JavaHome)) {
        Write-Error "The supplied path is not a valid JDK 25 home: $JavaHome"
        exit 1
    }

    Set-Java25Home -Candidate $JavaHome
    return
}

if ($env:JAVA25_HOME) {
    $candidates += $env:JAVA25_HOME
}

if ($env:JDK25_HOME) {
    $candidates += $env:JDK25_HOME
}

$searchRoots = @(
    'C:\dev\apps',
    'C:\Program Files\Java',
    'C:\Program Files\Eclipse Adoptium',
    'C:\Program Files\Microsoft'
)

foreach ($root in $searchRoots) {
    if (Test-Path -LiteralPath $root -PathType Container) {
        $matches = Get-ChildItem -LiteralPath $root -Directory -Filter 'jdk-25*' -ErrorAction SilentlyContinue |
            Sort-Object Name -Descending

        foreach ($match in $matches) {
            $candidates += $match.FullName
        }
    }
}

foreach ($candidate in $candidates) {
    if (Test-Java25Home -Candidate $candidate) {
        Set-Java25Home -Candidate $candidate
        return
    }
}

Write-Error @"
Could not find a JDK 25 installation.

Pass the JDK path explicitly, or set JAVA25_HOME or JDK25_HOME first.

Examples:
  .\scripts\Use-Java25.ps1 -JavaHome C:\dev\apps\jdk-25.0.0
  `$env:JAVA25_HOME = 'C:\dev\apps\jdk-25.0.0'; .\scripts\Use-Java25.ps1
"@
exit 1
