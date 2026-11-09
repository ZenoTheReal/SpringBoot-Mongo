#!/usr/bin/env bash
# Automatically load environment variables from .env if present
if [ -f .env ]; then
  set -a
  source .env
  set +a
fi

# Start Gradle watcher in background for continuous recompilation
./gradlew -t classes &
WATCH_PID=$!

# Cleanly stop the background watcher when you press Ctrl+C
trap "kill $WATCH_PID 2>/dev/null; exit" INT TERM EXIT

# Start Spring Boot in foreground
./gradlew bootRun
