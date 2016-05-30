# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table configs (
  id_config                 bigint auto_increment not null,
  config_key                varchar(255),
  value                     varchar(255),
  description               varchar(255),
  constraint pk_configs primary key (id_config))
;

create table instances (
  id_instance               integer auto_increment not null,
  ip                        varchar(255),
  name                      varchar(255),
  running                   tinyint(1) default 0,
  test                      tinyint(1) default 0,
  master                    tinyint(1) default 0,
  constraint pk_instances primary key (id_instance))
;

create table jobs (
  id                        bigint auto_increment not null,
  status                    integer,
  class_name                varchar(255),
  name                      varchar(255),
  params                    varchar(255),
  id_app                    integer,
  next_timestamp            bigint,
  time                      varchar(255),
  time_params               varchar(255),
  frequency                 integer,
  daemon                    tinyint(1) default 0,
  running                   tinyint(1) default 0,
  id_instance               integer,
  constraint pk_jobs primary key (id))
;

alter table jobs add constraint fk_jobs_instance_1 foreign key (id_instance) references instances (id_instance) on delete restrict on update restrict;
create index ix_jobs_instance_1 on jobs (id_instance);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table configs;

drop table instances;

drop table jobs;

SET FOREIGN_KEY_CHECKS=1;

