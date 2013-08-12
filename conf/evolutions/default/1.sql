# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table change (
  id                        bigint not null,
  initiated                 timestamp,
  summary                   varchar(255),
  description               varchar(255),
  initiator_userid          varchar(255),
  builder_userid            varchar(255),
  constraint pk_change primary key (id))
;

create table outage (
  id                        bigint not null,
  change_id                 bigint not null,
  length                    integer,
  description               varchar(255),
  constraint pk_outage primary key (id))
;

create table system (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_system primary key (id))
;

create table user (
  userid                    varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (userid))
;

create sequence change_seq;

create sequence outage_seq;

create sequence system_seq;

create sequence user_seq;

alter table change add constraint fk_change_initiator_1 foreign key (initiator_userid) references user (userid) on delete restrict on update restrict;
create index ix_change_initiator_1 on change (initiator_userid);
alter table change add constraint fk_change_builder_2 foreign key (builder_userid) references user (userid) on delete restrict on update restrict;
create index ix_change_builder_2 on change (builder_userid);
alter table outage add constraint fk_outage_change_3 foreign key (change_id) references change (id) on delete restrict on update restrict;
create index ix_outage_change_3 on outage (change_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists change;

drop table if exists outage;

drop table if exists system;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists change_seq;

drop sequence if exists outage_seq;

drop sequence if exists system_seq;

drop sequence if exists user_seq;

