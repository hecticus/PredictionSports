# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table alta (
  id                            bigint auto_increment not null,
  clickid                       varchar(255),
  pid                           varchar(255),
  msisdn                        varchar(255),
  constraint pk_alta primary key (id)
);

create table clients (
  id                            bigint auto_increment not null,
  identifier                    varchar(255),
  msisdn                        bigint not null,
  token                         varchar(255) not null,
  confirm                       varchar(255),
  service_id                    bigint not null,
  last_update                   datetime,
  constraint pk_clients primary key (id)
);

create table config (
  id                            bigint auto_increment not null,
  config_key                    varchar(50) not null,
  value                         varchar(255) not null,
  description                   varchar(255),
  constraint pk_config primary key (id)
);

create table render_login (
  id                            bigint auto_increment not null,
  fecha                         datetime(6),
  msisdn                        varchar(255),
  club                          varchar(255),
  constraint pk_render_login primary key (id)
);

create table services (
  id                            bigint auto_increment not null,
  name                          varchar(16) not null,
  identifier                    varchar(16) not null,
  sms                           varchar(150) not null,
  short_code                    integer,
  product_identifier            varchar(255),
  descripcion_producto          varchar(255),
  constraint pk_services primary key (id)
);

create table log (
  id                            bigint auto_increment not null,
  msisdn                        varchar(16) not null,
  identifier                    varchar(16) not null,
  extra                         varchar(16) not null,
  last_update                   datetime,
  constraint pk_log primary key (id)
);

alter table clients add constraint fk_clients_service_id foreign key (service_id) references services (id) on delete restrict on update restrict;
create index ix_clients_service_id on clients (service_id);


# --- !Downs

alter table clients drop foreign key fk_clients_service_id;
drop index ix_clients_service_id on clients;

drop table if exists alta;

drop table if exists clients;

drop table if exists config;

drop table if exists render_login;

drop table if exists services;

drop table if exists log;

