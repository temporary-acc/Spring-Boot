CREATE DATABASE `DB` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `ecomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `modify_date` datetime(6) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `economy_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn17geyjk9o4kwdwamtl201hov` (`author_id`),
  KEY `FK96xirg6x9565ccyfom29ymwy8` (`economy_id`),
  CONSTRAINT `FK96xirg6x9565ccyfom29ymwy8` FOREIGN KEY (`economy_id`) REFERENCES `economy` (`id`),
  CONSTRAINT `FKn17geyjk9o4kwdwamtl201hov` FOREIGN KEY (`author_id`) REFERENCES `web_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `economy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `modify_date` datetime(6) DEFAULT NULL,
  `subject` varchar(128) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlobpvbputdyaf8njfvh8epagu` (`author_id`),
  CONSTRAINT `FKlobpvbputdyaf8njfvh8epagu` FOREIGN KEY (`author_id`) REFERENCES `web_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `web_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kuqpi3q2x3r3e9gukuvnm6fho` (`email`),
  UNIQUE KEY `UK_hpgrddobol4j88ju681glfcg9` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;






--insert
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(1, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(2, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(3, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(4, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(5, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(6, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(7, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(8, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(9, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(10, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(11, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(12, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(13, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(14, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(15, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);
INSERT INTO DB.economy
(id, content, `date`, modify_date, subject, author_id)
VALUES(16, '<p>테스트 입니다.</p>', '2022-12-10 23:00:02.581', NULL, '테스트 입니다.', 1);



INSERT INTO DB.ecomment
(id, content, `date`, modify_date, author_id, economy_id)
VALUES(1, '<p>테스트 입니다.</p>', '2022-12-10 23:00:07.415', NULL, 1, 1);
INSERT INTO DB.ecomment
(id, content, `date`, modify_date, author_id, economy_id)
VALUES(2, '<p>테스트 입니다.</p>', '2022-12-10 23:00:09.364', NULL, 1, 1);
INSERT INTO DB.ecomment
(id, content, `date`, modify_date, author_id, economy_id)
VALUES(3, '<p>테스트 입니다.</p>', '2022-12-10 23:00:12.381', NULL, 1, 1);
