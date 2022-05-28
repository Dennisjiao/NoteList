/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/10 11:23:22                           */
/*==============================================================*/

drop database if exists test;
create database test;
use test;

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   cid                  char(32) not null,
   cname                char(30) not null,
   primary key (cid)
);

/*==============================================================*/
/* Table: course_choosing                                       */
/*==============================================================*/
create table course_choosing
(
   stuid                char(20) not null,
   cid                  char(32) not null,
   cdate                date not null,
   primary key (stuid, cid)
);

/*==============================================================*/
/* Table: score                                                 */
/*==============================================================*/
create table score
(
   id                   char(32) not null,
   cid                  char(32) not null,
   stuid                char(20) not null,
   score                numeric(4,1) not null,
   sdate                date not null,
   primary key (id)
);

/*==============================================================*/
/* Table: stuinfo                                               */
/*==============================================================*/
create table stuinfo
(
   stuid                char(20) not null,
   stuname              char(20) not null,
   gender               char(2),
   birthday             date,
   classname            char(20),
   address              char(50),
   primary key (stuid)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   username             char(40) not null,
   password             char(20) not null,
   usertype             smallint not null,
   stuid                char(20),
   primary key (username)
);

alter table user comment '用户类型字段分为教师和学生两种角色，1代表学生，2代表教师';

alter table course_choosing add constraint FK_R4 foreign key (cid)
      references course (cid) on delete restrict on update restrict;

alter table course_choosing add constraint FK_R5 foreign key (stuid)
      references stuinfo (stuid) on delete restrict on update restrict;

alter table score add constraint FK_R2 foreign key (cid)
      references course (cid) on delete restrict on update restrict;

alter table score add constraint FK_R3 foreign key (stuid)
      references stuinfo (stuid) on delete restrict on update restrict;

alter table user add constraint FK_R1 foreign key (stuid)
      references stuinfo (stuid) on delete restrict on update restrict;

