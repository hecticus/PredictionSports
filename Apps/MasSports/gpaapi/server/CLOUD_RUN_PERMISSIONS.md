# Permisos para desplegar GPAAPI en Google Cloud

Documento para enviar al administrador del proyecto GCP. Lista todo lo necesario para **construir la
imagen, subirla, crear la infraestructura (Cloud SQL, secretos, VPC) y desplegar en Cloud Run**.

> Reemplaza `TU_PROYECTO` y `tu.email@dominio.com` por los reales.

---

## 1. APIs que deben estar habilitadas

```bash
gcloud services enable \
  run.googleapis.com \
  artifactregistry.googleapis.com \
  cloudbuild.googleapis.com \
  sqladmin.googleapis.com \
  secretmanager.googleapis.com \
  vpcaccess.googleapis.com \
  compute.googleapis.com \
  iam.googleapis.com
```
Rol para habilitarlas: `roles/serviceusage.serviceUsageAdmin` (o que lo haga el admin una vez).

---

## 2. Roles a nivel de proyecto para tu usuario

| # | Rol | ID | Para qué |
|---|---|---|---|
| 1 | Cloud Run Admin | `roles/run.admin` | Crear/actualizar servicios Cloud Run |
| 2 | Service Account User | `roles/iam.serviceAccountUser` | Usar el service account de runtime al desplegar |
| 3 | Artifact Registry Writer | `roles/artifactregistry.writer` | Push de imágenes al repo |
| 4 | Artifact Registry Admin | `roles/artifactregistry.admin` | (solo una vez) crear el repositorio Docker |
| 5 | Cloud Build Editor | `roles/cloudbuild.builds.editor` | Si construyes con `gcloud builds submit` |
| 6 | Storage Admin | `roles/storage.admin` | Bucket de staging de Cloud Build |
| 7 | Logs Viewer | `roles/logging.viewer` | Ver logs de la app |
| 8 | Monitoring Viewer | `roles/monitoring.viewer` | Ver métricas |
| 9 | Compute Viewer | `roles/compute.viewer` | Ver red/VPC connector en el deploy |
| 10 | Cloud SQL Admin | `roles/cloudsql.admin` | Crear instancia, base y usuario MySQL |
| 11 | Secret Manager Admin | `roles/secretmanager.admin` | Crear/editar secretos |
| 12 | Serverless VPC Access Admin | `roles/vpcaccess.admin` | Crear el conector VPC |
| 13 | Compute Network Admin | `roles/compute.networkAdmin` | Red del conector / reglas |
| 14 | Service Account Admin | `roles/iam.serviceAccountAdmin` | Crear los service accounts dedicados |
| 15 | Service Usage Admin | `roles/serviceusage.serviceUsageAdmin` | Habilitar APIs |
| 16 | Cloud Run Viewer | `roles/run.viewer` | Ver servicios/revisiones/URLs |
| 17 | Cloud SQL Viewer | `roles/cloudsql.viewer` | Ver instancias y conexión (solo lectura) |
| 18 | Browser | `roles/browser` | Ver la jerarquía del proyecto en consola |

**Mínimo imprescindible** si la infraestructura ya existe y solo despliegas:
`roles/run.admin`, `roles/iam.serviceAccountUser`, `roles/artifactregistry.writer`,
`roles/logging.viewer`.

---

## 3. Service Accounts y sus roles

Se recomiendan dos service accounts (que el admin cree y otorgue).

### 3.1 `gpaapi-runtime@TU_PROYECTO.iam.gserviceaccount.com` (runtime de Cloud Run)
| Rol | ID | Para qué |
|---|---|---|
| Cloud SQL Client | `roles/cloudsql.client` | Conectarse a Cloud SQL |
| Secret Manager Secret Accessor | `roles/secretmanager.secretAccessor` | Leer los secretos montados |

### 3.2 `gpaapi-deployer@TU_PROYECTO.iam.gserviceaccount.com` (para CI/CD o Cloud Build)
| Rol | ID |
|---|---|
| Cloud Run Admin | `roles/run.admin` |
| Service Account User | `roles/iam.serviceAccountUser` |
| Artifact Registry Writer | `roles/artifactregistry.writer` |
| Cloud Build Editor | `roles/cloudbuild.builds.editor` |
| Storage Admin | `roles/storage.admin` |

### 3.3 `CLOUD_BUILD_SA@cloudbuild.gserviceaccount.com` (solo si usas `gcloud builds submit`)

El service account por defecto de Cloud Build **no puede auto-otorgarse** permisos; el admin debe
agregárselos. Su email es `<NUMERO_DE_PROYECTO>@cloudbuild.gserviceaccount.com`.

| Rol | ID |
|---|---|
| Cloud Run Admin | `roles/run.admin` |
| Service Account User | `roles/iam.serviceAccountUser` |
| Artifact Registry Writer | `roles/artifactregistry.writer` |
| Logs Writer | `roles/logging.logWriter` |

```bash
PROJECT_NUMBER=$(gcloud projects describe $PROJECT --format='value(projectNumber)')
CB="$PROJECT_NUMBER@cloudbuild.gserviceaccount.com"
for ROLE in roles/run.admin roles/iam.serviceAccountUser \
            roles/artifactregistry.writer roles/logging.logWriter ; do
  gcloud projects add-iam-policy-binding $PROJECT --member="serviceAccount:$CB" --role="$ROLE" --condition=None
done
```

