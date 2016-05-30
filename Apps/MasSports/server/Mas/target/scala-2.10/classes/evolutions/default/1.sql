# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table actions (
  id_action                 integer auto_increment not null,
  name                      varchar(255),
  msg                       varchar(255),
  ext_id                    integer,
  constraint pk_actions primary key (id_action))
;

create table clients (
  id_client                 integer auto_increment not null,
  user_id                   varchar(255),
  status                    integer,
  login                     varchar(255),
  password                  varchar(255),
  last_check_date           varchar(255),
  id_country                integer,
  constraint pk_clients primary key (id_client))
;

create table client_bets (
  id_client_bets            bigint auto_increment not null,
  id_client                 integer,
  id_tournament             integer,
  id_game_match             integer,
  client_bet                integer,
  status                    integer,
  constraint pk_client_bets primary key (id_client_bets))
;

create table client_has_devices (
  id_client_has_devices     integer auto_increment not null,
  id_client                 integer,
  id_device                 integer,
  registration_id           varchar(255),
  constraint pk_client_has_devices primary key (id_client_has_devices))
;

create table client_has_push_alerts (
  id_client_has_push_alert  integer auto_increment not null,
  name                      varchar(255),
  id_client                 integer,
  id_push_alert             integer,
  constraint pk_client_has_push_alerts primary key (id_client_has_push_alert))
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

create table instances (
  id_instance               integer auto_increment not null,
  ip                        varchar(255),
  name                      varchar(255),
  running                   integer,
  test                      integer,
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
  constraint pk_jobs primary key (id))
;

create table languages (
  id_language               integer auto_increment not null,
  name                      varchar(255),
  short_name                varchar(255),
  active                    integer,
  constraint pk_languages primary key (id_language))
;

create table leaderboard (
  id_leaderboard            bigint auto_increment not null,
  id_client                 integer,
  id_tournament             integer,
  score                     integer,
  constraint pk_leaderboard primary key (id_leaderboard))
;

create table leaderboard_global (
  id_leaderboard_global     bigint auto_increment not null,
  id_client                 integer,
  score                     integer,
  constraint pk_leaderboard_global primary key (id_leaderboard_global))
;

create table linked_account (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  provider_user_id          varchar(255),
  provider_key              varchar(255),
  constraint pk_linked_account primary key (id))
;

create table news (
  id                        bigint auto_increment not null,
  head_line                 varchar(255),
  summary                   varchar(255),
  xml                       varchar(255),
  date                      varchar(255),
  sort                      integer,
  constraint pk_news primary key (id))
;

create table push_alerts (
  id_push_alert             integer auto_increment not null,
  name                      varchar(255),
  id_ext                    integer,
  pushable                  tinyint(1) default 0,
  constraint pk_push_alerts primary key (id_push_alert))
;

create table resource (
  id                        bigint auto_increment not null,
  url                       varchar(255),
  tags                      varchar(255),
  meta_data                 varchar(255),
  date                      varchar(255),
  constraint pk_resource primary key (id))
;

create table security_role (
  id                        bigint auto_increment not null,
  role_name                 varchar(255),
  constraint pk_security_role primary key (id))
;

create table token_action (
  id                        bigint auto_increment not null,
  token                     varchar(255),
  target_user_id            bigint,
  type                      varchar(2),
  created                   datetime,
  expires                   datetime,
  constraint ck_token_action_type check (type in ('EV','PR')),
  constraint uq_token_action_token unique (token),
  constraint pk_token_action primary key (id))
;

create table users (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  name                      varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  last_login                datetime,
  active                    tinyint(1) default 0,
  email_validated           tinyint(1) default 0,
  constraint pk_users primary key (id))
;

create table user_permission (
  id                        bigint auto_increment not null,
  value                     varchar(255),
  constraint pk_user_permission primary key (id))
;


create table news_resource (
  news_id                        bigint not null,
  resource_id                    bigint not null,
  constraint pk_news_resource primary key (news_id, resource_id))
