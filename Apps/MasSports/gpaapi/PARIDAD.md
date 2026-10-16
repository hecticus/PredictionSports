# Paridad: app legacy (Play) vs app Spring Boot

Este documento registra las diferencias de comportamiento **conocidas** entre las dos apps de
`Apps/MasSports/gpaapi/` — legacy Play en `server/`, nueva Spring Boot en `springboot/` — y las
reglas para hacer modificaciones futuras sin romper la paridad.

Contexto: desde el **2026-10-16** las ramas `main_v2` y `TimPanama` apuntan al **mismo commit**
(mismo árbol de código). Cada rama alimenta un deploy distinto:

| Rama | Deploy | App que se despliega |
|---|---|---|
| `main_v2` | Cloud Run, trigger con `--branch-pattern='^main_v2$'` (`cloudbuild.yaml`) | Nueva (`springboot/`) |
| `TimPanama` | Entorno legacy (no Cloud Run) | Legacy (`server/`) |

## Reglas para cambios futuros

1. **Mantener las ramas en el mismo commit.** Tras cualquier commit:

   ```bash
   git push origin HEAD:main_v2 HEAD:TimPanama
   git ls-remote origin refs/heads/main_v2 refs/heads/TimPanama   # deben dar el mismo SHA
   ```

   (Alternativa ya usada: PR hacia `main_v2` y luego fast-forward de `TimPanama` al merge commit.)

2. **Los cambios funcionales aplican a las dos apps.** Comparten BD (`extapi`) y los mismos
   endpoints en distintos entornos. Si se toca un flujo en una app, revisar/espejar el equivalente
   en la otra.

3. **La bandera `disable-provider-events` es solo de Spring Boot** (default `false`,
   env `DISABLE_PROVIDER_EVENTS`). No portarla al Play legacy. Cubre: los 5 postbacks de
   `ConversionService` (Mobipium/Traffic/SEXY/Chat/VIA), `AppLandServicio.comunicarStatus`,
   `KrakenServicio.crearAlta`, `ManhattanServicio.crearAlta` y `SilverServicio.crearAlta`.
   Tests: `springboot/src/test/java/com/hecticus/gpaapi/` (`ConversionServiceTest`,
   `AppLandServicioTest`, `KrakenServicioTest`, `ManhattanServicioTest`, `SilverServicioTest`,
   `GpaApiPropertiesTest`).

4. **No agregar el scheduler de AppLand al Spring Boot** (ver siguiente sección).

## Diferencias intencionadas (no son bugs; no "corregirlas" sin decisión del equipo)

### 1. Scheduler diario de AppLand — ausente a propósito en Spring Boot

El legacy (`server/app/scheduler/DisableCheckerTask.java`, activado por `MainJobModule`) corre al
arrancar y luego cada 24 h:

- `applandServicio.makeUnsubscribeCall("HECTI_MOVIS_U_VE")`
- `applandServicio.makeUnsubscribeCall("HECTI_CIUDA_U_VE")`

Eso consulta los clientes deshabilitados en Kraken (`obtenerUsuariosDeshabilitadosPorFecha`) y les
envía `SUBSCRIPTION_END` a AppLand (`comunicarStatus`). **La app Spring Boot no tiene ningún
`@Scheduled` a propósito.** Si en el futuro se necesita, agregarlo como
`@Scheduled(initialDelay = 0, fixedDelay = 86_400_000)`; el envío ya respeta
`disable-provider-events` porque pasa por `AppLandServicio.comunicarStatus`.

### 2. Secretos: configurables (Spring Boot) vs hardcodeados (Play)

En Spring Boot los valores sensibles se leen de variables de entorno / Secret Manager; en el legacy
están escritos en el código. Para paridad funcional en producción, los valores de Secret Manager
deben ser **los mismos que los hardcodeados del legacy** (ver `SECRETS.md`, que lista los 8
secretos):

| Variable | Valor legacy |
|---|---|
| `PAXXION_HASH` | `server/app/utils/Constants.java` (handler `11240`) |
| `MAXGAME_HASH` | `server/app/controllers/CiudadJuegoApplandController.java` y `ClickToSMSController.java` (handler `11191`) |
| `SEXY_POSTBACK_KEY` | `server/app/utils/Constants.java` |
| `CHAT_SECURITY_TOKEN` | `server/app/utils/Constants.java` |
| `APPLAND_SERVICE_SECRET` | firma HMAC de la app legacy |
| `APPLICATION_SECRET` | `server/conf/application.conf` / env del legacy |
| `DB_PASSWORD`, `MAIL_PASSWORD` | entorno |

Handlers (`11240`/`11191`), URLs de providers y credenciales del dashboard
(`DASHBOARD_ADMIN_USER/PASS`, default `admin`/`humbilumby_2026`) coinciden por defecto y no
requieren nada extra.

### 3. Mejoras de robustez del rewrite (mantener)

- Claim de actividad en transacción explícita (`TransactionTemplate` + `SELECT ... FOR UPDATE`).
- `ip_maxgame`: responde 200 vacío si la actividad no existe (el legacy lanzaba NPE → 500).
- `mark_maxgame` sin `System.out.println` de debug.

## Diferencias cosméticas conocidas (sin acción)

- Cuerpo de errores 4xx/5xx: el legacy responde texto plano (`ErrorHandler`); Spring Boot usa su
  manejo por defecto (whitelabel desactivado).
- `MAIL_TO` por defecto no incluye `palenge@gmail.com` (el legacy sí); configurable por env. En
  ambas apps el envío de mails solo se invoca desde código comentado.
- `gpaapi.base-url` existe en Spring Boot pero no se usa.
- Código muerto solo en legacy: `Application.scala`, `MaxgameController.mg()` y `Static.java`
  (`/mg` renderiza `maxgame_index` en **ambas** apps; el `mg()` legacy no está ruteado).
- *(Resuelto 2026-10-16)* Logs `Claimed BliveActivity / PaxxionActivity / LearnLiveActivity /
  Maxgame TRA activity` en `ClickToSMSController`: ahora los 4 se emiten en ambas apps.

## Paridad verificada (checklist rápida)

- **Rutas**: todas las de `server/conf/routes` tienen equivalente Spring (`@GetMapping`/`@PostMapping`).
- **Ids de negocio** (`Constants`): países 14/6/13, businesses 6/5/4, maxgame 6/10, `LANDING6`/`LANDING`.
- **Dashboard**: auth Basic (default `admin`/`humbilumby_2026`), clasificación `convBusiness`
  (MAXGAME/HAITI/BLIVE/PAXXION), `extractProviderId`, template portado 1:1, trazas `REQ`/`CLAIM`.
- **`/clicktosms`**: respuesta 200 vacío; claim `date < now-20s`; orden gclid-first, dedupe,
  `rawQuery` completo; `LANDING6` cuenta GADS sin postback; `LANDING` postbackea TRA.
- **Kraken MO** `GLOBALWEB` en register/confirm; **health** `{"status":"ok","version":"2.7.1"}`;
  **CORS** todos los orígenes; timeouts WS 120 s y SEXY 2 min.
- **Tests del rewrite**: `cd springboot && mvn test` (39 tests).

---

Última revisión: 2026-10-16.
