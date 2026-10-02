-- Tablas de actividad que no están en las evoluciones (se crean a mano en prod)
-- + tablas base de las evoluciones (en dev stage no se aplican evoluciones)
-- Aplicar idempotente: make dev-db

create table if not exists log (
  id                            bigint auto_increment not null,
  msisdn                        varchar(40) not null,
  identifier                    varchar(16) not null,
  extra                         varchar(400) not null,
  last_update                   datetime,
  constraint pk_log primary key (id)
);

create table if not exists alta (
  id                            bigint auto_increment not null,
  modo                          varchar(255),
  clickid                       varchar(255),
  pid                           varchar(255),
  msisdn                        varchar(255),
  constraint pk_alta primary key (id)
);

create table if not exists cliente_appland (
  id                            bigint auto_increment not null,
  msisdn                        varchar(255) not null,
  identifier                    varchar(255) not null,
  password                      varchar(255) not null,
  status                        bigint not null,
  constraint pk_cliente_appland primary key (id)
);

create table if not exists clients (
  id                            bigint auto_increment not null,
  identifier                    varchar(255),
  msisdn                        bigint not null,
  token                         varchar(255) not null,
  confirm                       varchar(255),
  service_id                    bigint not null,
  last_update                   datetime,
  constraint pk_clients primary key (id)
);

create table if not exists config (
  id                            bigint auto_increment not null,
  config_key                    varchar(50) not null,
  value                         varchar(255) not null,
  description                   varchar(255),
  constraint pk_config primary key (id)
);

create table if not exists render_login (
  id                            bigint auto_increment not null,
  fecha                         datetime(6),
  msisdn                        varchar(255),
  club                          varchar(255),
  constraint pk_render_login primary key (id)
);

create table if not exists services (
  id                            bigint auto_increment not null,
  name                          varchar(16) not null,
  identifier                    varchar(16) not null,
  sms                           varchar(150) not null,
  short_code                    integer,
  product_identifier            varchar(255),
  descripcion_producto          varchar(255),
  constraint pk_services primary key (id)
);

create table if not exists blive_activity (
  id                            bigint auto_increment not null,
  click_id                      varchar(255),
  date                          varchar(255),
  msisdn                        varchar(255),
  constraint pk_blive_activity primary key (id)
);

create table if not exists paxxion_activity (
  id                            bigint auto_increment not null,
  click_id                      varchar(255),
  date                          varchar(255),
  msisdn                        varchar(255),
  origin                        varchar(255),
  constraint pk_paxxion_activity primary key (id)
);

create table if not exists learn_live_activity (
  id                            bigint auto_increment not null,
  click_id                      varchar(255),
  date                          varchar(255),
  msisdn                        varchar(255),
  origin                        varchar(255),
  constraint pk_learn_live_activity primary key (id)
);

create table if not exists maxgame_activity (
  id                            bigint auto_increment not null,
  click_id                      varchar(255),
  date                          varchar(255),
  ip                            varchar(255),
  sent                          tinyint(1) default 0 not null,
  origin                        varchar(255),
  msisdn                        varchar(255),
  constraint pk_maxgame_activity primary key (id)
);
