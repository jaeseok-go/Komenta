CREATE DATABASE komenta;
USE komenta;


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
)DEFAULT CHARSET=utf8mb4;





CREATE TABLE `genre` (
  `g_id` int NOT NULL AUTO_INCREMENT,
  `g_name` varchar(20) NOT NULL,
  PRIMARY KEY (`g_id`),
  UNIQUE KEY `g_name` (`g_name`)
)DEFAULT CHARSET=utf8mb4;





CREATE TABLE `genre_detail` (
  `gd_id` int NOT NULL AUTO_INCREMENT,
  `gd_name` varchar(20) NOT NULL,
  `g_id` int DEFAULT NULL,
  PRIMARY KEY (`gd_id`),
  UNIQUE KEY `gd_name` (`gd_name`),
  KEY `g_id` (`g_id`),
  CONSTRAINT `genre_detail_ibfk_1` FOREIGN KEY (`g_id`) REFERENCES `genre` (`g_id`)
  ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;






CREATE TABLE `vod` (
  `v_id` int NOT NULL AUTO_INCREMENT,
  `v_title` varchar(40) NOT NULL,
  `v_summary` varchar(200) DEFAULT NULL,
  `v_director` varchar(20) DEFAULT NULL,
  `v_actors` varchar(50) DEFAULT NULL,
  `v_age_grade` int DEFAULT '0',
  `v_poster` varchar(150) DEFAULT NULL,
  `gd_id` int DEFAULT NULL,
  PRIMARY KEY (`v_id`),
  KEY `gd_id` (`gd_id`),
  CONSTRAINT `vod_ibfk_1` FOREIGN KEY (`gd_id`) REFERENCES `genre_detail` (`gd_id`)
  ON DELETE cascade
)DEFAULT CHARSET=utf8mb4;





CREATE TABLE `vod_episode` (
  `ve_id` int NOT NULL AUTO_INCREMENT,
  `v_id` int NOT NULL,
  `ve_episode_num` int NOT NULL,
  `ve_contents` varchar(200) NOT NULL,
  `ve_upload_time` DATETIME DEFAULT NOW(),
  `admin` VARCHAR(30) DEFAULT "admin",
  PRIMARY KEY (`ve_id`,`v_id`),
  UNIQUE KEY `ve_episode_num` (`ve_episode_num`),
  KEY `v_id` (`v_id`),
  CONSTRAINT `vod_episode_ibfk_1` FOREIGN KEY (`v_id`) REFERENCES `vod` (`v_id`)
  ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;




CREATE TABLE `vod_history` (
  `vh_id` int NOT NULL AUTO_INCREMENT,
  `vh_watching_time` time DEFAULT '00:00:00',
  `vh_last_watched_time` DATETIME DEFAULT '0000-00-00 00:00:00',
  `vh_comment` varchar(100) DEFAULT NULL,
  `vh_score` int DEFAULT NULL,
  `vh_good` tinyint(1) DEFAULT '0',
  `u_id` int DEFAULT NULL,
  `ve_id` int DEFAULT NULL,
  PRIMARY KEY (`vh_id`),
  KEY `u_id` (`u_id`),
  KEY `ve_id` (`ve_id`),
  CONSTRAINT `vod_history_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade,
  CONSTRAINT `vod_history_ibfk_2` FOREIGN KEY (`ve_id`) REFERENCES `vod_episode` (`ve_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;





CREATE TABLE `blocked_vod` (
  `u_id` int NOT NULL,
  `v_id` int NOT NULL,
  PRIMARY KEY (`u_id`,`v_id`),
  KEY `v_id` (`v_id`),
  CONSTRAINT `blocked_vod_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade,
  CONSTRAINT `blocked_vod_ibfk_2` FOREIGN KEY (`v_id`) REFERENCES `vod` (`v_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;




CREATE TABLE `comment_info` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `c_contents` varchar(200) NOT NULL,
  `c_playtime` time DEFAULT '00:00:00',
  `c_upload_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `u_id` int DEFAULT NULL,
  `ve_id` int DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `u_id` (`u_id`),
  KEY `ve_id` (`ve_id`),
  CONSTRAINT `comment_info_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade,
  CONSTRAINT `comment_info_ibfk_2` FOREIGN KEY (`ve_id`) REFERENCES `vod_episode` (`ve_id`) ON DELETE cascade
)DEFAULT CHARSET=utf8mb4;



CREATE TABLE `comment_good` (
  `c_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`c_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `comment_good_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `comment_info` (`c_id`) ON DELETE cascade,
  CONSTRAINT `comment_good_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;





CREATE TABLE `follow` (
  `f_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`f_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`f_id`) REFERENCES `member` (`u_id`) ON DELETE cascade,
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;






CREATE TABLE `play_list` (
  `pl_id` int NOT NULL AUTO_INCREMENT,
  `pl_name` varchar(100) NOT NULL,
  `pl_comment` VARCHAR(300) DEFAULT "",
  `u_id` int DEFAULT NULL,
  PRIMARY KEY (`pl_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `play_list_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade
)DEFAULT CHARSET=utf8mb4;






CREATE TABLE `play_list_contents` (
  `plc_id` int NOT NULL AUTO_INCREMENT,
  `vh_id` int DEFAULT NULL,
  `pl_id` int DEFAULT NULL,
  PRIMARY KEY (`plc_id`),
  KEY `vh_id` (`vh_id`),
  KEY `pl_id` (`pl_id`),
  CONSTRAINT `play_list_contents_ibfk_1` FOREIGN KEY (`vh_id`) REFERENCES `vod_history` (`vh_id`) ON DELETE cascade,
  CONSTRAINT `play_list_contents_ibfk_2` FOREIGN KEY (`pl_id`) REFERENCES `play_list` (`pl_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;






CREATE TABLE `play_list_good` (
  `pl_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`pl_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `play_list_good_ibfk_1` FOREIGN KEY (`pl_id`) REFERENCES `play_list` (`pl_id`) ON DELETE cascade,
  CONSTRAINT `play_list_good_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;





CREATE TABLE `re_comment` (
  `rc_id` int NOT NULL AUTO_INCREMENT,
  `rc_contents` varchar(150) DEFAULT NULL,
  `rc_upload_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `u_id` int DEFAULT NULL,
  `c_id` int DEFAULT NULL,
  PRIMARY KEY (`rc_id`),
  KEY `u_id` (`u_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `re_comment_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade,
  CONSTRAINT `re_comment_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `comment_info` (`c_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;



CREATE TABLE `re_comment_good` (
  `rc_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`rc_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `re_comment_good_ibfk_1` FOREIGN KEY (`rc_id`) REFERENCES `re_comment` (`rc_id`) ON DELETE cascade,
  CONSTRAINT `re_comment_good_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4 ;





CREATE TABLE `report` (
  `r_id` int NOT NULL AUTO_INCREMENT,
  `r_type` varchar(20) DEFAULT NULL,
  `r_contents` varchar(100) DEFAULT NULL,
  `u_id` int DEFAULT NULL,
  `c_id` int DEFAULT NULL,
  `rc_id` int DEFAULT NULL,
  PRIMARY KEY (`r_id`),
  KEY `u_id` (`u_id`),
  KEY `c_id` (`c_id`),
  KEY `rc_id` (`rc_id`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade,
  CONSTRAINT `report_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `comment_info` (`c_id`) ON DELETE cascade,
  CONSTRAINT `report_ibfk_3` FOREIGN KEY (`rc_id`) REFERENCES `re_comment` (`rc_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4 ;





CREATE TABLE `un_follow` (
  `uf_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`uf_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `un_follow_ibfk_1` FOREIGN KEY (`uf_id`) REFERENCES `member` (`u_id`) ON DELETE cascade,
  CONSTRAINT `un_follow_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`) ON DELETE cascade
) DEFAULT CHARSET=utf8mb4;





