#!/usr/bin/env bash

if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
  echo "This script must be sourced so it can update the current shell:"
  echo "  source scripts/use-java-27-windows.sh [JAVA_27_HOME]"
  exit 1
fi

set_java_home_candidate() {
  local candidate="$1"
  [[ -n "$candidate" ]] || return 1
  if command -v cygpath >/dev/null 2>&1; then candidate="$(cygpath -u "$candidate" 2>/dev/null || printf '%s' "$candidate")"; fi
  [[ -x "$candidate/bin/java" ]] || return 1
  local version_output
  version_output="$("$candidate/bin/java" --version 2>&1 | head -n 1)"
  [[ "$version_output" =~ ^(openjdk|java)[[:space:]]27([[:space:].]|$) ]] || return 1
  export JAVA_HOME="$candidate"
  export PATH="$JAVA_HOME/bin:$PATH"
  echo "JAVA_HOME=$JAVA_HOME"
  java --version
}

if [[ -n "${1:-}" ]]; then
  set_java_home_candidate "$1" || { echo "The supplied path is not a valid JDK 27 home: $1"; return 1; }
  return 0
fi

for candidate in "${JAVA27_HOME:-}" "${JDK27_HOME:-}" "/c/dev/apps"/* "/c/Program Files/Java"/* "/c/Program Files/Eclipse Adoptium"/* "/c/Program Files/Microsoft"/* "/c/Program Files/Zulu"/*; do
  if set_java_home_candidate "$candidate"; then return 0; fi
done

echo "Could not find a JDK 27 installation. Pass the JDK path explicitly, or set JAVA27_HOME or JDK27_HOME first."
return 1
