# Secretos y variables para Cloud Run

Proyecto `hecticus-173000`, servicio `gpaapi` (app Spring Boot). Qué crear en **Secret Manager** y qué
**variables de entorno** configura el pipeline (`cloudbuild.yaml`).

**Convención:** el secreto se llama `GPAAPI_` + el nombre de la variable de entorno (`DB_PASSWORD` →
`GPAAPI_DB_PASSWORD`). Solo los secretos con el prefijo `GPAAPI_` son administrables por el equipo de gpaapi;
uno sin prefijo se puede crear pero no se puede usar.

---

## 1. Secret Manager (valores sensibles)

| Secreto (Secret Manager) | Variable (env) | App nueva | App legacy | Descripción |
|---|---|:--:|:--:|---|
| `GPAAPI_DB_PASSWORD` | `DB_PASSWORD` | ✅ | ✅ | Password del usuario MySQL |
| `GPAAPI_APPLICATION_SECRET` | `APPLICATION_SECRET` | ✅ | ✅ | Secreto criptográfico de la app |
| `GPAAPI_APPLAND_SERVICE_SECRET` | `APPLAND_SERVICE_SECRET` | ✅ | ❌ | Firma HMAC de AppLand (hardcodeado en legacy) |
| `GPAAPI_SEXY_POSTBACK_KEY` | `SEXY_POSTBACK_KEY` | ✅ | ❌ | Key postback lktrack/SEXY (hardcodeado en legacy) |
| `GPAAPI_CHAT_SECURITY_TOKEN` | `CHAT_SECURITY_TOKEN` | ✅ | ❌ | Token postback mobidea/CHAT (hardcodeado en legacy) |
| `GPAAPI_PAXXION_HASH` | `PAXXION_HASH` | ✅ | ❌ | Hash Level23 de Paxxion (hardcodeado en legacy) |
| `GPAAPI_MAXGAME_HASH` | `MAXGAME_HASH` | ✅ | ❌ | Hash Level23 de MaxGame / Ciudad Juego (hardcodeado en legacy) |
| `GPAAPI_MAIL_PASSWORD` | `MAIL_PASSWORD` | ✅ | ❌ | Password SMTP (hardcodeado en legacy) |

Los 8 son obligatorios: si falta uno, Cloud Run no crea la revisión y el tráfico sigue en la anterior.

### Crearlos

El valor se lee por stdin (pégalo y termina con Ctrl-D), así no queda en el historial del shell:

```bash
P=hecticus-173000
for s in DB_PASSWORD APPLICATION_SECRET APPLAND_SERVICE_SECRET SEXY_POSTBACK_KEY \
         CHAT_SECURITY_TOKEN PAXXION_HASH MAXGAME_HASH MAIL_PASSWORD; do
  echo "Valor de GPAAPI_$s:"
  gcloud secrets create "GPAAPI_$s" --replication-policy=automatic --data-file=- --project=$P
done
```

Actualizar un valor existente:
```bash
gcloud secrets versions add GPAAPI_DB_PASSWORD --data-file=- --project=hecticus-173000
```

### Dar acceso a la service account de Cloud Run

```bash
for s in DB_PASSWORD APPLICATION_SECRET APPLAND_SERVICE_SECRET SEXY_POSTBACK_KEY \
         CHAT_SECURITY_TOKEN PAXXION_HASH MAXGAME_HASH MAIL_PASSWORD; do
  gcloud secrets add-iam-policy-binding "GPAAPI_$s" --project=hecticus-173000 \
    --member="serviceAccount:run-gpaapi-sa@hecticus-173000.iam.gserviceaccount.com" \
    --role="roles/secretmanager.secretAccessor" >/dev/null
done
```

---

## 2. Variables de entorno NO secretas

Las fija el paso `deploy` de `cloudbuild.yaml`. Las que cambian por entorno son *substitutions* del trigger.

### 2.1 App nueva (Spring Boot)

| Variable | Valor en dev | Origen |
|---|---|---|
| `DB_HOST` | IP privada de la BD | substitution `_DB_HOST` (obligatoria) |
| `DB_PORT` / `DB_NAME` / `DB_USER` | `3306` / `extapi` / `extapi` | `_DB_PORT`, `_DB_NAME`, `_DB_USER` |
| `DB_POOL_SIZE` | `10` | `_DB_POOL_SIZE` |
| `MAIL_USER` | cuenta SMTP | `_MAIL_USER` |
| `MAIL_FROM` / `MAIL_TO` | `alarma@hecticus.com` / `soporte.daemons@hecticus.com` | `_MAIL_FROM`, `_MAIL_TO` |
| `LOGGING_LEVEL_COM_HECTICUS_GPAAPI` | `INFO` | `_LOG_LEVEL` |
| `APPLAND_SUBSCRIPTION_ID` | `HECTI_MOVIS_U_VE` | fijo en el pipeline |
| `APPLAND_SERVICE_KEY` | `appland-hecticus` | fijo |
| `KRAKEN_BASE_URL` | `http://api.hecticus.com/client` | fijo |
| `KRAKEN_EVENTS_URL` | `http://02.kapp.hecticus.com/ws/receiveMO.php` | fijo |
| `SILVER_POSTBACK_URL` | `http://offers.silversol.affise.com/postback` | fijo |
| `MANHATTAN_NOTIFY_URL` | `http://146.20.33.21:8080/man-gateway-web/api/DigitalSuccessNotification/notify` | fijo |
| `SEXY_POSTBACK_URL` | `https://www.lktrack.com/adserver/delivery/cv.php` | fijo |
| `CHAT_POSTBACK_URL` | `https://postback.mobidea.ai/postback` | fijo |
| `PAXXION_HANDLER` / `MAXGAME_HANDLER` | `11240` / `11191` | fijo |
| `MAIL_HOST` / `MAIL_PORT` | `smtp.gmail.com` / `587` | fijo |
| `CORS_ALLOWED_ORIGINS` | `*` | fijo |
| `MANAGEMENT_HEALTH_MAIL_ENABLED` | `false` | fijo (sin esto `/actuator/health` da 503 si SMTP no responde) |
| `PORT` | `8080` | lo inyecta Cloud Run |

### 2.2 App legacy (Play)

No se despliega en Cloud Run (ver `DEPLOY.md`). Solo lee `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `PORT` y los
secretos `DB_PASSWORD` y `APPLICATION_SECRET`; AppLand/Sexy/Chat/Paxxion/MaxGame/Mail están **hardcodeados** en su código.

---

## 3. Notas

- **Rotación:** con `:latest`, cada instancia nueva lee la última versión al arrancar. Para aplicarla ya, redesplegar.
- **Nunca** subir `.env` ni secretos al repositorio.
