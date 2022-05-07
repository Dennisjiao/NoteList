/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : coursework2

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 15/10/2021 18:51:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus
-- ----------------------------
DROP TABLE IF EXISTS `bus`;
CREATE TABLE `bus`  (
  `type` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `length` int(11) NULL DEFAULT NULL,
  `description` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus
-- ----------------------------
INSERT INTO `bus` VALUES ('Dart, medium single-deck bus', 9, 'Used by Moor-Dale Coaches');
INSERT INTO `bus` VALUES ('Delta, single-deck bus', 12, 'Used by Northumbria and United');
INSERT INTO `bus` VALUES ('Excel, single-deck bus', 11, 'Used by Northern');
INSERT INTO `bus` VALUES ('Metro, short single-deck bus', 6, 'Used by Northumbria');
INSERT INTO `bus` VALUES ('Olympian, double-deck bus', 10, 'Used by Coastline, Northern,Northumbria and United');
INSERT INTO `bus` VALUES ('Prestige, single-deck bus', 11, 'Used by Northern and United');
INSERT INTO `bus` VALUES ('Renown, single-deck bus', 11, 'Used by Northern and Northumbria');
INSERT INTO `bus` VALUES ('Spectra, double-deck bus', 10, 'Used by Northern,');
INSERT INTO `bus` VALUES ('Super-Dart, single-deck bus', 11, 'Used by Northern');
INSERT INTO `bus` VALUES ('Tiger, coach', 12, 'Used by Northumbria');

-- ----------------------------
-- Table structure for busoperator
-- ----------------------------
DROP TABLE IF EXISTS `busoperator`;
CREATE TABLE `busoperator`  (
  `name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `address` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busoperator
-- ----------------------------
INSERT INTO `busoperator` VALUES ('Coastline', 'Coastline House, North Shields,NE29 2AB', 'info@coastlinebuses.co.uk', '0191 222 1451');
INSERT INTO `busoperator` VALUES ('Moor-Dale Coaches', 'Dudley Road, Newcatle, NE23 2MD', 'moor-dale@gmail.com', '0191 255 3272');
INSERT INTO `busoperator` VALUES ('Northern Transport Co', 'The Depot, Chester-le-Street, Co Durham, DH3 1ZZ', 'passengerservices@northerntransport.com', '0191 300 3000');
INSERT INTO `busoperator` VALUES ('Northumbria Motor Services', 'Jesmond Garage, Newcastle, NE2 2EN', 'enquiries@nms.co.uk', '0191 028 4929');
INSERT INTO `busoperator` VALUES ('United Services', 'Grange Road, Darlington, Co Durham, DL11DK', 'customerservices@unitedbus.co.uk', '01325 222 555');

-- ----------------------------
-- Table structure for busroute
-- ----------------------------
DROP TABLE IF EXISTS `busroute`;
CREATE TABLE `busroute`  (
  `number` varchar(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `destination` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `frequency` int(11) NULL DEFAULT NULL,
  `stand` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE,
  INDEX `stand`(`stand`) USING BTREE,
  CONSTRAINT `busroute_ibfk_1` FOREIGN KEY (`stand`) REFERENCES `stand` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busroute
-- ----------------------------
INSERT INTO `busroute` VALUES ('21', 'Durham', 2, 'B');
INSERT INTO `busroute` VALUES ('301', 'Whitley Bay', 2, 'C');
INSERT INTO `busroute` VALUES ('308', 'Blyth', 2, 'C');
INSERT INTO `busroute` VALUES ('355', 'Whitley Bay', 2, 'D');
INSERT INTO `busroute` VALUES ('366', 'Cramlington', 1, 'C');
INSERT INTO `busroute` VALUES ('505', 'Berwick', 1, 'D');
INSERT INTO `busroute` VALUES ('518', 'Alnwick', 1, 'D');
INSERT INTO `busroute` VALUES ('602', 'Hexham', 2, 'A');
INSERT INTO `busroute` VALUES ('722', 'Darlington', 2, 'B');
INSERT INTO `busroute` VALUES ('X1', 'Middlesbrough', 1, 'B');

-- ----------------------------
-- Table structure for operatesroute
-- ----------------------------
DROP TABLE IF EXISTS `operatesroute`;
CREATE TABLE `operatesroute`  (
  `routeNumber` varchar(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `operatorName` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `proportion` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`routeNumber`, `operatorName`) USING BTREE,
  INDEX `operatorName`(`operatorName`) USING BTREE,
  CONSTRAINT `operatesroute_ibfk_1` FOREIGN KEY (`routeNumber`) REFERENCES `busroute` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `operatesroute_ibfk_2` FOREIGN KEY (`operatorName`) REFERENCES `busoperator` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operatesroute
-- ----------------------------
INSERT INTO `operatesroute` VALUES ('21', 'Northern Transport Co', 100);
INSERT INTO `operatesroute` VALUES ('301', 'Coastline', 100);
INSERT INTO `operatesroute` VALUES ('308', 'Coastline', 50);
INSERT INTO `operatesroute` VALUES ('308', 'Northumbria Motor Services', 100);
INSERT INTO `operatesroute` VALUES ('355', 'Northumbria Motor Services', 100);
INSERT INTO `operatesroute` VALUES ('366', 'Moor-Dale Coaches', 100);
INSERT INTO `operatesroute` VALUES ('505', 'Northumbria Motor Services', 100);
INSERT INTO `operatesroute` VALUES ('518', 'Northumbria Motor Services', 100);
INSERT INTO `operatesroute` VALUES ('602', 'Northumbria Motor Services', 100);
INSERT INTO `operatesroute` VALUES ('722', 'Northern Transport Co', 50);
INSERT INTO `operatesroute` VALUES ('722', 'United Services', 50);
INSERT INTO `operatesroute` VALUES ('X1', 'United Services', 100);

-- ----------------------------
-- Table structure for stand
-- ----------------------------
DROP TABLE IF EXISTS `stand`;
CREATE TABLE `stand`  (
  `ID` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `maxLength` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stand
-- ----------------------------
INSERT INTO `stand` VALUES ('A', 10);
INSERT INTO `stand` VALUES ('B', 12);
INSERT INTO `stand` VALUES ('C', 12);
INSERT INTO `stand` VALUES ('D', 12);
INSERT INTO `stand` VALUES ('E', 12);

-- ----------------------------
-- Table structure for uses
-- ----------------------------
DROP TABLE IF EXISTS `uses`;
CREATE TABLE `uses`  (
  `operatorName` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `busType` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`operatorName`, `busType`) USING BTREE,
  INDEX `busType`(`busType`) USING BTREE,
  CONSTRAINT `uses_ibfk_1` FOREIGN KEY (`operatorName`) REFERENCES `busoperator` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `uses_ibfk_2` FOREIGN KEY (`busType`) REFERENCES `bus` (`type`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uses
-- ----------------------------
INSERT INTO `uses` VALUES ('Moor-Dale Coaches', 'Dart, medium single-deck bus');
INSERT INTO `uses` VALUES ('Northumbria Motor Services', 'Delta, single-deck bus');
INSERT INTO `uses` VALUES ('United Services', 'Delta, single-deck bus');
INSERT INTO `uses` VALUES ('Northern Transport Co', 'Excel, single-deck bus');
INSERT INTO `uses` VALUES ('Northumbria Motor Services', 'Metro, short single-deck bus');
INSERT INTO `uses` VALUES ('Coastline', 'Olympian, double-deck bus');
INSERT INTO `uses` VALUES ('Northern Transport Co', 'Olympian, double-deck bus');
INSERT INTO `uses` VALUES ('Northumbria Motor Services', 'Olympian, double-deck bus');
INSERT INTO `uses` VALUES ('United Services', 'Olympian, double-deck bus');
INSERT INTO `uses` VALUES ('Northern Transport Co', 'Prestige, single-deck bus');
INSERT INTO `uses` VALUES ('United Services', 'Prestige, single-deck bus');
INSERT INTO `uses` VALUES ('Northern Transport Co', 'Renown, single-deck bus');
INSERT INTO `uses` VALUES ('Northumbria Motor Services', 'Renown, single-deck bus');
INSERT INTO `uses` VALUES ('Northern Transport Co', 'Spectra, double-deck bus');
INSERT INTO `uses` VALUES ('Northern Transport Co', 'Super-Dart, single-deck bus');
INSERT INTO `uses` VALUES ('Northumbria Motor Services', 'Tiger, coach');

SET FOREIGN_KEY_CHECKS = 1;
