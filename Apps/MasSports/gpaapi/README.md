# GPAAPI — MasSports

Backend de campañas y suscripciones de MasSports/Hecticus. Expone landing pages de campañas,
flujos de PIN/suscripción (Digitel, AppLand, Kraken, Silver, Manhattan), tracking de clicks y
postbacks de conversión.

El repositorio contiene **dos implementaciones de la misma API**:

| Carpeta | Stack | Descripción |
|---|---|---|
| `server/` | **Play Framework 2.5.5** (Java/Scala 2.11, sbt 0.13.11, JDK 8) | Implementación original (legacy). |
| `springboot/` | **Spring Boot 3.3** (Java 17, Maven) | Reescritura moderna, mismas rutas y comportamiento. |

Ambas escuchan en el puerto `8080` (Cloud Run inyecta `$PORT`) y exponen `GET /health`.

```
gpaapi/
├── README.md            # este archivo
├── SECRETS.md           # variables/secretos a crear en Secret Manager
├── DEPLOY_CLOUD_RUN.md  # (en server/) guía de deploy de ambas imagenes
├── .env / .env.example  # entorno local para ambas apps (no se commitea)
├── docker-compose.yml   # MySQL para desarrollo (+ perfil "apps" para docker total)
├── justfile             # atajos para levantar todo
├── server/              # app legacy (Play)
└── springboot/          # app nueva (Spring Boot)
```

## Arquitectura funcional

- **Landings de campaña**: MaxGame, Ciudad Juego, LearnLive, Blive, Paxxion/Klike, portales.
- **Suscripción/PIN**: `WapSite` (getpin/confirm), `RegisterController` (clienteExterno).
- **Integraciones**: AppLand (firma HMAC), Kraken (usuarios/alta), Silver, Manhattan,
  Digitel *(SOAP eliminado en la versión nueva; las rutas siguen vivas)*.
- **Tracking**: click parameters (`CLICKID`, `token`, `tr_token`, `transaction_id`, `mobidea_id`,
  `gclid`…), actividades (`*_activity`) y postbacks (Level23, Mobipium, lktrack, mobidea).
- **Persistencia**: MySQL/Ebean en legacy → MySQL/JPA (Hibernate) en Spring.

> **Diferencia clave**: la versión nueva **elimina el scheduler** (`DisableCheckerTask`), que en
> el legacy corría un `while(true)` por instancia.

## Requisitos

- Docker (para MySQL).
- **App nueva**: JDK 17 + Maven.
- **App legacy**: JDK 8 arm64 (`~/.jdks/zulu8`, ver abajo) + sbt.
- [`just`](https://github.com/casey/just) (`brew install just`).

### JDK 8 arm64 (Apple Silicon)
Adoptium **no** publica JDK 8 para macOS arm64; usa **Zulu 8** (o Liberica 8):
```bash
curl -sL -o /tmp/zulu8.tgz \
  https://cdn.azul.com/zulu/bin/zulu8.96.0.205-ca-jdk8.0.504-macosx_aarch64.tar.gz
mkdir -p ~/.jdks && tar xzf /tmp/zulu8.tgz -C ~/.jdks
mv ~/.jdks/zulu8.96.0.205-ca-jdk8.0.504-macosx_aarch64 ~/.jdks/zulu8
```
(Alternativa: `sudo softwareupdate --install-rosetta --agree-to-license` y usar un JDK 8 x64, más lento.)

## Desarrollo local

```bash
cp .env.example .env      # ajustar JAVA8_HOME si hace falta
just db                   # MySQL en docker (schema + seed)
just spring               # app nueva   -> http://localhost:8080
just legacy               # app legacy  -> http://localhost:9000   (otra terminal)
```

Levantar **ambas** en background y ver logs:

```bash
just dev                  # db + legacy + spring
just logs-spring
just logs-legacy
just ps
just stop                 # detiene apps y contenedores
```

Todo dentro de docker (construye las dos imágenes; el legacy tarda unos minutos):

```bash
just full                 # docker compose --profile apps up --build
```

### Base de datos
- Schema y seed: `springboot/docker/mysql/init/` (13 tablas).
- `just db-reset` borra el volumen y recrea.
- `just db-shell` abre consola MySQL.
- **Nota**: la tabla `learn_live_rdactivity` lleva ese nombre exacto (convención de Ebean).

## Comandos útiles (`just`)

| Comando | Qué hace |
|---|---|
| `just db` / `just db-down` / `just db-reset` / `just db-shell` | ciclo de vida de MySQL |
| `just spring` / `just legacy` | app nueva / legacy en primer plano |
| `just dev` / `just stop` / `just logs-*` / `just ps` | ambos en background + logs |
| `just test` | tests de la app nueva |
| `just compile-legacy` | compila el legacy |
| `just images` | construye `gpaapi-spring:2.7.0` y `gpaapi-legacy:2.7.0` |
| `just full` | db + ambas apps en docker |

## Deploy

- **Secretos**: ver [`SECRETS.md`](SECRETS.md).
- **Cloud Run**: ver [`server/DEPLOY_CLOUD_RUN.md`](server/DEPLOY_CLOUD_RUN.md).

## Notas

- El legacy está **EOL** (Play 2.5 / Scala 2.11 / JDK 8); usar como puente.
- El legacy solo externaliza `DB_*` y `APPLICATION_SECRET`; el resto de secretos están en el código.
- El conector MySQL del legacy (`5.1.47`) no soporta `caching_sha2_password`; usar
  `mysql_native_password` en Cloud SQL.
