# GPAAPI (Spring Boot 3 / JDK 17) para Cloud Run.
# Bases Temurin sobre Ubuntu noble fijadas por digest (multi-arquitectura). Para actualizarlas:
#   docker buildx imagetools inspect eclipse-temurin:17-jre-noble
#   docker buildx imagetools inspect maven:3.9-eclipse-temurin-17-noble

# ---- build ----
FROM maven:3.9-eclipse-temurin-17-noble@sha256:f1ab639371711ac150c8e344bff7d39d8161c17a531b5dd653d3fa03af554807 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -B dependency:go-offline || true
COPY src ./src
# Los tests no necesitan BD: corren aquí y cortan el build si fallan.
RUN mvn -q -B package

# ---- runtime ----
FROM eclipse-temurin:17-jre-noble@sha256:f7537fa73fa7c5bc4e51ea11a611c1d0d8bbf4e9f22de93b666072de98e752e2
# Temurin trae LC_ALL=en_US.UTF-8, que cambia user.country a US.
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8
WORKDIR /app
RUN useradd -r -u 1001 appuser
COPY --from=build --chown=appuser:appuser /app/target/gpaapi.jar app.jar
USER appuser
# Cloud Run inyecta PORT; application.yml lo lee (server.port=${PORT:9000}).
ENV PORT=8080
EXPOSE 8080
# java como PID 1 para recibir SIGTERM. Heap al 75% de la memoria del contenedor; ante un OOM la
# instancia termina y Cloud Run la reemplaza en vez de quedar degradada.
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-XX:+ExitOnOutOfMemoryError", "-jar", "app.jar"]
