-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ssafy_project_1
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blocked_vod`
--

DROP TABLE IF EXISTS `blocked_vod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blocked_vod` (
  `u_id` int NOT NULL,
  `v_id` int NOT NULL,
  PRIMARY KEY (`u_id`,`v_id`),
  KEY `v_id` (`v_id`),
  CONSTRAINT `blocked_vod_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`),
  CONSTRAINT `blocked_vod_ibfk_2` FOREIGN KEY (`v_id`) REFERENCES `vod` (`v_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blocked_vod`
--

LOCK TABLES `blocked_vod` WRITE;
/*!40000 ALTER TABLE `blocked_vod` DISABLE KEYS */;
/*!40000 ALTER TABLE `blocked_vod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_good`
--

DROP TABLE IF EXISTS `comment_good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_good` (
  `c_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`c_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `comment_good_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `comment_info` (`c_id`),
  CONSTRAINT `comment_good_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_good`
--

LOCK TABLES `comment_good` WRITE;
/*!40000 ALTER TABLE `comment_good` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_info`
--

DROP TABLE IF EXISTS `comment_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_info` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `c_contents` varchar(200) NOT NULL,
  `c_playtime` datetime DEFAULT '0000-00-00 00:00:00',
  `c_upload_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `u_id` int DEFAULT NULL,
  `ve_id` int DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `u_id` (`u_id`),
  KEY `ve_id` (`ve_id`),
  CONSTRAINT `comment_info_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`),
  CONSTRAINT `comment_info_ibfk_2` FOREIGN KEY (`ve_id`) REFERENCES `vod_episode` (`ve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_info`
--

LOCK TABLES `comment_info` WRITE;
/*!40000 ALTER TABLE `comment_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `f_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`f_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`f_id`) REFERENCES `member` (`u_id`),
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `g_id` int NOT NULL AUTO_INCREMENT,
  `g_name` varchar(20) NOT NULL,
  PRIMARY KEY (`g_id`),
  UNIQUE KEY `g_name` (`g_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre_detail`
--

DROP TABLE IF EXISTS `genre_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre_detail` (
  `gd_id` int NOT NULL AUTO_INCREMENT,
  `gd_name` varchar(20) NOT NULL,
  `g_id` int DEFAULT NULL,
  PRIMARY KEY (`gd_id`),
  UNIQUE KEY `gd_name` (`gd_name`),
  KEY `g_id` (`g_id`),
  CONSTRAINT `genre_detail_ibfk_1` FOREIGN KEY (`g_id`) REFERENCES `genre` (`g_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre_detail`
--

LOCK TABLES `genre_detail` WRITE;
/*!40000 ALTER TABLE `genre_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `genre_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_list`
--

DROP TABLE IF EXISTS `play_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_list` (
  `pl_id` int NOT NULL AUTO_INCREMENT,
  `pl_name` varchar(100) NOT NULL,
  `u_id` int DEFAULT NULL,
  PRIMARY KEY (`pl_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `play_list_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_list`
--

LOCK TABLES `play_list` WRITE;
/*!40000 ALTER TABLE `play_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `play_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_list_contents`
--

DROP TABLE IF EXISTS `play_list_contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_list_contents` (
  `plc_id` int NOT NULL AUTO_INCREMENT,
  `vh_id` int DEFAULT NULL,
  `pl_id` int DEFAULT NULL,
  PRIMARY KEY (`plc_id`),
  KEY `vh_id` (`vh_id`),
  KEY `pl_id` (`pl_id`),
  CONSTRAINT `play_list_contents_ibfk_1` FOREIGN KEY (`vh_id`) REFERENCES `vod_history` (`vh_id`),
  CONSTRAINT `play_list_contents_ibfk_2` FOREIGN KEY (`pl_id`) REFERENCES `play_list` (`pl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_list_contents`
--

LOCK TABLES `play_list_contents` WRITE;
/*!40000 ALTER TABLE `play_list_contents` DISABLE KEYS */;
/*!40000 ALTER TABLE `play_list_contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_list_good`
--

DROP TABLE IF EXISTS `play_list_good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_list_good` (
  `pl_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`pl_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `play_list_good_ibfk_1` FOREIGN KEY (`pl_id`) REFERENCES `play_list` (`pl_id`),
  CONSTRAINT `play_list_good_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_list_good`
--

LOCK TABLES `play_list_good` WRITE;
/*!40000 ALTER TABLE `play_list_good` DISABLE KEYS */;
/*!40000 ALTER TABLE `play_list_good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `re_comment`
--

DROP TABLE IF EXISTS `re_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `re_comment` (
  `rc_id` int NOT NULL AUTO_INCREMENT,
  `rc_contents` varchar(150) DEFAULT NULL,
  `rc_upload_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `u_id` int DEFAULT NULL,
  `c_id` int DEFAULT NULL,
  PRIMARY KEY (`rc_id`),
  KEY `u_id` (`u_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `re_comment_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`),
  CONSTRAINT `re_comment_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `comment_info` (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `re_comment`
--

LOCK TABLES `re_comment` WRITE;
/*!40000 ALTER TABLE `re_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `re_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `re_comment_good`
--

DROP TABLE IF EXISTS `re_comment_good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `re_comment_good` (
  `rc_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`rc_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `re_comment_good_ibfk_1` FOREIGN KEY (`rc_id`) REFERENCES `re_comment` (`rc_id`),
  CONSTRAINT `re_comment_good_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `re_comment_good`
--

LOCK TABLES `re_comment_good` WRITE;
/*!40000 ALTER TABLE `re_comment_good` DISABLE KEYS */;
/*!40000 ALTER TABLE `re_comment_good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`),
  CONSTRAINT `report_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `comment_info` (`c_id`),
  CONSTRAINT `report_ibfk_3` FOREIGN KEY (`rc_id`) REFERENCES `re_comment` (`rc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `un_follow`
--

DROP TABLE IF EXISTS `un_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `un_follow` (
  `uf_id` int NOT NULL,
  `u_id` int NOT NULL,
  PRIMARY KEY (`uf_id`,`u_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `un_follow_ibfk_1` FOREIGN KEY (`uf_id`) REFERENCES `member` (`u_id`),
  CONSTRAINT `un_follow_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `un_follow`
--

LOCK TABLES `un_follow` WRITE;
/*!40000 ALTER TABLE `un_follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `un_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vod`
--

DROP TABLE IF EXISTS `vod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vod`
--

LOCK TABLES `vod` WRITE;
/*!40000 ALTER TABLE `vod` DISABLE KEYS */;
/*!40000 ALTER TABLE `vod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vod_episode`
--

DROP TABLE IF EXISTS `vod_episode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vod_episode` (
  `ve_id` int NOT NULL AUTO_INCREMENT,
  `v_id` int NOT NULL,
  `ve_episode_num` int NOT NULL,
  `ve_contents` varchar(200) NOT NULL,
  PRIMARY KEY (`ve_id`,`v_id`),
  UNIQUE KEY `ve_episode_num` (`ve_episode_num`),
  KEY `v_id` (`v_id`),
  CONSTRAINT `vod_episode_ibfk_1` FOREIGN KEY (`v_id`) REFERENCES `vod` (`v_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vod_episode`
--

LOCK TABLES `vod_episode` WRITE;
/*!40000 ALTER TABLE `vod_episode` DISABLE KEYS */;
/*!40000 ALTER TABLE `vod_episode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vod_history`
--

DROP TABLE IF EXISTS `vod_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vod_history` (
  `vh_id` int NOT NULL AUTO_INCREMENT,
  `vh_watching_time` datetime DEFAULT '0000-00-00 00:00:00',
  `vh_comment` varchar(100) DEFAULT NULL,
  `vh_score` int DEFAULT NULL,
  `vh_good` tinyint(1) DEFAULT '0',
  `u_id` int DEFAULT NULL,
  `ve_id` int DEFAULT NULL,
  PRIMARY KEY (`vh_id`),
  KEY `u_id` (`u_id`),
  KEY `ve_id` (`ve_id`),
  CONSTRAINT `vod_history_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `member` (`u_id`),
  CONSTRAINT `vod_history_ibfk_2` FOREIGN KEY (`ve_id`) REFERENCES `vod_episode` (`ve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vod_history`
--

LOCK TABLES `vod_history` WRITE;
/*!40000 ALTER TABLE `vod_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `vod_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-27 13:12:09
