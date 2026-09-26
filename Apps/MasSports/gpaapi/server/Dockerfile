# Imagen del legacy (Play Framework 2.5 / Scala 2.11 / JDK 8).
# Se construye desde server/ (no desde server/springboot).

# ---- build ----
FROM eclipse-temurin:8-jdk AS build
ARG SBT_VERSION=1.10.7
RUN apt-get update && apt-get install -y --no-install-recommends curl ca-certificates \
    && rm -rf /var/lib/apt/lists/* \
    && curl -fsSL "https://github.com/sbt/sbt/releases/download/v${SBT_VERSION}/sbt-${SBT_VERSION}.tgz" -o /tmp/sbt.tgz \
    && tar xzf /tmp/sbt.tgz -C /opt \
    && ln -s /opt/sbt/bin/sbt /usr/local/bin/sbt
WORKDIR /app
COPY . .
RUN sbt -batch -Dsbt.log.noformat=true stage

# ---- runtime ----
FROM eclipse-temurin:8-jre
WORKDIR /app
RUN useradd -r -u 1001 appuser
COPY --from=build /app/target/universal/stage ./
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh && chown -R appuser:appuser /app
USER appuser
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]
