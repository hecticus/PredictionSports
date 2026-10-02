# Makefile para el proyecto Play Framework
# Configuración de Java 8
JAVA_HOME := /Library/Java/JavaVirtualMachines/temurin-8.jdk/Contents/Home
PATH := $(JAVA_HOME)/bin:$(PATH)

# Variables
PROJECT_DIR := .
SBT := sbt
MYSQL_CONTAINER := gpaapi-mysql-dev
DEV_DB_ENV := -Dhecticusvars.ip=127.0.0.1 -Dhecticusvars.port=3306 -Dhecticusvars.login=root -Dhecticusvars.password=root -DapplyEvolutions.default=true

# Exportar variables de entorno
export JAVA_HOME
export PATH

.PHONY: help run clean compile test status stop restart logs check-java

# Mostrar ayuda por defecto
help:
	@echo "Comandos disponibles para el proyecto Play Framework:"
	@echo ""
	@echo "  make run          - Ejecutar la aplicación"
	@echo "  make dev-db       - Levantar MySQL dev en Docker (127.0.0.1:3306) + schema"
	@echo "  make dev-db-down  - Bajar el MySQL dev (borra datos)"
	@echo "  make dev-run      - Levantar la app contra la DB dev (background)"
	@echo "  make dev-stop     - Detener la app dev"
	@echo "  make dev-logs     - Ver logs de la app dev (tail -f)"
	@echo "  make dev-reset    - Reiniciar DB dev desde cero"
	@echo "  make clean        - Limpiar el proyecto"
	@echo "  make compile      - Compilar el proyecto"
	@echo "  make test         - Ejecutar tests"
	@echo "  make check-java   - Verificar versión de Java"
	@echo "  make status       - Mostrar estado del proyecto"
	@echo "  make stop         - Detener la aplicación (si está corriendo)"
	@echo "  make restart      - Reiniciar la aplicación"
	@echo "  make logs         - Mostrar logs de la aplicación"
	@echo ""
	@echo "Asegúrate de estar en el directorio: $(PROJECT_DIR)"

# Verificar Java
check-java:
	@echo "Verificando configuración de Java..."
	@echo "JAVA_HOME: $(JAVA_HOME)"
	@java -version
	@echo ""

# Ejecutar la aplicación
run: check-java
	@echo "Iniciando aplicación Play Framework..."
	@echo "Directorio: $(PWD)"
	@echo "Asegúrate de estar en: $(PROJECT_DIR)"
	@$(SBT) run

# Limpiar proyecto
clean: check-java
	@echo "Limpiando proyecto..."
	@$(SBT) clean

# Compilar proyecto
compile: check-java
	@echo "Compilando proyecto..."
	@$(SBT) compile

# Ejecutar tests
test: check-java
	@echo "Ejecutando tests..."
	@$(SBT) test

# Mostrar estado
status:
	@echo "Estado del proyecto:"
	@echo "Directorio actual: $(PWD)"
	@echo "Directorio esperado: $(PROJECT_DIR)"
	@echo "Java configurado: $(JAVA_HOME)"
	@if [ -f "build.sbt" ]; then echo "✓ build.sbt encontrado"; else echo "✗ build.sbt NO encontrado"; fi
	@if [ -d "app" ]; then echo "✓ Directorio app encontrado"; else echo "✗ Directorio app NO encontrado"; fi
	@if [ -d "conf" ]; then echo "✓ Directorio conf encontrado"; else echo "✗ Directorio conf NO encontrado"; fi

# Detener aplicación (útil si está corriendo en background)
stop:
	@echo "Intentando detener procesos de Play Framework..."
	@pkill -f "play.core.server" || echo "No hay procesos corriendo"

# Reiniciar aplicación
restart: stop
	@echo "Esperando 3 segundos..."
	@sleep 3
	@$(MAKE) run

# Mostrar logs (si la app está corriendo)
logs:
	@echo "Logs de la aplicación (si está disponible):"
	@if [ -f "logs/application.log" ]; then tail -f logs/application.log; else echo "No se encontraron logs"; fi

# Comando de desarrollo (con auto-reload)
dev: check-java
	@echo "Iniciando en modo desarrollo con auto-reload..."
	@$(SBT) ~run

# Comando para ejecutar en background
run-bg: check-java
	@echo "Iniciando aplicación en background..."
	@nohup $(SBT) run > app.log 2>&1 &
	@echo "Aplicación iniciada en background. Ver logs con: make logs-bg"

# Ver logs de background
logs-bg:
	@tail -f app.log

# Limpiar completamente (incluye target y logs)
clean-all: clean
	@echo "Limpieza completa..."
	@rm -rf target/
	@rm -rf project/target/
	@rm -rf logs/*.log
	@rm -f app.log
	@echo "Limpieza completa terminada"

# Verificar dependencias
check-deps:
	@echo "Verificando dependencias..."
	@$(SBT) dependencyTree

# Actualizar dependencias
update:
	@echo "Actualizando dependencias..."
	@$(SBT) update

# ============ DEV con Docker (DB de pruebas) ============

# Levantar MySQL dev en Docker + crear tablas de actividad
dev-db:
	@echo "Levantando MySQL dev en Docker..."
	@docker compose -f docker-compose.dev.yml up -d
	@echo "Esperando a que MySQL esté listo..."
	@until docker exec $(MYSQL_CONTAINER) mysqladmin ping -h localhost -uroot -proot --silent > /dev/null 2>&1; do sleep 1; done
	@docker exec -i $(MYSQL_CONTAINER) mysql -uroot -proot extapi < conf/dev-schema.sql
	@echo "DB dev lista: 127.0.0.1:3306 (root/root, db=extapi)"

# Bajar MySQL dev (elimina el volumen con los datos)
dev-db-down:
	@docker compose -f docker-compose.dev.yml down -v || true
	@echo "MySQL dev detenido y volumen eliminado"

# Levantar la app (stage) contra la DB dev en background
dev-run: dev-db
	@echo "Compilando y empaquetando (sbt stage)..."
	@$(SBT) stage
	@-kill $$(cat target/universal/stage/RUNNING_PID 2>/dev/null) 2>/dev/null || true
	@rm -f target/universal/stage/RUNNING_PID
	@nohup target/universal/stage/bin/extapi $(DEV_DB_ENV) -Dhttp.port=9000 > app-dev.log 2>&1 &
	@sleep 10
	@curl -s -o /dev/null http://localhost:9000/health && echo "App dev corriendo en http://localhost:9000/admin/dashboard (admin / humbilumby_2026)" || echo "Aún arrancando... revisa: make dev-logs"
	@echo "Logs: make dev-logs"

# Detener la app dev
dev-stop:
	@-kill $$(cat target/universal/stage/RUNNING_PID 2>/dev/null) 2>/dev/null || true
	@-pkill -f "play.core.server" 2>/dev/null || true
	@rm -f target/universal/stage/RUNNING_PID
	@echo "App dev detenida"

# Logs de la app dev
dev-logs:
	@tail -f app-dev.log

# Reiniciar la DB dev desde cero
dev-reset: dev-db-down dev-db