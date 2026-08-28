#!/usr/bin/env bash

if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
  echo "This script must be sourced so it can update the current shell:"
  echo "  source scripts/use-java-26-windows.sh [JAVA_26_HOME]"
  exit 1
fi

set_java_home_candidate() {
  local candidate="$1"

  if [[ -z "$candidate" ]]; then
    return 1
  fi

  # Convert a Windows path such as:
  # C:\Program Files\Zulu\zulu-26
  # to a Git Bash path such as:
  # /c/Program Files/Zulu/zulu-26
  if command -v cygpath >/dev/null 2>&1; then
    candidate="$(cygpath -u "$candidate" 2>/dev/null || printf '%s' "$candidate")"
  fi

  if [[ ! -x "$candidate/bin/java" ]]; then
    return 1
  fi

  local version_output
  version_output="$("$candidate/bin/java" --version 2>&1 | head -n 1)"

  if [[ ! "$version_output" =~ ^(openjdk|java)[[:space:]]26([[:space:].]|$) ]]; then
    return 1
  fi

  export JAVA_HOME="$candidate"

  # Avoid adding the same Java 26 bin directory repeatedly.
  local java_bin="$JAVA_HOME/bin"
  local new_path=""
  local path_entry

  IFS=':' read -ra path_entries <<< "$PATH"

  for path_entry in "${path_entries[@]}"; do
    if [[ -n "$path_entry" && "$path_entry" != "$java_bin" ]]; then
      if [[ -z "$new_path" ]]; then
        new_path="$path_entry"
      else
        new_path="$new_path:$path_entry"
      fi
    fi
  done

  export PATH="$java_bin:$new_path"

  echo "JAVA_HOME=$JAVA_HOME"
  java --version

  return 0
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

search_roots=(
  "/c/dev/apps"
  "/c/Program Files/Java"
  "/c/Program Files/Eclipse Adoptium"
  "/c/Program Files/Microsoft"
  "/c/Program Files/Zulu"
)

for root in "${search_roots[@]}"; do
  [[ -d "$root" ]] || continue

  for candidate in "$root"/*; do
    [[ -d "$candidate" ]] || continue

    if set_java_home_candidate "$candidate"; then
      return 0
    fi
  done
done

echo "Could not find a JDK 26 installation."
echo "Pass the JDK path explicitly, or set JAVA26_HOME or JDK26_HOME first."
echo
echo "Examples:"
echo "  source scripts/use-java-26-windows.sh '/c/Program Files/Zulu/zulu-26'"
echo "  source scripts/use-java-26-windows.sh 'C:\Program Files\Zulu\zulu-26'"
return 1
