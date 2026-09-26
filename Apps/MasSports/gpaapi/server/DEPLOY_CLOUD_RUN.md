# Deploy a Google Cloud Run — GPAAPI

Hay **dos imágenes** listas. Eliges cuál desplegar (o corres ambas en dos servicios separados):

| App | Contexto de build | Dockerfile | Imagen sugerida |
|---|---|---|---|
| **Nueva** (Spring Boot 3 / JDK 17) | `springboot/` | `springboot/Dockerfile` | `gpaapi-spring:2.7.0` |
| **Legacy** (Play 2.5 / JDK 8) | `server/` | `server/Dockerfile` | `gpaapi-legacy:2.7.0` |

Ambas exponen `$PORT` (Cloud Run lo inyecta, por defecto **8080**) y tienen health check en `GET /health`.

---

## 1. Requisitos previos

```bash
gcloud auth login
gcloud config set project TU_PROYECTO
export PROJECT=$(gcloud config get-value project)
export REGION=us-central1
export REPO=$REGION-docker.pkg.dev/$PROJECT/gpaapi

gcloud services enable run.googleapis.com artifactregistry.googleapis.com \
  sqladmin.googleapis.com secretmanager.googleapis.com vpcaccess.googleapis.com

gcloud artifacts repositories create gpaapi \
  --repository-format=docker --location=$REGION
```

## 2. Cloud SQL (MySQL) y red

Recomendado para **ambas** apps: Cloud SQL con **IP privada** + **Serverless VPC Access connector**
(la app legacy solo habla TCP con `host:puerto`, así que esto es lo más simple y homogéneo).

```bash
gcloud sql instances create mysql-gpaapi \
  --database-version=MYSQL_8_0 --tier=db-f1-micro --region=$REGION \
  --network=default --no-assign-ip

gcloud sql databases create extapi --instance=mysql-gpaapi

# usuario de la app (ver nota de autenticacion para el legacy mas abajo)
gcloud sql users create extapi --instance=mysql-gpaapi --password='<PASSWORD>'

gcloud compute networks vpc-access connectors create gpaapi-conn \
  --region=$REGION --range=10.8.0.0/28
```

Luego usa `DB_HOST=<IP_privada_de_Cloud_SQL>` y `--vpc-connector gpaapi-conn --vpc-egress private-ranges-only`.

> Alternativa para la app **nueva**: conexión por socket (`--add-cloudsql-instances`) usando el
> `mysql-socket-factory` (requiere agregar la dependencia y cambiar el formato de `DB_HOST`). Con
> IP privada no hace falta tocar código.

## 3. Secretos (Secret Manager)

Estos son los valores que **NO** deben ir en texto plano. Créalos una vez:

```bash
printf '%s' '<PASSWORD_DB>'        | gcloud secrets create db-password --data-file=-
printf '%s' '<SECRETO_APP>'        | gcloud secrets create application-secret --data-file=-
# solo app NUEVA (Spring):
printf '%s' '<APPLAND_SECRET>'     | gcloud secrets create appland-service-secret --data-file=-
printf '%s' '<SEXY_KEY>'           | gcloud secrets create sexy-postback-key --data-file=-
printf '%s' '<CHAT_TOKEN>'         | gcloud secrets create chat-security-token --data-file=-
printf '%s' '<PAXXION_HASH>'       | gcloud secrets create paxxion-hash --data-file=-
printf '%s' '<MAXGAME_HASH>'       | gcloud secrets create maxgame-hash --data-file=-
printf '%s' '<MAIL_PASSWORD>'      | gcloud secrets create mail-password --data-file=-
```

| Secreto (Secret Manager) | Env var | ¿Quién lo usa? | Descripción |
|---|---|---|---|
| `db-password` | `DB_PASSWORD` | nueva + legacy | Password del usuario MySQL |
| `application-secret` | `APPLICATION_SECRET` | nueva + legacy | Secreto criptográfico de la app |
| `appland-service-secret` | `APPLAND_SERVICE_SECRET` | **nueva** | Firma HMAC de AppLand |
| `sexy-postback-key` | `SEXY_POSTBACK_KEY` | **nueva** | Key de postback lktrack/SEXY |
| `chat-security-token` | `CHAT_SECURITY_TOKEN` | **nueva** | Token de postback mobidea/CHAT |
| `paxxion-hash` | `PAXXION_HASH` | **nueva** | Hash Level23 de Paxxion |
| `maxgame-hash` | `MAXGAME_HASH` | **nueva** | Hash Level23 de MaxGame / Ciudad Juego |
| `mail-password` | `MAIL_PASSWORD` | **nueva** | Password SMTP |

> En el **legacy** estos últimos 6 están **hardcodeados en el código** (no son configurables por env),
> excepto `DB_PASSWORD` y `APPLICATION_SECRET`. Si migras, quedan cubiertos por la app nueva.

## 4. Variables de entorno no secretas

**App NUEVA (Spring):**

