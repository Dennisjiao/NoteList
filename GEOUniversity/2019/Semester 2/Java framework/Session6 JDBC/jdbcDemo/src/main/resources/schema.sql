DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` integer identity primary key,
  `book_name` varchar(80)  NOT NULL,
  `authors` varchar(100)  NULL,
  `publisher` varchar(50) NULL,
  `pub_date` date NULL,
  `price` double NULL  
)
