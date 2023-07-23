/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.4.28-MariaDB : Database - product_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`product_db` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `product_db`;

/*Table structure for table `attrs` */

DROP TABLE IF EXISTS `attrs`;

CREATE TABLE `attrs` (
  `id` int(11) NOT NULL,
  `attrs_key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd0k2xkpyplifjkgx2m3fsyw58` (`product_id`),
  CONSTRAINT `FKd0k2xkpyplifjkgx2m3fsyw58` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `attrs` */

insert  into `attrs`(`id`,`attrs_key`,`value`,`product_id`) values (0,'JAN???','0404233477019',0),(1,'?????','???????',0),(2,NULL,NULL,1);

/*Table structure for table `attrs_seq` */

DROP TABLE IF EXISTS `attrs_seq`;

CREATE TABLE `attrs_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `attrs_seq` */

insert  into `attrs_seq`(`next_val`) values (1);

/*Table structure for table `description` */

DROP TABLE IF EXISTS `description`;

CREATE TABLE `description` (
  `id` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7snqhoww0nj9mr0ueel769wbn` (`product_id`),
  CONSTRAINT `FK7snqhoww0nj9mr0ueel769wbn` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `description` */

insert  into `description`(`id`,`value`,`product_id`) values (0,NULL,0),(1,NULL,1);

/*Table structure for table `description_seq` */

DROP TABLE IF EXISTS `description_seq`;

CREATE TABLE `description_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `description_seq` */

insert  into `description_seq`(`next_val`) values (1);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `jan_code` varchar(255) NOT NULL,
  `maker` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`brand`,`jan_code`,`maker`,`product_name`) values (0,NULL,'0404233477019',NULL,'????1NALP'),(1,NULL,'4516946031351',NULL,'????? ????');

/*Table structure for table `product_seq` */

DROP TABLE IF EXISTS `product_seq`;

CREATE TABLE `product_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `product_seq` */

insert  into `product_seq`(`next_val`) values (1);

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtbwj6nvgtvvgwr7shern86rlq` (`product_id`),
  CONSTRAINT `FKtbwj6nvgtvvgwr7shern86rlq` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `review` */

insert  into `review`(`id`,`value`,`product_id`) values (0,NULL,0),(1,NULL,1);

/*Table structure for table `review_seq` */

DROP TABLE IF EXISTS `review_seq`;

CREATE TABLE `review_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `review_seq` */

insert  into `review_seq`(`next_val`) values (1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