| Variable | Ejemplo | Notas |
|---|---|---|
| `DB_HOST` | `10.20.0.3` | IP privada de Cloud SQL |
| `DB_PORT` | `3306` | |
| `DB_NAME` | `extapi` | |
| `DB_USER` | `extapi` | |
| `DB_POOL_SIZE` | `10` | |
| `APPLAND_SUBSCRIPTION_ID` | `HECTI_MOVIS_U_VE` | |
| `APPLAND_SERVICE_KEY` | `appland-hecticus` | |
| `KRAKEN_BASE_URL` | `http://api.hecticus.com/client` | |
| `KRAKEN_EVENTS_URL` | `http://02.kapp.hecticus.com/ws/receiveMO.php` | |
| `SILVER_POSTBACK_URL` | `http://offers.silversol.affise.com/postback` | |
| `MANHATTAN_NOTIFY_URL` | `http://146.20.33.21:8080/man-gateway-web/api/DigitalSuccessNotification/notify` | |
| `SEXY_POSTBACK_URL` | `https://www.lktrack.com/adserver/delivery/cv.php` | |
| `CHAT_POSTBACK_URL` | `https://postback.mobidea.ai/postback` | |
| `PAXXION_HANDLER` | `11240` | |
| `MAXGAME_HANDLER` | `11191` | |
| `MAIL_HOST` / `MAIL_PORT` | `smtp.gmail.com` / `587` | |
| `MAIL_USER` | `...` | |
| `MAIL_FROM` / `MAIL_TO` | `...` | |
| `CORS_ALLOWED_ORIGINS` | `*` | |
| `BASE_URL` | `https://...` | |
| `PORT` | `8080` | lo setea Cloud Run |

**App LEGACY (Play):**

| Variable | Ejemplo | Notas |
|---|---|---|
| `DB_HOST` | `10.20.0.3` | |
| `DB_PORT` | `3306` | |
| `DB_NAME` | `extapi` | |
| `DB_USER` | `extapi` | |
| `PORT` | `8080` | lo setea Cloud Run |

## 5. Build y push

```bash
# App nueva
docker build -t $REPO/gpaapi-spring:2.7.0 springboot
docker push $REPO/gpaapi-spring:2.7.0

# Legacy
docker build -t $REPO/gpaapi-legacy:2.7.0 server
docker push $REPO/gpaapi-legacy:2.7.0
```

## 6. Deploy

**App nueva:**
```bash
gcloud run deploy gpaapi-spring \
  --image $REPO/gpaapi-spring:2.7.0 \
  --region $REGION --allow-unauthenticated --port 8080 \
  --vpc-connector gpaapi-conn --vpc-egress private-ranges-only \
  --add-cloudsql-instances $PROJECT:$REGION:mysql-gpaapi \
  --set-env-vars DB_HOST=10.20.0.3,DB_PORT=3306,DB_NAME=extapi,DB_USER=extapi,DB_POOL_SIZE=10,APPLAND_SUBSCRIPTION_ID=HECTI_MOVIS_U_VE,APPLAND_SERVICE_KEY=appland-hecticus,KRAKEN_BASE_URL=http://api.hecticus.com/client,KRAKEN_EVENTS_URL=http://02.kapp.hecticus.com/ws/receiveMO.php,SILVER_POSTBACK_URL=http://offers.silversol.affise.com/postback,MANHATTAN_NOTIFY_URL=http://146.20.33.21:8080/man-gateway-web/api/DigitalSuccessNotification/notify,SEXY_POSTBACK_URL=https://www.lktrack.com/adserver/delivery/cv.php,CHAT_POSTBACK_URL=https://postback.mobidea.ai/postback,PAXXION_HANDLER=11240,MAXGAME_HANDLER=11191,MAIL_HOST=smtp.gmail.com,MAIL_PORT=587,CORS_ALLOWED_ORIGINS='*' \
  --set-secrets DB_PASSWORD=db-password:latest,APPLICATION_SECRET=application-secret:latest,APPLAND_SERVICE_SECRET=appland-service-secret:latest,SEXY_POSTBACK_KEY=sexy-postback-key:latest,CHAT_SECURITY_TOKEN=chat-security-token:latest,PAXXION_HASH=paxxion-hash:latest,MAXGAME_HASH=maxgame-hash:latest,MAIL_PASSWORD=mail-password:latest
```

**Legacy:**
```bash
gcloud run deploy gpaapi-legacy \
  --image $REPO/gpaapi-legacy:2.7.0 \
  --region $REGION --allow-unauthenticated --port 8080 \
  --vpc-connector gpaapi-conn --vpc-egress private-ranges-only \
  --add-cloudsql-instances $PROJECT:$REGION:mysql-gpaapi \
  --set-env-vars DB_HOST=10.20.0.3,DB_PORT=3306,DB_NAME=extapi,DB_USER=extapi \
  --set-secrets DB_PASSWORD=db-password:latest,APPLICATION_SECRET=application-secret:latest
```

Para “elegir” cuál queda activo: despliega ambas en servicios distintos y enruta con un
load balancer, **o** despliega la elegida sobre el **mismo nombre de servicio** (`gpaapi`),
que reemplaza la revisión anterior.

## 7. Notas importantes

- **Scheduler**: el legacy incluye `DisableCheckerTask` (loop `while(true)` en cada instancia). En
  Cloud Run con varias instancias se **duplica** el trabajo. La app nueva **no lo tiene**.
- **Autenticación MySQL del legacy**: usa el conector `mysql-connector-java 5.1.47`, que **no
  soporta `caching_sha2_password`**. En Cloud SQL MySQL 8 crea el usuario con legacy auth:
  ```sql
  ALTER USER 'extapi'@'%' IDENTIFIED WITH mysql_native_password BY '<PASSWORD>';
  ```
  (La app nueva usa el conector `mysql-connector-j` 8.x, sin ese problema.)
- **Escalado**: Cloud Run escala a cero; si algún flujo depende de procesos en background, revisar.
- **Legacy EOL**: Play 2.5 / Scala 2.11 / JDK 8 sin parches de seguridad; úsalo solo como puente.
- **Timeout HTTP**: la app nueva tiene timeouts de 120s en llamadas externas (igual que el legacy).
