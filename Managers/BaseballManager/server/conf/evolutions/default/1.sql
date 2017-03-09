# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table action (
  id_action                     bigint auto_increment not null,
  inning_id_inning              bigint not null,
  event_id_event                bigint not null,
  guid                          varchar(128) not null,
  mode                          integer,
  event_num                     bigint,
  bottom_top                    tinyint(1) default 0,
  descripcion                   varchar(1024) not null,
  constraint pk_action primary key (id_action)
);

create table configs (
  id_config                     bigint auto_increment not null,
  config_key                    varchar(255),
  value                         varchar(255),
  description                   varchar(255),
  constraint pk_configs primary key (id_config)
);

create table event (
  id_event                      bigint auto_increment not null,
  name                          varchar(128) not null,
  constraint pk_event primary key (id_event)
);

create table game (
  id_game                       bigint auto_increment not null,
  identifier                    varchar(120) not null,
  home_team_id_team             bigint not null,
  away_team_id_team             bigint not null,
  league_id_league              bigint not null,
  venue_id_venue                bigint not null,
  game_date                     datetime,
  status_id_status              bigint not null,
  hr_home                       integer,
  hr_away                       integer,
  e_home                        integer,
  e_away                        integer,
  so_home                       integer,
  so_away                       integer,
  r_home                        integer,
  r_away                        integer,
  sb_home                       integer,
  sb_away                       integer,
  h_home                        integer,
  h_away                        integer,
  constraint pk_game primary key (id_game)
);

create table inning (
  id_inning                     bigint auto_increment not null,
  game_id_game                  bigint not null,
  inning_number                 integer not null,
  home                          integer not null,
  away                          integer not null,
  constraint pk_inning primary key (id_inning)
);

create table league (
  id_league                     bigint auto_increment not null,
  name                          varchar(50) not null,
  status                        integer,
  `show`                        integer,
  constraint pk_league primary key (id_league)
);

create table status (
  id_status                     bigint auto_increment not null,
  name                          varchar(50) not null,
  constraint pk_status primary key (id_status)
);

create table team (
  id_team                       bigint auto_increment not null,
  name                          varchar(50) not null,
  short_code                    varchar(3) not null,
  city                          varchar(255) not null,
  constraint pk_team primary key (id_team)
);

create table venue (
  id_venue                      bigint auto_increment not null,
  name                          varchar(50) not null,
  constraint pk_venue primary key (id_venue)
);

alter table action add constraint fk_action_inning_id_inning foreign key (inning_id_inning) references inning (id_inning) on delete restrict on update restrict;
create index ix_action_inning_id_inning on action (inning_id_inning);

alter table action add constraint fk_action_event_id_event foreign key (event_id_event) references event (id_event) on delete restrict on update restrict;
create index ix_action_event_id_event on action (event_id_event);

alter table game add constraint fk_game_home_team_id_team foreign key (home_team_id_team) references team (id_team) on delete restrict on update restrict;
create index ix_game_home_team_id_team on game (home_team_id_team);

alter table game add constraint fk_game_away_team_id_team foreign key (away_team_id_team) references team (id_team) on delete restrict on update restrict;
create index ix_game_away_team_id_team on game (away_team_id_team);

alter table game add constraint fk_game_league_id_league foreign key (league_id_league) references league (id_league) on delete restrict on update restrict;
create index ix_game_league_id_league on game (league_id_league);

alter table game add constraint fk_game_venue_id_venue foreign key (venue_id_venue) references venue (id_venue) on delete restrict on update restrict;
create index ix_game_venue_id_venue on game (venue_id_venue);

alter table game add constraint fk_game_status_id_status foreign key (status_id_status) references status (id_status) on delete restrict on update restrict;
create index ix_game_status_id_status on game (status_id_status);

alter table inning add constraint fk_inning_game_id_game foreign key (game_id_game) references game (id_game) on delete restrict on update restrict;
create index ix_inning_game_id_game on inning (game_id_game);


# --- !Downs

alter table action drop foreign key fk_action_inning_id_inning;
drop index ix_action_inning_id_inning on action;

alter table action drop foreign key fk_action_event_id_event;
drop index ix_action_event_id_event on action;

alter table game drop foreign key fk_game_home_team_id_team;
drop index ix_game_home_team_id_team on game;

alter table game drop foreign key fk_game_away_team_id_team;
drop index ix_game_away_team_id_team on game;

alter table game drop foreign key fk_game_league_id_league;
drop index ix_game_league_id_league on game;

alter table game drop foreign key fk_game_venue_id_venue;
drop index ix_game_venue_id_venue on game;

alter table game drop foreign key fk_game_status_id_status;
drop index ix_game_status_id_status on game;

alter table inning drop foreign key fk_inning_game_id_game;
drop index ix_inning_game_id_game on inning;

drop table if exists action;

drop table if exists configs;

drop table if exists event;

drop table if exists game;

drop table if exists inning;

drop table if exists league;

drop table if exists status;

drop table if exists team;

drop table if exists venue;

