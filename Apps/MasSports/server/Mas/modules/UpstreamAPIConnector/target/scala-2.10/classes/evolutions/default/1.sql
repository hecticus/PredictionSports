# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table clients (
  id_client                 integer auto_increment not null,
  user_id                   varchar(255),
  status                    integer,
  login                     varchar(255),
  password                  varchar(255),
  last_check_date           varchar(255),
  nickname                  varchar(255),
  facebook_id               varchar(255),
  session                   varchar(255),
  id_country                integer,
  id_language               integer,
  last_update               timestamp default '2014-10-06 21:17:06' not null,
  constraint pk_clients primary key (id_client))
;

create table client_has_devices (
  id_client_has_devices     integer auto_increment not null,
  id_client                 integer,
  id_device                 integer,
  registration_id           varchar(255),
  constraint pk_client_has_devices primary key (id_client_has_devices))
;

create table configs (
  id_config                 bigint auto_increment not null,
  config_key                varchar(255),
  value                     varchar(255),
  description               varchar(255),
  constraint pk_configs primary key (id_config))
;

create table countries (
  id_country                integer auto_increment not null,
  name                      varchar(255),
  short_name                varchar(255),
  active                    integer,
  id_language               integer,
  constraint pk_countries primary key (id_country))
;

create table devices (
  id_device                 integer auto_increment not null,
  name                      varchar(255),
  constraint pk_devices primary key (id_device))
;

create table languages (
  id_language               integer auto_increment not null,
  name                      varchar(255),
  short_name                varchar(255),
  active                    integer,
  app_localization_file     varchar(255),
  constraint pk_languages primary key (id_language))
;

alter table clients add constraint fk_clients_country_1 foreign key (id_country) references countries (id_country) on delete restrict on update restrict;
create index ix_clients_country_1 on clients (id_country);
alter table clients add constraint fk_clients_language_2 foreign key (id_language) references languages (id_language) on delete restrict on update restrict;
create index ix_clients_language_2 on clients (id_language);
alter table client_has_devices add constraint fk_client_has_devices_client_3 foreign key (id_client) references clients (id_client) on delete restrict on update restrict;
create index ix_client_has_devices_client_3 on client_has_devices (id_client);
alter table client_has_devices add constraint fk_client_has_devices_device_4 foreign key (id_device) references devices (id_device) on delete restrict on update restrict;
create index ix_client_has_devices_device_4 on client_has_devices (id_device);
alter table countries add constraint fk_countries_language_5 foreign key (id_language) references languages (id_language) on delete restrict on update restrict;
create index ix_countries_language_5 on countries (id_language);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table clients;

drop table client_has_devices;

drop table configs;

drop table countries;

drop table devices;

drop table languages;

SET FOREIGN_KEY_CHECKS=1;

