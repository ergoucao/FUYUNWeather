/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.28-log : Database - fuyun
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fuyun` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fuyun`;

/*Table structure for table `cities` */

DROP TABLE IF EXISTS `cities`;

CREATE TABLE `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityId` int(11) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `province` varchar(20) DEFAULT '直辖市及自治区',
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `cities` */

insert  into `cities`(`id`,`cityId`,`city`,`province`) values (1,110000,'北京市','直辖市'),(2,120000,'天津市','直辖市'),(3,130100,'合肥市','河北省'),(4,140100,'太原市','山西省'),(5,150100,'呼和浩特市','内蒙古自治区'),(6,210100,'沈阳市','辽宁省'),(7,220100,'长春市','吉林省'),(8,230100,'哈尔滨市','黑龙江省'),(9,310000,'上海市','直辖市'),(10,320100,'南京市','江苏省'),(11,330100,'杭州市','浙江省'),(12,340100,'合肥市','安徽省'),(13,350100,'福州市','福建省'),(14,360100,'南昌市','江西省'),(15,370100,'济南市','山东省'),(16,410100,'郑州市','河南省'),(17,420100,'武汉市','河北省'),(18,430100,'长沙市','湖南省'),(19,440100,'广州市','广东省'),(20,450100,'南宁市','广西壮族自治区'),(21,460100,'海口市','湖南省'),(22,500000,'重庆市','直辖市'),(23,510100,'成都市','四川省'),(24,520100,'贵阳市','贵州省'),(25,530100,'昆明市','云南省'),(26,540100,'拉萨市','西藏自治区'),(27,610100,'西安市','陕西省'),(28,620100,'兰州市','甘肃省'),(29,630100,'西宁市','青海省'),(30,640100,'银川市','宁夏回族自治区'),(31,650100,'乌鲁木齐市','新疆维吾尔自治区'),(32,710000,'台湾省','台湾'),(33,810000,'香港特别行政区','特别行政区');

/*Table structure for table `date` */

DROP TABLE IF EXISTS `date`;

