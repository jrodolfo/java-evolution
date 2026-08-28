#!/usr/bin/env bash

export JAVA_HOME=$(/usr/libexec/java_home -v 26)
export PATH="$JAVA_HOME/bin:$PATH"

java --version
