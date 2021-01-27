CREATE TABLE `member` (
                          `u_id` int NOT NULL AUTO_INCREMENT,
                          `u_email` varchar(50) NOT NULL,
                          `u_pw` varchar(32) DEFAULT NULL,
                          `u_phone_number` varchar(20) NOT NULL,
                          `u_nickname` varchar(20) NOT NULL,
                          `u_expire_member` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
                          `u_is_admin` tinyint(1) NOT NULL DEFAULT '0',
                          `u_profile_pic` varchar(150) DEFAULT '',
                          `u_is_blocked` tinyint(1) NOT NULL DEFAULT '0',
                          PRIMARY KEY (`u_id`),
                          UNIQUE KEY `u_email` (`u_email`),
                          UNIQUE KEY `u_phone_number` (`u_phone_number`),
                          UNIQUE KEY `u_nickname` (`u_nickname`)
);

CREATE TABLE `genre` (
  `g_id` int NOT NULL AUTO_INCREMENT,
  `g_name` varchar(20) NOT NULL,
  PRIMARY KEY (`g_id`),
  UNIQUE KEY `g_name` (`g_name`)
);

CREATE TABLE `genre_detail` (
  `gd_id` int NOT NULL AUTO_INCREMENT,
  `gd_name` varchar(20) NOT NULL,
  `g_id` int DEFAULT NULL,
  PRIMARY KEY (`gd_id`),
  UNIQUE KEY `gd_name` (`gd_name`),
  KEY `g_id` (`g_id`),
  CONSTRAINT `genre_detail_ibfk_1` FOREIGN KEY (`g_id`) REFERENCES `genre` (`g_id`)
);


