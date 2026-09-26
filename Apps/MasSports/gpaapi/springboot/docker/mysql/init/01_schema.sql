-- Esquema equivalente al de produccion (extraido de las entidades JPA / evolutions).
-- Se usa para levantar la app contra un MySQL local sin tocar la base real.

CREATE TABLE IF NOT EXISTS services (
    id                     BIGINT AUTO_INCREMENT NOT NULL,
    name                   VARCHAR(16)  NOT NULL,
    identifier             VARCHAR(16)  NOT NULL,
    sms                    VARCHAR(150) NOT NULL,
    short_code             INT,
    product_identifier     VARCHAR(255),
    descripcion_producto   VARCHAR(255),
    CONSTRAINT pk_services PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS clients (
    id            BIGINT AUTO_INCREMENT NOT NULL,
    identifier    VARCHAR(255),
    msisdn        BIGINT       NOT NULL,
    token         VARCHAR(255) NOT NULL,
    confirm       VARCHAR(255),
    service_id    BIGINT       NOT NULL,
    last_update   DATETIME,
    CONSTRAINT pk_clients PRIMARY KEY (id),
    CONSTRAINT fk_clients_service_id FOREIGN KEY (service_id) REFERENCES services (id)
        ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE INDEX ix_clients_service_id ON clients (service_id);

CREATE TABLE IF NOT EXISTS cliente_appland (
    id           BIGINT AUTO_INCREMENT NOT NULL,
    msisdn       VARCHAR(255) NOT NULL,
    identifier   VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    status       BIGINT       NOT NULL,
    CONSTRAINT pk_cliente_appland PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS config (
    id            BIGINT AUTO_INCREMENT NOT NULL,
    config_key    VARCHAR(50)  NOT NULL,
    `value`       VARCHAR(255) NOT NULL,
    description   VARCHAR(255),
    CONSTRAINT pk_config PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS render_login (
    id       BIGINT AUTO_INCREMENT NOT NULL,
    fecha    DATETIME(6),
    msisdn   VARCHAR(255),
    club     VARCHAR(255),
    CONSTRAINT pk_render_login PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS alta (
    id        BIGINT AUTO_INCREMENT NOT NULL,
    modo      VARCHAR(255),
    clickid   VARCHAR(255),
    pid       VARCHAR(255),
    msisdn    VARCHAR(255),
    CONSTRAINT pk_alta PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `log` (
    id           BIGINT AUTO_INCREMENT NOT NULL,
    msisdn       VARCHAR(40)  NOT NULL,
    identifier   VARCHAR(16)  NOT NULL,
    extra        VARCHAR(400) NOT NULL,
    last_update  DATETIME,
    CONSTRAINT pk_log PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS maxgame_activity (
    id        BIGINT AUTO_INCREMENT NOT NULL,
    click_id  VARCHAR(255),
    `date`    VARCHAR(255),
    ip        VARCHAR(255),
    sent      TINYINT(1),
    origin    VARCHAR(255),
    msisdn    VARCHAR(255),
    CONSTRAINT pk_maxgame_activity PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS blive_activity (
    id        BIGINT AUTO_INCREMENT NOT NULL,
    click_id  VARCHAR(255),
    `date`    VARCHAR(255),
    msisdn    VARCHAR(255),
    CONSTRAINT pk_blive_activity PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ciudad_juego_activity (
    id        BIGINT AUTO_INCREMENT NOT NULL,
    click_id  VARCHAR(255),
    `date`    VARCHAR(255),
    ip        VARCHAR(255),
    used      TINYINT(1),
    origin    VARCHAR(255),
    msisdn    VARCHAR(255),
    CONSTRAINT pk_ciudad_juego_activity PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS learn_live_activity (
    id        BIGINT AUTO_INCREMENT NOT NULL,
    click_id  VARCHAR(255),
    `date`    VARCHAR(255),
    msisdn    VARCHAR(255),
    origin    VARCHAR(255),
    CONSTRAINT pk_learn_live_activity PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- OJO: Ebean nombra esta tabla sin guion bajo antes de "activity".
CREATE TABLE IF NOT EXISTS learn_live_rdactivity (
    id        BIGINT AUTO_INCREMENT NOT NULL,
    click_id  VARCHAR(255),
    `date`    VARCHAR(255),
    CONSTRAINT pk_learn_live_rdactivity PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS paxxion_activity (
    id        BIGINT AUTO_INCREMENT NOT NULL,
    click_id  VARCHAR(255),
    `date`    VARCHAR(255),
    msisdn    VARCHAR(255),
    origin    VARCHAR(255),
    CONSTRAINT pk_paxxion_activity PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
