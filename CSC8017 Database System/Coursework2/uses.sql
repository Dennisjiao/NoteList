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

 Date: 15/10/2021 18:49:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
