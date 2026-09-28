# Deploy de GPAAPI en Cloud Run (`hecticus-173000`)

Se despliega la **app Spring Boot** (`springboot/`) como servicio `gpaapi` en `us-east1`. El legacy (Play 2.5)
**no** va a Cloud Run: sigue en las VMs hasta que la app nueva lo reemplace (ver "Por qué no el legacy").

| Pieza | Valor |
|---|---|
| Servicio Cloud Run | `gpaapi` (us-east1) |
| Imagen | `us-east1-docker.pkg.dev/hecticus-173000/gpaapi/gpaapi-spring:<BUILD_ID>`, desplegada por digest |
| Service account | `run-gpaapi-sa@hecticus-173000.iam.gserviceaccount.com` |
| Red | conector `connector-dev`; egress `private-ranges-only` (o `all-traffic` para salir por la IP fija del Cloud NAT) |
| Pipeline | `cloudbuild.yaml` (build + tests + push + deploy) |
| Secretos | `GPAAPI_*` en Secret Manager, ver `SECRETS.md` |

La SA, el repo de imágenes y los permisos ya existen. No hace falta crear red, conector ni habilitar APIs.

## 1. Antes del primer deploy

1. **Secretos:** crear los 8 `GPAAPI_*` y darle acceso a `run-gpaapi-sa` (`SECRETS.md`).
2. **Base de datos — PENDIENTE, todavía no existe.** Hay que definir la instancia (Cloud SQL con IP privada en
   `hecticusvpc`, us-east1), crear la BD `extapi` y el usuario `extapi`, y cargar el schema/datos. Su IP privada es
   `_DB_HOST`, obligatoria en el trigger: sin ella el paso `deploy` falla a propósito. La app nueva usa
   `mysql-connector-j` 8, así que no necesita `mysql_native_password`.

## 2. Deploy con trigger (recomendado)

Cada push a `main_v2` que toque `springboot/` o `cloudbuild.yaml` construye, corre los tests y despliega.
Crearlo cuando existan la BD y los 8 secretos:

```bash
gcloud builds triggers create github --project=hecticus-173000 --name=gpaapi-spring-dev \
  --repo-owner=hecticus --repo-name=PredictionSports --branch-pattern='^main_v2$' \
  --build-config=Apps/MasSports/gpaapi/cloudbuild.yaml \
  --included-files='Apps/MasSports/gpaapi/springboot/**,Apps/MasSports/gpaapi/cloudbuild.yaml' \
  --substitutions=_DB_HOST=<IP_BD>,_MAIL_USER=<cuenta_smtp>
```

Si falla porque el repo no está conectado, hay que darle acceso a la GitHub App de Cloud Build sobre
`hecticus/PredictionSports` (la instalan los administradores de la org en GitHub).

Correrlo a mano: `gcloud builds triggers run gpaapi-spring-dev --branch=main_v2 --project=hecticus-173000`.

## 3. Deploy manual (sin trigger)

Desde `Apps/MasSports/gpaapi/`. En Mac con Apple Silicon hay que construir para `linux/amd64`:

```bash
TAG=$(git rev-parse --short HEAD)
IMG=us-east1-docker.pkg.dev/hecticus-173000/gpaapi/gpaapi-spring:$TAG
gcloud auth configure-docker us-east1-docker.pkg.dev
docker build --platform=linux/amd64 -t $IMG springboot
docker push $IMG
```

Después, el mismo `gcloud run deploy` del paso `deploy` de `cloudbuild.yaml`, con `--image=$IMG` y los valores
de las substitutions reemplazados.

## 4. Validar

```bash
URL=$(gcloud run services describe gpaapi --region=us-east1 --project=hecticus-173000 --format='value(status.url)')
curl -s -o /dev/null -w '%{http_code}\n' $URL/health           # 200
curl -s -o /dev/null -w '%{http_code}\n' $URL/maxgame_2026
gcloud logging read 'resource.type="cloud_run_revision" AND resource.labels.service_name="gpaapi" AND severity>=ERROR' \
  --project=hecticus-173000 --freshness=1h --limit=20
```

Validado en local (2026-09-28) contra MySQL 8 con el schema de `springboot/docker/mysql/init`: arranque en ~9 s
con 1 CPU / 1 GiB; `/health`, landings (`/maxgame_2026`, `/landing`, `/tyc`, `/extapi/*`, `/cj/*`), tracking
(`/mark_maxgame_2026`) y estáticos responden.

**Rollback:** `gcloud run services update-traffic gpaapi --to-revisions=<revisión_anterior>=100 --region=us-east1 --project=hecticus-173000`.

## 5. Pendiente antes de reemplazar al legacy

- **Job diario de AppLand.** El legacy (`DisableCheckerTask`) llama cada 24 h a `makeUnsubscribeCall` para
  `HECTI_MOVIS_U_VE` y `HECTI_CIUDA_U_VE`: toma de Kraken los usuarios dados de baja ayer y se los notifica a AppLand.
  La app nueva tiene el método pero **nadie lo invoca**. Propuesta: Cloud Run Job con la misma imagen (un
  `CommandLineRunner` activado por variable que ejecuta el método y termina) + Cloud Scheduler diario. No meterlo como
  hilo dentro del servicio: Cloud Run escala a cero y con varias instancias se duplica.
- **IPs de salida.** Hoy los partners ven las IPs públicas de las VMs. Si alguno filtra por IP (Manhattan, AppLand,
  Kraken/HAProxy), usar `_VPC_EGRESS=all-traffic` y pedirles que agreguen la IP fija del Cloud NAT (la da infraestructura).
- **Kraken** (`api.hecticus.com`, `02.kapp.hecticus.com`) pasa por el HAProxy de Hecticus: probar desde Cloud Run
  que responde.
- **Paridad.** La app nueva declara las mismas rutas que el legacy; validar los flujos de PIN/suscripción y postbacks
  con tráfico de prueba antes del corte.
- **Corte:** DNS de `gpaapi.hecticus.com` (hoy apunta al HAProxy) y `_MIN_INSTANCES=1` para evitar arranques en frío
  en las landings. Lo coordina infraestructura.

## Por qué no el legacy en Cloud Run

- `DisableCheckerTask` es un `while(true)` que arranca con cada instancia: se duplica al escalar y Cloud Run le corta
  la CPU entre requests.
- `logback.xml` escribe `logs/application.log`; en Cloud Run el disco es memoria y crece sin límite hasta el OOM.
- Play 2.5 / Scala 2.11 / JDK 8 sin parches de seguridad, y secretos en el código en vez de variables.
- El build declara resolvers que ya no existen (`dl.bintray.com`) o solo HTTP (`http://repo.typesafe.com`); su
  `server/Dockerfile` no está probado desde cero.

## Por qué no `docker compose` en Cloud Run

`docker-compose.yml` es para desarrollo local. `gcloud run compose up` metería todos sus servicios en **un** servicio de
Cloud Run (un contenedor de entrada y el resto como sidecars): el MySQL quedaría como sidecar con disco efímero (una BD
por instancia, que se pierde en cada reinicio), y no permite fijar SA, conector ni secretos de Secret Manager.
En Cloud Run la BD es externa (Cloud SQL o la VM de MySQL) y cada app es un servicio propio.
