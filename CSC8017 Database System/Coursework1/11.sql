/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : race

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 07/10/2021 03:04:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for athlete
-- ----------------------------
DROP TABLE IF EXISTS `athlete`;
CREATE TABLE `athlete`  (
  `AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AthleteName` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Biography` char(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`AgentID`, `TeamID`, `AthleteID`) USING BTREE,
  CONSTRAINT `FK_ATHLETE_ADMINISTR_TEAM` FOREIGN KEY (`AgentID`, `TeamID`) REFERENCES `team` (`AgentID`, `TeamID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of athlete
-- ----------------------------
INSERT INTO `athlete` VALUES ('1', 'N1', 'NC001', 'Jiao', 'Nobody');
INSERT INTO `athlete` VALUES ('1', 'N2', 'NC002', 'Rui', 'Mr great');
INSERT INTO `athlete` VALUES ('2', 'N3', 'NC003', 'Peng', 'Mr right');
INSERT INTO `athlete` VALUES ('2', 'N3', 'NC004', 'Dennis', 'UUS');
INSERT INTO `athlete` VALUES ('3', 'N5', 'NC005', 'Jackson', 'ok');
INSERT INTO `athlete` VALUES ('4', 'N4', 'NC006', 'Ying', 'Lover');

-- ----------------------------
-- Table structure for raceevent
-- ----------------------------
DROP TABLE IF EXISTS `raceevent`;
CREATE TABLE `raceevent`  (
  `AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `EventID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `StaffID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ven_AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ven_TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ven_AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ven_EventID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `VenueID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `EventDescription` char(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`AgentID`, `TeamID`, `AthleteID`, `EventID`) USING BTREE,
  INDEX `FK_RACEEVEN_MATCH1_VENUE`(`StaffID`, `Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`, `Ven_AgentID`, `Ven_TeamID`, `Ven_AthleteID`, `Ven_EventID`, `VenueID`) USING BTREE,
  CONSTRAINT `FK_RACEEVEN_MATCH1_VENUE` FOREIGN KEY (`StaffID`, `Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`, `Ven_AgentID`, `Ven_TeamID`, `Ven_AthleteID`, `Ven_EventID`, `VenueID`) REFERENCES `venue` (`StaffID`, `Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`, `AgentID`, `TeamID`, `AthleteID`, `EventID`, `VenueID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_RACEEVEN_MATCH_ATHLETE` FOREIGN KEY (`AgentID`, `TeamID`, `AthleteID`) REFERENCES `athlete` (`AgentID`, `TeamID`, `AthleteID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of raceevent
-- ----------------------------

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `StaffID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `StaffName` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `StaffTelephone` int(11) NOT NULL,
  `JobTitle` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`StaffID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamName` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamTelephone` int(11) NOT NULL,
  `TeamDescription` char(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamPeopleNumber` int(11) NOT NULL,
  `TeamOther` char(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamEmail` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`AgentID`, `TeamID`) USING BTREE,
  CONSTRAINT `FK_TEAM_REPRESENT_TEAMAGEN` FOREIGN KEY (`AgentID`) REFERENCES `teamagent` (`AgentID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1', 'N1', 'RNG', 123123, 'OK', 32113, '123DAD', '123DA');
INSERT INTO `team` VALUES ('1', 'N2', 'OMG', 32151, 'Nice', 123123, 'asd123', 'dawd1');
INSERT INTO `team` VALUES ('2', 'N3', 'IG', 123546, 'GREAT', 131215, 'AWD1', '12EDA');
INSERT INTO `team` VALUES ('3', 'N5', 'FPX', 456123, 'TEST', 15656, 'DAW12', '12DAWD');
INSERT INTO `team` VALUES ('4', 'N4', 'EDG', 654123, 'FINE', 54654, 'DASD123', '124A');

-- ----------------------------
-- Table structure for teamagent
-- ----------------------------
DROP TABLE IF EXISTS `teamagent`;
CREATE TABLE `teamagent`  (
  `AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AgentName` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AgentTelephone` int(11) NOT NULL,
  PRIMARY KEY (`AgentID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teamagent
-- ----------------------------
INSERT INTO `teamagent` VALUES ('1', 'Kiven', 123123);
INSERT INTO `teamagent` VALUES ('2', 'John', 123232);
INSERT INTO `teamagent` VALUES ('3', 'Mike', 323121);
INSERT INTO `teamagent` VALUES ('4', 'Jack', 123123);

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `StaffID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `EventID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `VenueID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TicketID` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`StaffID`, `Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`, `AgentID`, `TeamID`, `AthleteID`, `EventID`, `VenueID`, `TicketID`) USING BTREE,
  CONSTRAINT `FK_TICKET_SUPPLY_VENUE` FOREIGN KEY (`StaffID`, `Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`, `AgentID`, `TeamID`, `AthleteID`, `EventID`, `VenueID`) REFERENCES `venue` (`StaffID`, `Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`, `AgentID`, `TeamID`, `AthleteID`, `EventID`, `VenueID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ticket
-- ----------------------------

-- ----------------------------
-- Table structure for venue
-- ----------------------------
DROP TABLE IF EXISTS `venue`;
CREATE TABLE `venue`  (
  `StaffID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Ath_AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `EventID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `VenueID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `VenueName` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `VenuseTelephone` int(11) NOT NULL,
  `VenuseAddress` char(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TicketSellNumber` int(11) NOT NULL,
  `Date` date NOT NULL,
  `StratTime` datetime(0) NOT NULL,
  `FinishTime` datetime(0) NOT NULL,
  `MaxCustomerNumber` int(11) NOT NULL,
  `StaffNumber` int(11) NOT NULL,
  `StratlineAddress` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `FinishlineAddress` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`StaffID`, `Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`, `AgentID`, `TeamID`, `AthleteID`, `EventID`, `VenueID`) USING BTREE,
  INDEX `FK_VENUE_MATCH2_ATHLETE`(`Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`) USING BTREE,
  INDEX `FK_VENUE_MATCH3_RACEEVEN`(`AgentID`, `TeamID`, `AthleteID`, `EventID`) USING BTREE,
  CONSTRAINT `FK_VENUE_ADMINISTR_STAFF` FOREIGN KEY (`StaffID`) REFERENCES `staff` (`StaffID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_VENUE_MATCH2_ATHLETE` FOREIGN KEY (`Ath_AgentID`, `Ath_TeamID`, `Ath_AthleteID`) REFERENCES `athlete` (`AgentID`, `TeamID`, `AthleteID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_VENUE_MATCH3_RACEEVEN` FOREIGN KEY (`AgentID`, `TeamID`, `AthleteID`, `EventID`) REFERENCES `raceevent` (`AgentID`, `TeamID`, `AthleteID`, `EventID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of venue
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
