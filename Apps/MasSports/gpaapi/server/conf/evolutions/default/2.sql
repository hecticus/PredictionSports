# --- !Ups

alter table log modify msisdn varchar(40) not null;
alter table log modify extra varchar(400) not null;

# --- !Downs

alter table log modify msisdn varchar(16) not null;
alter table log modify extra varchar(16) not null;
