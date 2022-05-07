/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/10/7 22:58:22                           */
/*==============================================================*/


drop database if exists race;
create database race;
use race;



/*==============================================================*/
/* Table: Athlete                                               */
/*==============================================================*/
create table Athlete
(
   VenueID              char(10) not null  comment '',
   EventID              char(10) not null  comment '',
   AgentID              char(10) not null  comment '',
   TeamID               char(10) not null  comment '',
   AthleteID            char(10) not null  comment '',
   AthleteName          char(50) not null  comment '',
   Biography            char(200) not null  comment '',
   primary key (AthleteID)
);

/*==============================================================*/
/* Table: Customer                                              */
/*==============================================================*/
create table Customer
(
   VenueID              char(10) not null  comment '',
   TicketID             char(20) not null  comment '',
   CustomerID           int not null  comment '',
   CustomerName         char(20) not null  comment '',
   CustomerAddress      char(50) not null  comment '',
   CustomerTelephone    int not null  comment '',
   CustomerOther        char(100) comment '',
   primary key (VenueID, TicketID, CustomerID)
);

/*==============================================================*/
/* Table: RaceEvent                                             */
/*==============================================================*/
create table RaceEvent
(
   EventID              char(10) not null  comment '',
   VenueID              char(10) not null  comment '',
   EventDescription     char(100) not null  comment '',
   primary key (EventID)
);

/*==============================================================*/
/* Table: Staff                                                 */
/*==============================================================*/
create table Staff
(
   VenueID              char(10) not null  comment '',
   StaffID              char(10) not null  comment '',
   StaffName            char(50) not null  comment '',
   StaffTelephone       int not null  comment '',
   JobTitle             char(50) not null  comment '',
   primary key (StaffID)
);

/*==============================================================*/
/* Table: Team                                                  */
/*==============================================================*/
create table Team
(
   AgentID              char(10) not null  comment '',
   TeamID               char(10) not null  comment '',
   TeamName             char(20) not null  comment '',
   TeamTelephone        int not null  comment '',
   TeamDescription      char(100) not null  comment '',
   TeamPeopleNumber     int not null  comment '',
   TeamOther            char(100) not null  comment '',
   TeamEmail            char(50) not null  comment '',
   primary key (AgentID, TeamID)
);

INSERT INTO `team` VALUES ('1', 'N1', 'RNG', 123123, 'OK', 32113, '123DAD', '123DA');
INSERT INTO `team` VALUES ('1', 'N2', 'OMG', 32151, 'Nice', 123123, 'asd123', 'dawd1');
INSERT INTO `team` VALUES ('2', 'N3', 'IG', 123546, 'GREAT', 131215, 'AWD1', '12EDA');
INSERT INTO `team` VALUES ('3', 'N5', 'FPX', 456123, 'TEST', 15656, 'DAW12', '12DAWD');
INSERT INTO `team` VALUES ('4', 'N4', 'EDG', 654123, 'FINE', 54654, 'DASD123', '124A');

/*==============================================================*/
/* Table: TeamAgent                                             */
/*==============================================================*/
create table TeamAgent
(
   AgentID              char(10) not null  comment '',
   AgentName            char(50) not null  comment '',
   AgentTelephone       int not null  comment '',
   primary key (AgentID)
);

INSERT INTO `teamagent` VALUES ('1', 'Kiven', 123123);
INSERT INTO `teamagent` VALUES ('2', 'John', 123232);
INSERT INTO `teamagent` VALUES ('3', 'Mike', 323121);
INSERT INTO `teamagent` VALUES ('4', 'Jack', 123123);

/*==============================================================*/
/* Table: Ticket                                                */
/*==============================================================*/
create table Ticket
(
   VenueID              char(10) not null  comment '',
   TicketID             char(20) not null  comment '',
   primary key (VenueID, TicketID)
);

/*==============================================================*/
/* Table: Venue                                                 */
/*==============================================================*/
create table Venue
(
   EventID              char(10) not null  comment '',
   VenueID              char(10) not null  comment '',
   VenueName            char(50) not null  comment '',
   VenuseTelephone      int not null  comment '',
   VenuseAddress        char(40) not null  comment '',
   TicketSellNumber     int not null  comment '',
   Date                 date not null  comment '',
   StratTime            datetime not null  comment '',
   FinishTime           datetime not null  comment '',
   MaxCustomerNumber    int not null  comment '',
   StaffNumber          int CHEAK (StaffNumber>0 AND StaffNumber<=5) not null   comment '',
   StratlineAddress     char(50) not null  comment '',
   FinishlineAddress    char(50) not null  comment '',
   primary key (VenueID)
);

alter table Athlete add constraint FK_ATHLETE_ADMINISTR_TEAM foreign key (AgentID, TeamID)
      references Team (AgentID, TeamID) on delete restrict on update restrict;

alter table Athlete add constraint FK_ATHLETE_MATCH_RACEEVEN foreign key (EventID)
      references RaceEvent (EventID) on delete restrict on update restrict;

alter table Athlete add constraint FK_ATHLETE_MATCH2_VENUE foreign key (VenueID)
      references Venue (VenueID) on delete restrict on update restrict;

alter table Customer add constraint FK_CUSTOMER_SELL_TICKET foreign key (VenueID, TicketID)
      references Ticket (VenueID, TicketID) on delete restrict on update restrict;

alter table Staff add constraint FK_STAFF_ADMINISTR_VENUE foreign key (VenueID)
      references Venue (VenueID) on delete restrict on update restrict;

alter table Team add constraint FK_TEAM_REPRESENT_TEAMAGEN foreign key (AgentID)
      references TeamAgent (AgentID) on delete restrict on update restrict;

alter table Ticket add constraint FK_TICKET_SUPPLY_VENUE foreign key (VenueID)
      references Venue (VenueID) on delete restrict on update restrict;

alter table Venue add constraint FK_VENUE_MATCH3_RACEEVEN foreign key (EventID)
      references RaceEvent (EventID) on delete restrict on update restrict;


