# Permisos para desplegar GPAAPI en Cloud Run (para el admin)

**Proyecto:** `<PROJECT_ID>`
**Usuario que despliega:** `<tu-email>`

---

## 1. Listado de permisos (roles IAM)

**Usuario `<tu-email>` a nivel de proyecto:**

- `roles/run.admin` — desplegar Cloud Run
- `roles/iam.serviceAccountUser` — usar el service account de runtime
- `roles/artifactregistry.writer` — subir imágenes
- `roles/artifactregistry.admin` — crear el repositorio Docker (una vez)
- `roles/cloudbuild.builds.editor` — construir con Cloud Build
- `roles/storage.admin` — bucket de staging de Cloud Build
- `roles/logging.viewer` — ver logs
- `roles/monitoring.viewer` — ver métricas
- `roles/cloudsql.admin` — crear/usar Cloud SQL
- `roles/secretmanager.admin` — crear/usar secretos
- `roles/vpcaccess.admin` — crear el conector VPC
- `roles/compute.networkAdmin` — red del conector
- `roles/iam.serviceAccountAdmin` — crear service accounts
- `roles/serviceusage.serviceUsageAdmin` — habilitar APIs

**Si la infraestructura ya existe y solo se despliega, con esto alcanza:**
`roles/run.admin`, `roles/iam.serviceAccountUser`, `roles/artifactregistry.writer`, `roles/logging.viewer`

---

## 2. Service accounts

| Service account | Roles |
|---|---|
| `gpaapi-runtime@<PROJECT_ID>.iam.gserviceaccount.com` (runtime de la app) | `roles/cloudsql.client`, `roles/secretmanager.secretAccessor` |
| `<NUMERO_PROYECTO>@cloudbuild.gserviceaccount.com` (Cloud Build por defecto) | `roles/run.admin`, `roles/iam.serviceAccountUser`, `roles/artifactregistry.writer`, `roles/logging.logWriter` |

---

## 3. Script para ejecutar

> Solo puede ejecutarlo alguien con `roles/owner` o `roles/resourcemanager.projectIamAdmin`.
> Reemplazar `PROJECT_ID` y `USER_EMAIL` antes de correr.

```bash
#!/usr/bin/env bash
set -euo pipefail

# ---- parametros ----
PROJECT_ID="<PROJECT_ID>"
USER_EMAIL="<tu-email>"

# ---- APIs necesarias ----
gcloud services enable \
  run.googleapis.com \
  artifactregistry.googleapis.com \
  cloudbuild.googleapis.com \
  sqladmin.googleapis.com \
  secretmanager.googleapis.com \
  vpcaccess.googleapis.com \
  compute.googleapis.com \
  iam.googleapis.com \
  --project="$PROJECT_ID"

# ---- roles del usuario ----
for ROLE in \
  roles/run.admin \
  roles/iam.serviceAccountUser \
  roles/artifactregistry.writer \
  roles/artifactregistry.admin \
  roles/cloudbuild.builds.editor \
  roles/storage.admin \
  roles/logging.viewer \
  roles/monitoring.viewer \
  roles/cloudsql.admin \
  roles/secretmanager.admin \
  roles/vpcaccess.admin \
  roles/compute.networkAdmin \
  roles/iam.serviceAccountAdmin \
  roles/serviceusage.serviceUsageAdmin ; do
  gcloud projects add-iam-policy-binding "$PROJECT_ID" \
    --member="user:$USER_EMAIL" --role="$ROLE" --condition=None --quiet
done

# ---- service account de runtime ----
gcloud iam service-accounts create gpaapi-runtime \
  --display-name="GPAAPI runtime" --project="$PROJECT_ID" || true

gcloud projects add-iam-policy-binding "$PROJECT_ID" \
  --member="serviceAccount:gpaapi-runtime@$PROJECT_ID.iam.gserviceaccount.com" \
  --role="roles/cloudsql.client" --condition=None --quiet

gcloud projects add-iam-policy-binding "$PROJECT_ID" \
  --member="serviceAccount:gpaapi-runtime@$PROJECT_ID.iam.gserviceaccount.com" \
  --role="roles/secretmanager.secretAccessor" --condition=None --quiet

gcloud iam service-accounts add-iam-policy-binding \
  "gpaapi-runtime@$PROJECT_ID.iam.gserviceaccount.com" \
  --member="user:$USER_EMAIL" --role="roles/iam.serviceAccountUser" --quiet

# ---- service account de Cloud Build ----
PROJECT_NUMBER="$(gcloud projects describe "$PROJECT_ID" --format='value(projectNumber)')"
CB="$PROJECT_NUMBER@cloudbuild.gserviceaccount.com"

for ROLE in \
  roles/run.admin \
  roles/iam.serviceAccountUser \
  roles/artifactregistry.writer \
  roles/logging.logWriter ; do
  gcloud projects add-iam-policy-binding "$PROJECT_ID" \
    --member="serviceAccount:$CB" --role="$ROLE" --condition=None --quiet
done

echo "Permisos aplicados correctamente."
```

---

## 4. Nota final para el admin

Si se va a exponer el servicio públicamente (`--allow-unauthenticated`), verificar que la
política de organización `constraints/iam.allowedPolicyMemberDomains` no bloquee a `allUsers`.
Consulta:

```bash
gcloud org-policies describe constraints/iam.allowedPolicyMemberDomains --project="$PROJECT_ID"
```