> El **Cloud Run service agent** (`service-<NUMERO_DE_PROYECTO>@serverless-robot-prod.iam.gserviceaccount.com`)
> suele necesitar `roles/artifactregistry.reader` para descargar la imagen. Normalmente ya viene.

---

## 4. Comandos para que el admin conceda los permisos

> Solo pueden otorgar estos roles quien tenga **`roles/owner`** o
> **`roles/resourcemanager.projectIamAdmin`** (o `roles/iam.securityAdmin`) en el proyecto.

```bash
PROJECT=TU_PROYECTO
USER=tu.email@dominio.com

for ROLE in \
  roles/run.admin \
  roles/iam.serviceAccountUser \
  roles/artifactregistry.writer \
  roles/artifactregistry.admin \
  roles/cloudbuild.builds.editor \
  roles/storage.admin \
  roles/logging.viewer \
  roles/monitoring.viewer \
  roles/compute.viewer \
  roles/cloudsql.admin \
  roles/secretmanager.admin \
  roles/vpcaccess.admin \
  roles/compute.networkAdmin \
  roles/iam.serviceAccountAdmin \
  roles/serviceusage.serviceUsageAdmin ; do
  gcloud projects add-iam-policy-binding $PROJECT \
    --member="user:$USER" --role="$ROLE" --condition=None
done
```

Creación de service accounts:

```bash
for SA in gpaapi-runtime gpaapi-deployer ; do
  gcloud iam service-accounts create $SA --project=$PROJECT \
    --display-name="GPAAPI $SA"
done

gcloud projects add-iam-policy-binding $PROJECT \
  --member="serviceAccount:gpaapi-runtime@$PROJECT.iam.gserviceaccount.com" \
  --role="roles/cloudsql.client" --condition=None
gcloud projects add-iam-policy-binding $PROJECT \
  --member="serviceAccount:gpaapi-runtime@$PROJECT.iam.gserviceaccount.com" \
  --role="roles/secretmanager.secretAccessor" --condition=None

for ROLE in roles/run.admin roles/iam.serviceAccountUser \
            roles/artifactregistry.writer roles/cloudbuild.builds.editor roles/storage.admin ; do
  gcloud projects add-iam-policy-binding $PROJECT \
    --member="serviceAccount:gpaapi-deployer@$PROJECT.iam.gserviceaccount.com" \
    --role="$ROLE" --condition=None
done

# permitir que tu usuario use esos SA (impersonacion) al desplegar
for SA in gpaapi-runtime gpaapi-deployer ; do
  gcloud iam service-accounts add-iam-policy-binding \
    $SA@$PROJECT.iam.gserviceaccount.com \
    --member="user:$USER" --role="roles/iam.serviceAccountUser"
done
```

---

## 5. Permiso para exponer el servicio públicamente (`--allow-unauthenticated`)

Para publicar el servicio sin autenticación se necesita:

- `roles/run.admin` (incluye `run.services.setIamPolicy`), **y**
- que **no** exista una política de organización que lo bloquee:

```bash
# consultar restricciones que suelen bloquear el acceso publico
gcloud org-policies describe constraints/iam.allowedPolicyMemberDomains --project=TU_PROYECTO
gcloud org-policies describe constraints/run.allowedIngress --project=TU_PROYECTO
```

Si la politíca `iam.allowedPolicyMemberDomains` está activa, no se puede dar acceso a `allUsers`;
hay que pedirle al admin una **excepción para el proyecto** o usar autenticación con IAM
(`--no-allow-unauthenticated` + `roles/run.invoker` para quien consuma).

---

## 6. Comandos que usarás (referencia)

```bash
# repositorio de imagenes
gcloud artifacts repositories create gpaapi \
  --repository-format=docker --location=us-central1

# build + push (app nueva)
docker build -t us-central1-docker.pkg.dev/$PROJECT/gpaapi/gpaapi-spring:2.7.0 springboot
docker push us-central1-docker.pkg.dev/$PROJECT/gpaapi/gpaapi-spring:2.7.0

# build + push (legacy)
docker build -t us-central1-docker.pkg.dev/$PROJECT/gpaapi/gpaapi-legacy:2.7.0 server
docker push us-central1-docker.pkg.dev/$PROJECT/gpaapi/gpaapi-legacy:2.7.0

# deploy
gcloud run deploy gpaapi-spring \
  --image us-central1-docker.pkg.dev/$PROJECT/gpaapi/gpaapi-spring:2.7.0 \
  --region us-central1 --allow-unauthenticated --port 8080 \
  --service-account gpaapi-runtime@$PROJECT.iam.gserviceaccount.com
```

---

## 7. Checklist para el admin

- [ ] Habilitar las APIs de la sección 1.
- [ ] Otorgar a tu usuario los roles de la sección 2 (mínimo: 1, 2, 3, 7).
- [ ] Crear `gpaapi-runtime` y `gpaapi-deployer` con sus roles (sección 3).
- [ ] Permitir la impersonación de esos SA por tu usuario.
- [ ] Confirmar si la org policy permite `allUsers` (sección 5).
- [ ] (Si aplica) crear la instancia Cloud SQL y el conector VPC, o darte los roles 10/12/13.
