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

 Date: 09/10/2021 19:45:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for athlete
-- ----------------------------
DROP TABLE IF EXISTS `athlete`;
CREATE TABLE `athlete`  (
  `VenueID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `EventID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AgentID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TeamID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AthleteID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `AthleteName` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `Biography` char(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`AthleteID`) USING BTREE,
  INDEX `FK_ATHLETE_ADMINISTR_TEAM`(`AgentID`, `TeamID`) USING BTREE,
  INDEX `FK_ATHLETE_MATCH_RACEEVEN`(`EventID`) USING BTREE,
  INDEX `FK_ATHLETE_MATCH2_VENUE`(`VenueID`) USING BTREE,
  CONSTRAINT `FK_ATHLETE_ADMINISTR_TEAM` FOREIGN KEY (`AgentID`, `TeamID`) REFERENCES `team` (`AgentID`, `TeamID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ATHLETE_MATCH2_VENUE` FOREIGN KEY (`VenueID`) REFERENCES `venue` (`VenueID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ATHLETE_MATCH_RACEEVEN` FOREIGN KEY (`EventID`) REFERENCES `raceevent` (`EventID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of athlete
-- ----------------------------
INSERT INTO `athlete` VALUES ('V1', 'E1', '1', 'N1', 'Ac001', 'JIAORUIPENG', 'Mr.nobody');
INSERT INTO `athlete` VALUES ('V1', 'E1', '2', 'N3', 'Ac052', 'Dennisjiao', 'Mr.right');
INSERT INTO `athlete` VALUES ('V2', 'E4', '3', 'N5', 'Am100', 'SuBingtian', 'Greatman');
INSERT INTO `athlete` VALUES ('V2', 'E4', '4', 'N4', 'Am105', 'Jackson', 'UUS');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `VenueID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TicketID` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `CustomerName` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `CustomerAddress` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `CustomerTelephone` int(11) NOT NULL,
  `CustomerOther` char(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`VenueID`, `TicketID`, `CustomerID`) USING BTREE,
  CONSTRAINT `FK_CUSTOMER_SELL_TICKET` FOREIGN KEY (`VenueID`, `TicketID`) REFERENCES `ticket` (`VenueID`, `TicketID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('V1', 'TV001', 546, 'jackson', 'NE1 1TT', 451313513, '');
INSERT INTO `customer` VALUES ('V1', 'TV002', 13, 'DAJIDA', 'NE1 1TT', 435131, NULL);
INSERT INTO `customer` VALUES ('V2', 'TN001', 20, 'dawd', 'NE1 1AD', 4863, NULL);
INSERT INTO `customer` VALUES ('V2', 'TN002', 123, 'ADAWD', 'NE1 1TT', 54684, 'VIP');
INSERT INTO `customer` VALUES ('V2', 'TV005', 7, 'QWDA', 'NE1 11M', 515543, NULL);

-- ----------------------------
-- Table structure for raceevent
-- ----------------------------
DROP TABLE IF EXISTS `raceevent`;
CREATE TABLE `raceevent`  (
  `EventID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `VenueID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `EventName` char(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `EventDescription` char(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`EventID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of raceevent
-- ----------------------------
INSERT INTO `raceevent` VALUES ('E1', 'V1', 'Men\'s Marathon', 'This is MEN race event');
INSERT INTO `raceevent` VALUES ('E2', 'V2', 'Women\'s Marathon', 'This is WOMEN race event');
INSERT INTO `raceevent` VALUES ('E4', 'V4', '100 Meters', 'This is 100 Meter race event');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `VenueID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `StaffID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `StaffName` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `StaffTelephone` int(11) NOT NULL,
  `JobTitle` char(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`StaffID`) USING BTREE,
  INDEX `FK_STAFF_ADMINISTR_VENUE`(`VenueID`) USING BTREE,
  CONSTRAINT `FK_STAFF_ADMINISTR_VENUE` FOREIGN KEY (`VenueID`) REFERENCES `venue` (`VenueID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('V1', 'S12', 'StaffNO1', 1135431, 'Highlevel');
INSERT INTO `staff` VALUES ('V2', 'S123', 'john', 16549, 'Mid');
INSERT INTO `staff` VALUES ('V1', 'S32', 'sno2', 13532, 'low');

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
  `VenueID` char(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TicketID` char(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`VenueID`, `TicketID`) USING BTREE,
  CONSTRAINT `FK_TICKET_SUPPLY_VENUE` FOREIGN KEY (`VenueID`) REFERENCES `venue` (`VenueID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('V1', 'TV001');
INSERT INTO `ticket` VALUES ('V1', 'TV002');
INSERT INTO `ticket` VALUES ('V1', 'TV003');
INSERT INTO `ticket` VALUES ('V1', 'TV004');
INSERT INTO `ticket` VALUES ('V2', 'TN001');
INSERT INTO `ticket` VALUES ('V2', 'TN002');
INSERT INTO `ticket` VALUES ('V2', 'TV005');

-- ----------------------------
-- Table structure for venue
-- ----------------------------
DROP TABLE IF EXISTS `venue`;
CREATE TABLE `venue`  (
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
  PRIMARY KEY (`VenueID`) USING BTREE,
  INDEX `FK_VENUE_MATCH3_RACEEVEN`(`EventID`) USING BTREE,
  CONSTRAINT `FK_VENUE_MATCH3_RACEEVEN` FOREIGN KEY (`EventID`) REFERENCES `raceevent` (`EventID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of venue
-- ----------------------------
INSERT INTO `venue` VALUES ('E1', 'V1', 'NCL', 1512315618, 'address1', 500, '2021-10-06', '2021-10-07 23:26:32', '2021-10-08 23:26:37', 1000, 2, 'strataddress', 'finishaddress');
INSERT INTO `venue` VALUES ('E2', 'V2', 'NCL001', 153153, 'address2', 530, '2021-10-04', '2021-10-04 09:00:00', '2021-10-04 12:00:00', 2000, 1, 'strataddress1', 'finishaddress1');

SET FOREIGN_KEY_CHECKS = 1;
