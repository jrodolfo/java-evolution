param(
    [string] $JavaHome
)

function Test-Java26Home {
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
    return $versionLine -match '^(openjdk|java)\s+26(\s|\.|$)'
}

function Set-Java26Home {
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
    if (-not (Test-Java26Home -Candidate $JavaHome)) {
        Write-Error "The supplied path is not a valid JDK 26 home: $JavaHome"
        exit 1
    }

    Set-Java26Home -Candidate $JavaHome
    return
}

if ($env:JAVA26_HOME) {
    $candidates += $env:JAVA26_HOME
}

if ($env:JDK26_HOME) {
    $candidates += $env:JDK26_HOME
}

$searchRoots = @(
    'C:\dev\apps',
    'C:\Program Files\Java',
    'C:\Program Files\Eclipse Adoptium',
    'C:\Program Files\Microsoft',
    'C:\Program Files\Zulu'
)

foreach ($root in $searchRoots) {
    if (Test-Path -LiteralPath $root -PathType Container) {
        $matches = Get-ChildItem -LiteralPath $root -Directory -ErrorAction SilentlyContinue |
                Sort-Object Name -Descending

        foreach ($match in $matches) {
            $candidates += $match.FullName
        }
    }
}

foreach ($candidate in $candidates) {
    if (Test-Java26Home -Candidate $candidate) {
        Set-Java26Home -Candidate $candidate
        return
    }
}

Write-Error @"
Could not find a JDK 26 installation.

Pass the JDK path explicitly, or set JAVA26_HOME or JDK26_HOME first.

Examples:
  .\scripts\use-java-26-windows.ps1 -JavaHome 'C:\Program Files\Zulu\zulu-26'
  `$env:JAVA26_HOME = 'C:\Program Files\Zulu\zulu-26'; .\scripts\use-java-26-windows.ps1
"@
exit 1
