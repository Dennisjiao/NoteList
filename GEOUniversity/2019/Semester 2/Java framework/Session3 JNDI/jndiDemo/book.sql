/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : testweb

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 25/02/2020 16:15:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `authors` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publisher` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publish_date` date NULL DEFAULT NULL,
  `price` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (2, 'Spring Cloud微服务实战', '翟永超', '电子工业出版社', '2017-05-01', 89);
INSERT INTO `book` VALUES (3, 'Python语言程序设计基础', '嵩天 礼欣', '高等教育出版社', '2016-09-08', 39);
INSERT INTO `book` VALUES (4, '算法设计技巧与分析', 'M. H. Alsuwaiyel', '电子工业出版社', '2016-12-22', 55);

SET FOREIGN_KEY_CHECKS = 1;
