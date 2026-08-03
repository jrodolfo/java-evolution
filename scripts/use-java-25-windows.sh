#!/usr/bin/env bash

if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
  echo "This script must be sourced so it can update the current shell:"
  echo "  source scripts/use-java-25-windows.sh [JAVA_25_HOME]"
  exit 1
fi

set_java_home_candidate() {
  local candidate="$1"

  if [[ -z "$candidate" ]]; then
    return 1
  fi

  if command -v cygpath >/dev/null 2>&1; then
    candidate="$(cygpath -u "$candidate" 2>/dev/null || printf '%s' "$candidate")"
  fi

  if [[ -x "$candidate/bin/java" ]]; then
    local version_output
    version_output="$("$candidate/bin/java" --version 2>&1 | head -n 1)"

    if [[ "$version_output" =~ ^(openjdk|java)[[:space:]]25([[:space:].]|$) ]]; then
      export JAVA_HOME="$candidate"
      export PATH="$JAVA_HOME/bin:$PATH"
      echo "JAVA_HOME=$JAVA_HOME"
      java --version
      return 0
    fi
  fi

  return 1
}

java_home_arg="${1:-}"

if [[ -n "$java_home_arg" ]]; then
  if set_java_home_candidate "$java_home_arg"; then
    return 0
  fi

  echo "The supplied path is not a valid JDK 25 home: $java_home_arg"
  return 1
fi

if set_java_home_candidate "${JAVA25_HOME:-}"; then
  return 0
fi

if set_java_home_candidate "${JDK25_HOME:-}"; then
  return 0
fi

shopt -s nullglob
for candidate in \
  /c/dev/apps/jdk-25* \
  /c/Program\ Files/Java/jdk-25* \
  /c/Program\ Files/Eclipse\ Adoptium/jdk-25* \
  /c/Program\ Files/Microsoft/jdk-25*
do
  if set_java_home_candidate "$candidate"; then
    shopt -u nullglob
    return 0
  fi
done
shopt -u nullglob

echo "Could not find a JDK 25 installation."
echo "Pass the JDK path explicitly, or set JAVA25_HOME or JDK25_HOME first."
echo "Example:"
echo "  source scripts/use-java-25-windows.sh /c/dev/apps/jdk-25.0.0"
return 1
