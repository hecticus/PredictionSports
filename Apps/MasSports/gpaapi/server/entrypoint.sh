#!/bin/sh
# Entrypoint del legacy para Cloud Run.
# Traduce variables de entorno a -D de Play/Typesafe Config.
set -e

PORT="${PORT:-8080}"
DB_PORT="${DB_PORT:-3306}"
DB_NAME="${DB_NAME:-extapi}"
DB_URL="${DB_URL:-jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true}"

exec bin/extapi \
  -Dhttp.port="${PORT}" \
  -Dpidfile.path=/tmp/RUNNING_PID \
  -Ddb.default.url="${DB_URL}" \
  -Ddb.default.username="${DB_USER}" \
  -Ddb.default.password="${DB_PASSWORD}" \
  -Dplay.crypto.secret="${APPLICATION_SECRET}" \
  -Dapplication.secret="${APPLICATION_SECRET}"
