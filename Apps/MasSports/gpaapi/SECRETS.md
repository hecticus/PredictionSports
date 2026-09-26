# Secretos y variables para Cloud Run

Lista de **qué crear en Secret Manager** y **qué variables de entorno** configurar.
Convención: el nombre del secreto es kebab-case y la variable de entorno es el `UPPER_SNAKE` correspondiente.

---

## 1. Secret Manager (valores sensibles)

| Secreto (Secret Manager) | Variable (env) | App nueva | App legacy | Descripción |
|---|:--:|:--:|:--:|---|
| `db-password` | `DB_PASSWORD` | ✅ | ✅ | Password del usuario MySQL |
| `application-secret` | `APPLICATION_SECRET` | ✅ | ✅ | Secreto criptográfico de la app |
| `appland-service-secret` | `APPLAND_SERVICE_SECRET` | ✅ | ❌ | Firma HMAC de AppLand (hardcodeado en legacy) |
| `sexy-postback-key` | `SEXY_POSTBACK_KEY` | ✅ | ❌ | Key postback lktrack/SEXY (hardcodeado en legacy) |
| `chat-security-token` | `CHAT_SECURITY_TOKEN` | ✅ | ❌ | Token postback mobidea/CHAT (hardcodeado en legacy) |
| `paxxion-hash` | `PAXXION_HASH` | ✅ | ❌ | Hash Level23 de Paxxion (hardcodeado en legacy) |
| `maxgame-hash` | `MAXGAME_HASH` | ✅ | ❌ | Hash Level23 de MaxGame / Ciudad Juego (hardcodeado en legacy) |
| `mail-password` | `MAIL_PASSWORD` | ✅ | ❌ | Password SMTP (hardcodeado en legacy) |

### Crearlos

```bash
PROJECT=$(gcloud config get-value project)

printf '%s' '<PASSWORD_DB>'    | gcloud secrets create db-password            --data-file=- --replication-policy=automatic
printf '%s' '<SECRETO_APP>'    | gcloud secrets create application-secret      --data-file=- --replication-policy=automatic
printf '%s' '<APPLAND_SECRET>' | gcloud secrets create appland-service-secret  --data-file=- --replication-policy=automatic
printf '%s' '<SEXY_KEY>'       | gcloud secrets create sexy-postback-key       --data-file=- --replication-policy=automatic
printf '%s' '<CHAT_TOKEN>'     | gcloud secrets create chat-security-token     --data-file=- --replication-policy=automatic
printf '%s' '<PAXXION_HASH>'   | gcloud secrets create paxxion-hash            --data-file=- --replication-policy=automatic
printf '%s' '<MAXGAME_HASH>'   | gcloud secrets create maxgame-hash            --data-file=- --replication-policy=automatic
printf '%s' '<MAIL_PASSWORD>'  | gcloud secrets create mail-password           --data-file=- --replication-policy=automatic
```

Actualizar un valor existente:
```bash
printf '%s' '<NUEVO_VALOR>' | gcloud secrets versions add db-password --data-file=-
```

Dar acceso a la cuenta de servicio de Cloud Run:
```bash
SA=$(gcloud run services describe gpaapi-spring --region us-central1 --format='value(spec.template.spec.serviceAccountName)' 2>/dev/null)
for s in db-password application-secret appland-service-secret sexy-postback-key chat-security-token paxxion-hash maxgame-hash mail-password; do
  gcloud secrets add-iam-policy-binding "$s" \
    --member="serviceAccount:${SA}" --role="roles/secretmanager.secretAccessor" >/dev/null
done
```

---

## 2. Variables de entorno NO secretas

### 2.1 App nueva (Spring Boot)

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
| `MAIL_USER` / `MAIL_FROM` / `MAIL_TO` | `...` | |
| `CORS_ALLOWED_ORIGINS` | `*` | |
| `BASE_URL` | `https://...` | |
| `PORT` | `8080` | Cloud Run lo inyecta |

### 2.2 App legacy (Play)

