/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/10/9 23:46:19                           */
/*==============================================================*/


alter table Athlete 
   drop foreign key FK_ATHLETE_ADMINISTR_TEAM;

alter table Athlete 
   drop foreign key FK_ATHLETE_MATCH_RACEEVEN;

alter table Athlete 
   drop foreign key FK_ATHLETE_MATCH2_VENUE;

alter table Customer 
   drop foreign key FK_CUSTOMER_SELL_TICKET;

alter table RaceEvent 
   drop foreign key FK_RACEEVEN_MATCH1_VENUE;

alter table Staff 
   drop foreign key FK_STAFF_ADMINISTR_VENUE;

alter table Team 
   drop foreign key FK_TEAM_REPRESENT_TEAMAGEN;

alter table Ticket 
   drop foreign key FK_TICKET_SUPPLY_VENUE;

alter table Venue 
   drop foreign key FK_VENUE_MATCH3_RACEEVEN;


alter table Athlete 
   drop foreign key FK_ATHLETE_ADMINISTR_TEAM;

alter table Athlete 
   drop foreign key FK_ATHLETE_MATCH_RACEEVEN;

alter table Athlete 
   drop foreign key FK_ATHLETE_MATCH2_VENUE;

drop table if exists Athlete;


alter table Customer 
   drop foreign key FK_CUSTOMER_SELL_TICKET;

drop table if exists Customer;


alter table RaceEvent 
   drop foreign key FK_RACEEVEN_MATCH1_VENUE;

drop table if exists RaceEvent;


alter table Staff 
   drop foreign key FK_STAFF_ADMINISTR_VENUE;

drop table if exists Staff;


alter table Team 
   drop foreign key FK_TEAM_REPRESENT_TEAMAGEN;

drop table if exists Team;

drop table if exists TeamAgent;


alter table Ticket 
   drop foreign key FK_TICKET_SUPPLY_VENUE;

drop table if exists Ticket;


alter table Venue 
   drop foreign key FK_VENUE_MATCH3_RACEEVEN;

drop table if exists Venue;

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
   CustomerOther        char(100)  comment '',
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
   EventName            char(100) not null  comment '',
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
   VenueTelephone       int not null  comment '',
   VenueAddress         char(40) not null  comment '',
   TicketSellNumber     int not null  comment '',
   Date                 date not null  comment '',
   StratTime            datetime not null  comment '',
   FinishTime           datetime not null  comment '',
   MaxCustomerNumber    int not null  comment '',
   StaffNumber          int not null  comment '',
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

alter table RaceEvent add constraint FK_RACEEVEN_MATCH1_VENUE foreign key (VenueID)
      references Venue (VenueID) on delete restrict on update restrict;

alter table Staff add constraint FK_STAFF_ADMINISTR_VENUE foreign key (VenueID)
      references Venue (VenueID) on delete restrict on update restrict;

alter table Team add constraint FK_TEAM_REPRESENT_TEAMAGEN foreign key (AgentID)
      references TeamAgent (AgentID) on delete restrict on update restrict;

alter table Ticket add constraint FK_TICKET_SUPPLY_VENUE foreign key (VenueID)
      references Venue (VenueID) on delete restrict on update restrict;

alter table Venue add constraint FK_VENUE_MATCH3_RACEEVEN foreign key (EventID)
      references RaceEvent (EventID) on delete restrict on update restrict;

