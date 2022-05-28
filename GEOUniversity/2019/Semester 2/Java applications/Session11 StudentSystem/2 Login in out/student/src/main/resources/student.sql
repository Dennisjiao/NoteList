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

alter table user comment '�û������ֶη�Ϊ��ʦ��ѧ�����ֽ�ɫ��1����ѧ����2�����ʦ';

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


delimiter //
create procedure Proc_course_choosing (IN stuid char(20))
begin
select stuinfo.stuid,stuname,course.cid,cname,cdate from stuinfo,course_choosing,course 
where stuinfo.stuid=stuid and stuinfo.stuid=course_choosing.stuid 
and course.cid=course_choosing.cid;
end//;


delimiter //
create procedure Proc_score_query (IN stuid char(20))
begin
select id,stuinfo.stuid,stuname,course.cid,cname,score,sdate from stuinfo,score,course 
where stuinfo.stuid=stuid and stuinfo.stuid=score.stuid and course.cid=score.cid;
end//;