| Variable | Ejemplo | Notas |
|---|---|---|
| `DB_HOST` | `10.20.0.3` | IP privada de Cloud SQL |
| `DB_PORT` | `3306` | |
| `DB_NAME` | `extapi` | |
| `DB_USER` | `extapi` | |
| `PORT` | `8080` | Cloud Run lo inyecta |

> El legacy **solo** lee estos valores + los secretos `DB_PASSWORD` y `APPLICATION_SECRET`.
> AppLand/Sexy/Chat/Paxxion/MaxGame/Mail están **hardcodeados** en su código.

---

## 3. Cómo se montan en Cloud Run

**App nueva:**
```bash
gcloud run deploy gpaapi-spring \
  --image $REPO/gpaapi-spring:2.7.0 --region us-central1 --allow-unauthenticated --port 8080 \
  --vpc-connector gpaapi-conn --vpc-egress private-ranges-only \
  --add-cloudsql-instances $PROJECT:us-central1:mysql-gpaapi \
  --set-env-vars DB_HOST=10.20.0.3,DB_PORT=3306,DB_NAME=extapi,DB_USER=extapi,DB_POOL_SIZE=10,APPLAND_SUBSCRIPTION_ID=HECTI_MOVIS_U_VE,APPLAND_SERVICE_KEY=appland-hecticus,KRAKEN_BASE_URL=http://api.hecticus.com/client,KRAKEN_EVENTS_URL=http://02.kapp.hecticus.com/ws/receiveMO.php,SILVER_POSTBACK_URL=http://offers.silversol.affise.com/postback,MANHATTAN_NOTIFY_URL=http://146.20.33.21:8080/man-gateway-web/api/DigitalSuccessNotification/notify,SEXY_POSTBACK_URL=https://www.lktrack.com/adserver/delivery/cv.php,CHAT_POSTBACK_URL=https://postback.mobidea.ai/postback,PAXXION_HANDLER=11240,MAXGAME_HANDLER=11191,MAIL_HOST=smtp.gmail.com,MAIL_PORT=587,CORS_ALLOWED_ORIGINS='*' \
  --set-secrets DB_PASSWORD=db-password:latest,APPLICATION_SECRET=application-secret:latest,APPLAND_SERVICE_SECRET=appland-service-secret:latest,SEXY_POSTBACK_KEY=sexy-postback-key:latest,CHAT_SECURITY_TOKEN=chat-security-token:latest,PAXXION_HASH=paxxion-hash:latest,MAXGAME_HASH=maxgame-hash:latest,MAIL_PASSWORD=mail-password:latest
```

**App legacy:**
```bash
gcloud run deploy gpaapi-legacy \
  --image $REPO/gpaapi-legacy:2.7.0 --region us-central1 --allow-unauthenticated --port 8080 \
  --vpc-connector gpaapi-conn --vpc-egress private-ranges-only \
  --add-cloudsql-instances $PROJECT:us-central1:mysql-gpaapi \
  --set-env-vars DB_HOST=10.20.0.3,DB_PORT=3306,DB_NAME=extapi,DB_USER=extapi \
  --set-secrets DB_PASSWORD=db-password:latest,APPLICATION_SECRET=application-secret:latest
```

`--set-secrets` mapea `VARIABLE=secreto:version`. Se pueden montar como archivos con
`--set-secrets /ruta/archivo=secreto:latest` si se prefiere.

---

## 4. Notas

- **Cloud SQL MySQL 8 + legacy**: el conector `5.1.47` no soporta `caching_sha2_password`.
  Crear el usuario con auth nativa:
  ```sql
  ALTER USER 'extapi'@'%' IDENTIFIED WITH mysql_native_password BY '<PASSWORD>';
  ```
- **Rotación**: al agregar una versión nueva (`gcloud secrets versions add`), redeployar o usar
  `:latest` (Cloud Run resuelve `latest` en cada arranque de instancia).
- **Nunca** subir `.env` (está en `.gitignore`) ni secretos al repositorio.