;

create table users_security_role (
  users_id                       bigint not null,
  security_role_id               bigint not null,
  constraint pk_users_security_role primary key (users_id, security_role_id))
;

create table users_user_permission (
  users_id                       bigint not null,
  user_permission_id             bigint not null,
  constraint pk_users_user_permission primary key (users_id, user_permission_id))
;
alter table clients add constraint fk_clients_country_1 foreign key (id_country) references countries (id_country) on delete restrict on update restrict;
create index ix_clients_country_1 on clients (id_country);
alter table client_bets add constraint fk_client_bets_client_2 foreign key (id_client) references clients (id_client) on delete restrict on update restrict;
create index ix_client_bets_client_2 on client_bets (id_client);
alter table client_has_devices add constraint fk_client_has_devices_client_3 foreign key (id_client) references clients (id_client) on delete restrict on update restrict;
create index ix_client_has_devices_client_3 on client_has_devices (id_client);
alter table client_has_devices add constraint fk_client_has_devices_device_4 foreign key (id_device) references devices (id_device) on delete restrict on update restrict;
create index ix_client_has_devices_device_4 on client_has_devices (id_device);
alter table client_has_push_alerts add constraint fk_client_has_push_alerts_client_5 foreign key (id_client) references clients (id_client) on delete restrict on update restrict;
create index ix_client_has_push_alerts_client_5 on client_has_push_alerts (id_client);
alter table client_has_push_alerts add constraint fk_client_has_push_alerts_pushAlert_6 foreign key (id_push_alert) references push_alerts (id_push_alert) on delete restrict on update restrict;
create index ix_client_has_push_alerts_pushAlert_6 on client_has_push_alerts (id_push_alert);
alter table countries add constraint fk_countries_language_7 foreign key (id_language) references languages (id_language) on delete restrict on update restrict;
create index ix_countries_language_7 on countries (id_language);
alter table leaderboard add constraint fk_leaderboard_client_8 foreign key (id_client) references clients (id_client) on delete restrict on update restrict;
create index ix_leaderboard_client_8 on leaderboard (id_client);
alter table leaderboard_global add constraint fk_leaderboard_global_client_9 foreign key (id_client) references clients (id_client) on delete restrict on update restrict;
create index ix_leaderboard_global_client_9 on leaderboard_global (id_client);
alter table linked_account add constraint fk_linked_account_user_10 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_linked_account_user_10 on linked_account (user_id);
alter table token_action add constraint fk_token_action_targetUser_11 foreign key (target_user_id) references users (id) on delete restrict on update restrict;
create index ix_token_action_targetUser_11 on token_action (target_user_id);



alter table news_resource add constraint fk_news_resource_news_01 foreign key (news_id) references news (id) on delete restrict on update restrict;

alter table news_resource add constraint fk_news_resource_resource_02 foreign key (resource_id) references resource (id) on delete restrict on update restrict;

alter table users_security_role add constraint fk_users_security_role_users_01 foreign key (users_id) references users (id) on delete restrict on update restrict;

alter table users_security_role add constraint fk_users_security_role_security_role_02 foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;

alter table users_user_permission add constraint fk_users_user_permission_users_01 foreign key (users_id) references users (id) on delete restrict on update restrict;

alter table users_user_permission add constraint fk_users_user_permission_user_permission_02 foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table actions;

drop table clients;

drop table client_bets;

drop table client_has_devices;

drop table client_has_push_alerts;

drop table configs;

drop table countries;

drop table devices;

drop table instances;

drop table jobs;

drop table languages;

drop table leaderboard;

drop table leaderboard_global;

drop table linked_account;

drop table news;

drop table news_resource;

drop table push_alerts;

drop table resource;

drop table security_role;

drop table token_action;

drop table users;

drop table users_security_role;

drop table users_user_permission;

drop table user_permission;

SET FOREIGN_KEY_CHECKS=1;

