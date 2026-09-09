#!/usr/bin/env bash

if [[ $# -gt 0 ]]; then
  candidate="$1"
  if [[ ! -x "$candidate/bin/java" ]]; then
    echo "The supplied path is not a valid JDK 27 home: $candidate"
    return 1 2>/dev/null || exit 1
  fi
  version_output="$("$candidate/bin/java" --version 2>&1 | head -n 1)"
  if [[ ! "$version_output" =~ ^(openjdk|java)[[:space:]]27([[:space:].]|$) ]]; then
    echo "The supplied path is not a valid JDK 27 home: $candidate"
    return 1 2>/dev/null || exit 1
  fi
  export JAVA_HOME="$candidate"
else
  export JAVA_HOME=$(/usr/libexec/java_home -v 27)
fi

export PATH="$JAVA_HOME/bin:$PATH"
java --version
