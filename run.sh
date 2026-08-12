#!/usr/bin/env bash
set -e

if [ "$1" = "spring" ]; then
    mvn -pl Core javafx:run -Dbootstrap=spring
else
    mvn -pl Core javafx:run
fi
