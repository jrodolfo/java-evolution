#!/usr/bin/env bash

# Sourcing uses the caller's shell, regardless of the shebang.
if { [[ -n "${BASH_VERSION:-}" ]] && [[ "${BASH_SOURCE[0]}" == "$0" ]]; } ||
   { [[ -n "${ZSH_VERSION:-}" ]] && [[ "${ZSH_EVAL_CONTEXT:-}" != *:file ]]; }; then
  echo "This script must be sourced so it can update the current shell:"
  echo "  source scripts/use-java-26-linux.sh [JAVA_26_HOME]"
  exit 1
fi

use_java_26_linux() {
  local candidate java_bin remaining entry new_path

  # Discover in a subshell so glob options and helper functions stay local.
  candidate="$(
    if [[ -n "${ZSH_VERSION:-}" ]]; then
      setopt NULL_GLOB
    else
      shopt -s nullglob
    fi

    set_java_home_candidate() {
      local candidate="$1" version_output
      local version_pattern='^(openjdk|java)[[:space:]]26([[:space:].+-]|$)'

      [[ -n "$candidate" && -x "$candidate/bin/java" && -x "$candidate/bin/javac" ]] || return 1
      version_output="$("$candidate/bin/java" --version 2>&1)" || return 1
      [[ "$version_output" =~ $version_pattern ]] || return 1
      # Keep JAVA_HOME valid after changing directories.
      (cd -- "$candidate" && pwd -P)
    }

    if [[ -n "${1:-}" ]]; then
      set_java_home_candidate "$1" && exit 0
      echo "The supplied path is not a valid JDK 26 home: $1" >&2
      exit 1
    fi

    for candidate in \
      "${JAVA26_HOME:-}" \
      "${JDK26_HOME:-}" \
      /usr/lib/jvm/*26* \
      /opt/jdk-26* \
      /opt/java/jdk-26* \
      /usr/local/jdk-26* \
      "${SDKMAN_CANDIDATES_DIR:-${SDKMAN_DIR:-$HOME/.sdkman}/candidates}"/java/26*
    do
      if set_java_home_candidate "$candidate"; then
        exit 0
      fi
    done

    echo "Could not find a JDK 26 installation." >&2
    echo "Pass the JDK path explicitly, or set JAVA26_HOME or JDK26_HOME first." >&2
    exit 1
  )" || return 1

  # Move the selected bin directory to the front without duplicating it.
  java_bin="$candidate/bin"
  remaining="$PATH"
  new_path="$java_bin"
  while true; do
    entry="${remaining%%:*}"
    if [[ "$entry" != "$java_bin" ]]; then
      new_path="$new_path:$entry"
    fi
    [[ "$remaining" == *:* ]] || break
    remaining="${remaining#*:}"
  done

  export JAVA_HOME="$candidate"
  export PATH="$new_path"
  echo "JAVA_HOME=$JAVA_HOME"
  java --version
}

if use_java_26_linux "$@"; then
  unset -f use_java_26_linux
  return 0
else
  unset -f use_java_26_linux
  return 1
fi
