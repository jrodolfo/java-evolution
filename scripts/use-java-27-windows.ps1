param([string] $JavaHome)

function Test-Java27Home {
    param([string] $Candidate)
    if ([string]::IsNullOrWhiteSpace($Candidate)) { return $false }
    $expanded = [Environment]::ExpandEnvironmentVariables($Candidate)
    $javaExe = Join-Path $expanded "bin\java.exe"
    if (-not (Test-Path -LiteralPath $javaExe -PathType Leaf)) { return $false }
    $versionLine = & $javaExe --version 2>&1 | Select-Object -First 1
    return $versionLine -match '^(openjdk|java)\s+27(\s|\.|$)'
}

function Set-Java27Home {
    param([string] $Candidate)
    $env:JAVA_HOME = (Resolve-Path -LiteralPath $Candidate).Path
    $javaBin = Join-Path $env:JAVA_HOME "bin"
    $env:Path = (@($javaBin) + ($env:Path -split ';' | Where-Object { $_ -and $_ -ne $javaBin })) -join ';'
    Write-Host "JAVA_HOME=$env:JAVA_HOME"
    java --version
}

if ($JavaHome) {
    if (-not (Test-Java27Home $JavaHome)) { Write-Error "The supplied path is not a valid JDK 27 home: $JavaHome"; exit 1 }
    Set-Java27Home $JavaHome
    return
}

$candidates = @($env:JAVA27_HOME, $env:JDK27_HOME)
foreach ($root in @('C:\dev\apps', 'C:\Program Files\Java', 'C:\Program Files\Eclipse Adoptium', 'C:\Program Files\Microsoft', 'C:\Program Files\Zulu')) {
    if (Test-Path -LiteralPath $root -PathType Container) { $candidates += Get-ChildItem -LiteralPath $root -Directory -ErrorAction SilentlyContinue | Sort-Object Name -Descending | Select-Object -ExpandProperty FullName }
}
foreach ($candidate in $candidates) { if (Test-Java27Home $candidate) { Set-Java27Home $candidate; return } }
Write-Error "Could not find a JDK 27 installation. Pass the JDK path explicitly, or set JAVA27_HOME or JDK27_HOME first."
exit 1
