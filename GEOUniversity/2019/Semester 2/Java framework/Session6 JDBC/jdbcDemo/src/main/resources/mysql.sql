DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(80) NOT NULL,
  `authors` varchar(100) NULL,
  `publisher` varchar(50) NULL,
  `pub_date` date NULL,
  `price` double NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 'Spring Cloud微服务实战', '翟永超', '电子工业出版社', '2017-05-01', 89);
INSERT INTO `book` VALUES (2, 'Python语言程序设计基础', '嵩天 礼欣', '高等教育出版社', '2016-09-08', 39);
INSERT INTO `book` VALUES (3, '算法设计技巧与分析', 'M. H. Alsuwaiyel', '电子工业出版社', '2016-12-22', 55.5);