CREATE TABLE `date` (
  `dateId` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `date` */

insert  into `date`(`dateId`,`name`) values (1,'2019-12-19'),(2,'2019-12-20'),(3,'2019-12-21'),(4,'2019-12-22');

/*Table structure for table `weather` */

DROP TABLE IF EXISTS `weather`;

CREATE TABLE `weather` (
  `id` int(11) DEFAULT NULL,
  `cityId` int(11) DEFAULT NULL,
  `dateId` int(11) DEFAULT NULL,
  `dayweatherEventId` int(11) DEFAULT NULL,
  `nightweatherEventId` int(11) DEFAULT NULL,
  `daytemp` int(8) DEFAULT NULL,
  `nighttemp` varchar(8) DEFAULT NULL,
  `daywindId` int(11) DEFAULT NULL,
  `nightwindId` int(11) DEFAULT NULL,
  `daypower` varchar(4) DEFAULT NULL,
  `nightpower` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `weather` */

insert  into `weather`(`id`,`cityId`,`dateId`,`dayweatherEventId`,`nightweatherEventId`,`daytemp`,`nighttemp`,`daywindId`,`nightwindId`,`daypower`,`nightpower`) values (1,110000,1,1,1,2,'-9',9,9,'4','4'),(2,110000,2,1,1,3,'-7',6,6,'≤3','≤3'),(3,110000,3,1,4,4,'-6',9,9,'≤3','≤3'),(4,110000,4,4,1,4,'-5',9,9,'≤3','≤3'),(5,120000,1,1,1,3,'-5',8,8,'4','4'),(6,120000,2,1,1,4,'-3',6,6,'≤3','≤3'),(7,120000,3,4,5,5,'-2',4,4,'≤3','≤3'),(8,120000,4,5,4,4,'-2',8,8,'5','5'),(9,130100,1,1,1,5,'-5',9,9,'≤3','≤3'),(10,130100,2,1,1,4,'-3',5,5,'≤3','≤3'),(11,130100,3,4,4,5,'-3',5,5,'≤3','≤3'),(12,130100,4,1,1,7,'-3',9,9,'≤3','≤3'),(13,140100,1,1,1,5,'-9',8,8,'≤3','≤3'),(14,140100,2,1,1,7,'-8',7,7,'≤3','≤3'),(15,140100,3,1,1,8,'-7',6,6,'≤3','≤3'),(16,140100,4,1,1,7,'-8',8,8,'≤3','≤3'),(17,150100,1,1,1,-7,'-15',8,8,'4','4'),(18,150100,2,1,1,-3,'-14',7,7,'4','4'),(19,150100,3,4,1,-2,'-14',6,6,'4','4'),(20,150100,4,1,1,-4,'-14',7,7,'≤3','≤3'),(21,210100,1,4,1,-5,'-16',9,9,'4','4'),(22,210100,2,1,1,-1,'-13',5,5,'4','4'),(23,210100,3,1,5,-2,'-12',6,6,'4','4'),(24,210100,4,44,4,0,'-14',6,6,'4','4'),(25,220100,1,4,1,-10,'-19',7,7,'≤3','≤3'),(26,220100,2,1,4,-7,'-14',6,6,'4','4'),(27,220100,3,4,4,-8,'-15',6,6,'≤3','≤3'),(28,220100,4,44,44,-10,'-17',6,6,'4','4'),(29,230100,1,4,1,-12,'-24',7,7,'≤3','≤3'),(30,230100,2,1,4,-10,'-21',6,6,'4','4'),(31,230100,3,1,4,-10,'-23',9,9,'≤3','≤3'),(32,230100,4,1,1,-8,'-24',4,4,'≤3','≤3'),(33,310000,1,4,4,8,'4',9,9,'≤3','≤3'),(34,310000,2,22,22,10,'7',2,2,'4','4'),(35,310000,3,22,23,10,'9',3,3,'4','4'),(36,310000,4,22,5,12,'9',9,9,'4','4'),(37,320100,1,4,4,7,'0',9,9,'≤3','≤3'),(38,320100,2,4,22,10,'2',3,3,'4','4'),(39,320100,3,22,23,8,'6',3,3,'4','4'),(40,320100,4,5,4,9,'6',9,9,'≤3','≤3'),(41,330100,1,22,5,6,'4',9,9,'4','4'),(42,330100,2,22,22,6,'6',1,1,'≤3','≤3'),(43,330100,3,33,23,10,'7',3,3,'4','4'),(44,330100,4,5,5,13,'9',7,7,'4','4'),(45,340100,1,4,4,8,'0',9,9,'≤3','≤3'),(46,340100,2,4,22,9,'2',3,3,'4','4'),(47,340100,3,22,22,8,'4',3,3,'4','4'),(48,340100,4,5,4,14,'7',8,8,'4','4'),(49,350100,1,22,5,15,'12',1,1,'≤3','≤3'),(50,350100,2,5,5,14,'12',1,1,'≤3','≤3'),(51,350100,3,4,4,20,'14',1,1,'≤3','≤3'),(52,350100,4,4,5,20,'15',8,8,'4','4'),(53,360100,1,22,22,7,'4',9,9,'5','5'),(54,360100,2,22,22,7,'5',2,2,'4','4'),(55,360100,3,22,22,8,'6',9,9,'≤3','≤3'),(56,360100,4,22,5,10,'7',6,6,'≤3','≤3'),(57,370100,1,1,1,6,'-3',9,9,'≤3','≤3'),(58,370100,2,1,1,8,'-1',5,5,'4','4'),(59,370100,3,1,5,10,'3',5,5,'4','4'),(60,370100,4,4,1,10,'0',6,6,'4','4'),(61,410100,1,4,4,10,'-4',3,3,'≤3','≤3'),(62,410100,2,4,4,8,'-2',5,5,'≤3','≤3'),(63,410100,3,4,4,10,'0',5,5,'≤3','≤3'),(64,410100,4,1,4,13,'0',7,7,'≤3','≤3'),(65,420100,1,4,4,9,'1',2,2,'≤3','≤3'),(66,420100,2,4,22,9,'3',3,3,'≤3','≤3'),(67,420100,3,22,22,8,'1',8,8,'≤3','≤3'),(68,420100,4,4,4,11,'2',7,7,'≤3','≤3'),(69,430100,1,22,22,8,'4',9,9,'≤3','≤3'),(70,430100,2,22,22,8,'5',9,9,'≤3','≤3'),(71,430100,3,22,22,8,'6',8,8,'≤3','≤3'),(72,430100,4,5,5,10,'6',9,9,'≤3','≤3'),(73,440100,1,4,4,19,'13',9,9,'4','4'),(74,440100,2,4,4,21,'15',1,1,'≤3','≤3'),(75,440100,3,4,4,23,'15',1,1,'≤3','≤3'),(76,440100,4,22,4,22,'15',1,1,'≤3','≤3'),(77,450100,1,22,22,15,'12',2,2,'4','4'),(78,450100,2,22,22,15,'12',2,2,'≤3','≤3'),(79,450100,3,22,22,15,'13',2,2,'≤3','≤3'),(80,450100,4,5,22,17,'11',2,2,'≤3','≤3'),(81,460100,1,22,22,24,'20',1,1,'≤3','≤3'),(82,460100,2,22,22,22,'20',2,2,'4','4'),(83,460100,3,22,22,24,'20',1,1,'≤3','≤3'),(84,460100,4,22,22,26,'20',1,1,'≤3','≤3'),(85,500000,1,5,22,9,'8',9,9,'≤3','≤3'),(86,500000,2,22,22,9,'8',5,5,'≤3','≤3'),(87,500000,3,22,4,9,'8',3,3,'≤3','≤3'),(88,500000,4,4,4,12,'7',4,4,'≤3','≤3'),(89,510100,1,5,22,10,'5',1,1,'≤3','≤3'),(90,510100,2,22,22,8,'5',1,1,'≤3','≤3'),(91,510100,3,22,22,9,'5',1,1,'≤3','≤3'),(92,510100,4,5,5,9,'5',1,1,'≤3','≤3'),(93,520100,1,5,22,6,'2',3,3,'≤3','≤3'),(94,520100,2,22,22,6,'3',3,3,'≤3','≤3'),(95,520100,3,22,5,5,'3',2,2,'≤3','≤3'),(96,520100,4,5,22,9,'6',5,5,'≤3','≤3'),(97,530100,1,4,1,18,'3',6,6,'4','4'),(98,530100,2,4,1,19,'4',6,6,'4','4'),(99,530100,3,4,4,18,'4',6,6,'4','4'),(100,530100,4,1,1,20,'3',6,6,'4','4'),(101,540100,1,1,1,11,'-5',1,1,'≤3','≤3'),(102,540100,2,1,1,12,'-1',1,1,'≤3','≤3'),(103,540100,3,1,1,10,'-4',1,1,'≤3','≤3'),(104,540100,4,1,1,10,'-5',1,1,'≤3','≤3'),(105,610100,1,1,1,9,'-4',3,3,'≤3','≤3'),(106,610100,2,4,4,9,'-3',2,2,'≤3','≤3'),(107,610100,3,5,4,9,'-4',2,2,'≤3','≤3'),(108,610100,4,1,4,9,'-2',2,2,'≤3','≤3'),(109,620100,1,1,1,3,'-6',3,3,'≤3','≤3'),(110,620100,2,4,1,3,'-7',6,6,'≤3','≤3'),(111,620100,3,1,1,5,'-7',2,2,'≤3','≤3'),(112,620100,4,1,1,1,'-8',7,7,'≤3','≤3'),(113,630100,1,1,1,0,'-11',1,1,'≤3','≤3'),(114,630100,2,1,1,0,'-10',1,1,'≤3','≤3'),(115,630100,3,1,1,2,'-10',1,1,'≤3','≤3'),(116,630100,4,1,1,3,'-9',7,7,'4','4'),(117,640100,1,1,1,1,'-8',1,1,'≤3','≤3'),(118,640100,2,1,1,3,'-8',1,1,'≤3','≤3'),(119,640100,3,1,1,3,'-8',9,9,'5','5'),(120,640100,4,1,1,2,'-8',1,1,'≤3','≤3'),(121,650100,1,56,56,-6,'-14',1,1,'≤3','≤3'),(122,650100,2,4,4,-3,'-13',1,1,'≤3','≤3'),(123,650100,3,56,56,-4,'-12',1,1,'≤3','≤3'),(124,650100,4,44,44,-5,'-12',1,1,'≤3','≤3'),(125,710000,1,22,22,20,'17',2,2,'4','4'),(126,710000,2,22,23,20,'17',3,3,'4','4'),(127,710000,3,4,4,22,'19',1,1,'≤3','≤3'),(128,710000,4,4,4,21,'18',1,1,'≤3','≤3'),(129,810000,1,4,4,23,'19',1,1,'≤3','≤3'),(130,810000,2,22,22,21,'17',2,2,'≤3','≤3'),(131,810000,3,22,22,20,'17',3,3,'4','4'),(132,810000,4,19,4,22,'17',1,1,'≤3','≤3'),(133,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(134,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(135,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(136,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(137,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(138,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(139,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(140,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(141,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(142,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(143,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(144,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(145,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(146,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(147,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(148,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(149,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(150,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(151,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(152,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(153,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(154,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(155,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(156,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(157,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(158,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(159,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(160,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(161,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(162,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(163,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(164,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(165,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(166,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(167,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(168,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(169,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(170,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(171,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(172,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(173,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(174,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(175,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(176,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(177,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(178,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(179,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(180,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(181,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(182,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(183,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(184,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(185,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(186,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(187,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(188,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(189,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(190,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(191,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(192,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(193,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(194,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(195,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(196,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(197,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(198,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(199,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(200,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `weatherevents` */

DROP TABLE IF EXISTS `weatherevents`;

CREATE TABLE `weatherevents` (
  `weatherEventId` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `weatherevents` */

insert  into `weatherevents`(`weatherEventId`,`name`) values (1,'晴'),(2,'少云'),(3,'晴间多云'),(4,'多云'),(5,'阴'),(6,'有风'),(7,'平静'),(8,'微风'),(9,'和风'),(10,'清风'),(11,'强风/劲风'),(12,'疾风'),(13,'大风'),(14,'烈风'),(15,'风暴'),(16,'狂爆风'),(17,'飓风'),(18,'热带风暴'),(19,'阵雨'),(20,'雷阵雨'),(21,'雷阵雨并伴有冰雹'),(22,'小雨'),(23,'中雨'),(24,'大雨'),(25,'暴雨'),(26,'大暴雨'),(27,'特大暴雨'),(28,'强阵雨'),(29,'强雷阵雨'),(30,'极端降雨'),(31,'毛毛雨/细雨'),(32,'雨'),(33,'小雨-中雨'),(34,'中雨-大雨'),(35,'大雨-暴雨'),(36,'暴雨-大暴雨'),(37,'大暴雨-特大暴雨'),(38,'雨雪天气'),(39,'雨夹雪'),(40,'阵雨夹雪'),(41,'冻雨'),(42,'雪'),(43,'阵雪'),(44,'小雪'),(45,'中雪'),(46,'大雪'),(47,'暴雪'),(48,'小雪-中雪'),(49,'中雪-大雪'),(50,'大雪-暴雪'),(51,'浮尘'),(52,'扬沙'),(53,'沙尘暴'),(54,'强沙尘暴'),(55,'龙卷风'),(56,'雾'),(57,'浓雾'),(58,'强浓雾'),(59,'轻雾'),(60,'大雾'),(61,'特强浓雾'),(62,'霾'),(63,'中度霾'),(64,'重度霾'),(65,'严重霾'),(66,'热'),(67,'冷'),(68,'未知');

/*Table structure for table `wind` */

DROP TABLE IF EXISTS `wind`;

CREATE TABLE `wind` (
  `windId` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wind` */

insert  into `wind`(`windId`,`name`) values (1,'无风向'),(2,'东北'),(3,'东'),(4,'东南'),(5,'南'),(6,'西南'),(7,'西'),(8,'西北'),(9,'北'),(10,'旋转不定');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
