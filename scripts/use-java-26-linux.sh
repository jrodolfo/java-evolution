#!/usr/bin/env bash

if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
  echo "This script must be sourced so it can update the current shell:"
  echo "  source scripts/use-java-26-linux.sh [JAVA_26_HOME]"
  exit 1
fi

set_java_home_candidate() {
  local candidate="$1"

  if [[ -z "$candidate" ]]; then
    return 1
  fi

  if [[ -x "$candidate/bin/java" ]]; then
    local version_output
    version_output="$("$candidate/bin/java" --version 2>&1 | head -n 1)"

    if [[ "$version_output" =~ ^(openjdk|java)[[:space:]]26([[:space:].]|$) ]]; then
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

  echo "The supplied path is not a valid JDK 26 home: $java_home_arg"
  return 1
fi

if set_java_home_candidate "${JAVA26_HOME:-}"; then
  return 0
fi

if set_java_home_candidate "${JDK26_HOME:-}"; then
  return 0
fi

shopt -s nullglob
for candidate in \
  /usr/lib/jvm/*26* \
  /opt/jdk-26* \
  /opt/java/jdk-26* \
  /usr/local/jdk-26* \
  "$HOME"/.sdkman/candidates/java/*26*
do
  if set_java_home_candidate "$candidate"; then
    shopt -u nullglob
    return 0
  fi
done
shopt -u nullglob

echo "Could not find a JDK 26 installation."
echo "Pass the JDK path explicitly, or set JAVA26_HOME or JDK26_HOME first."
echo "Checked common Linux locations:"
echo "  /usr/lib/jvm/*26*"
echo "  /opt/jdk-26*"
echo "  /opt/java/jdk-26*"
echo "  /usr/local/jdk-26*"
echo "  $HOME/.sdkman/candidates/java/*26*"
echo "Example:"
echo "  source scripts/use-java-26-linux.sh /usr/lib/jvm/jdk-26.0.2.1"
return 1
