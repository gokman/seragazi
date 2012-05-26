-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.49-1ubuntu8.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema emlakVT
--

CREATE DATABASE IF NOT EXISTS emlakVT;
USE emlakVT;

--
-- Definition of table `emlakVT`.`ara_tara_mst`
--

DROP TABLE IF EXISTS `emlakVT`.`ara_tara_mst`;
CREATE TABLE  `emlakVT`.`ara_tara_mst` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `baslik` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `emlakVT`.`ara_tara_mst`
--

/*!40000 ALTER TABLE `ara_tara_mst` DISABLE KEYS */;
LOCK TABLES `ara_tara_mst` WRITE;
INSERT INTO `emlakVT`.`ara_tara_mst` VALUES  (1,'sfsdfs'),
 (2,'sdf'),
 (3,'gokman'),
 (4,'asd'),
 (5,'kocabey'),
 (6,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ara_tara_mst` ENABLE KEYS */;


--
-- Definition of table `emlakVT`.`musteri`
--

DROP TABLE IF EXISTS `emlakVT`.`musteri`;
CREATE TABLE  `emlakVT`.`musteri` (
  `ad` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `soyad` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id` int(10) unsigned NOT NULL DEFAULT '0',
  `tel` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sehir` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ulke` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adres_detay` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dogum_tarihi` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cinsiyet` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `medeni_durum` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cep_tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `musteri_tipi` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `meslek_bilgisi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `aylik_gelir` int(11) DEFAULT NULL,
  `egitim_durum` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'lisans,yüksek lisans,lise...',
  `calisilan_kurum` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ikamet_durum` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'kira,ev sahibi',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `emlakVT`.`musteri`
--

/*!40000 ALTER TABLE `musteri` DISABLE KEYS */;
LOCK TABLES `musteri` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `musteri` ENABLE KEYS */;


--
-- Definition of table `emlakVT`.`musteri_urun_islem`
--

DROP TABLE IF EXISTS `emlakVT`.`musteri_urun_islem`;
CREATE TABLE  `emlakVT`.`musteri_urun_islem` (
  `id` int(11) NOT NULL,
  `musteri_id` int(11) DEFAULT NULL,
  `urun_id` int(11) DEFAULT NULL,
  `islem_tip` varchar(155) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'takip ediyor,kiraladı,satın aldı,randevu aldı vs.',
  `olusum_tarihi` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `emlakVT`.`musteri_urun_islem`
--

/*!40000 ALTER TABLE `musteri_urun_islem` DISABLE KEYS */;
LOCK TABLES `musteri_urun_islem` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `musteri_urun_islem` ENABLE KEYS */;


--
-- Definition of table `emlakVT`.`urun`
--

DROP TABLE IF EXISTS `emlakVT`.`urun`;
CREATE TABLE  `emlakVT`.`urun` (
  `id` int(10) unsigned NOT NULL DEFAULT '0',
  `tip` varchar(45) DEFAULT NULL COMMENT 'arsa,kiralık daire,satılık daire,villa',
  `sehir` varchar(45) DEFAULT NULL,
  `semt` varchar(45) DEFAULT NULL,
  `adres_detay` varchar(255) DEFAULT NULL COMMENT 'mahalle,cadde,sokak no vs.',
  `ebat` int(10) unsigned DEFAULT NULL COMMENT '150 m2 vs.',
  `ozellik_detay` varchar(255) DEFAULT NULL COMMENT '3+1 daire, 2. kat vs.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emlakVT`.`urun`
--

/*!40000 ALTER TABLE `urun` DISABLE KEYS */;
LOCK TABLES `urun` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `urun` ENABLE KEYS */;


--
-- Definition of table `emlakVT`.`urun_ilan`
--

DROP TABLE IF EXISTS `emlakVT`.`urun_ilan`;
CREATE TABLE  `emlakVT`.`urun_ilan` (
  `urun_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `ilanda_mi` varchar(1) DEFAULT NULL COMMENT 'E veya H olacak. evet veya hayır yani',
  `islem_tarih` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ilan edilme veya kaldırılma zamanı',
  `neden` varchar(50) DEFAULT NULL COMMENT 'ilana konma veya ilandan kaldırılma nedeni(satılma,kiralanma...)',
  `bitis_tarih` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emlakVT`.`urun_ilan`
--

/*!40000 ALTER TABLE `urun_ilan` DISABLE KEYS */;
LOCK TABLES `urun_ilan` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `urun_ilan` ENABLE KEYS */;


--
-- Definition of table `emlakVT`.`user`
--

DROP TABLE IF EXISTS `emlakVT`.`user`;
CREATE TABLE  `emlakVT`.`user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `surname` varchar(25) NOT NULL,
  `username` varchar(25) NOT NULL,
  `birth_place` varchar(25) DEFAULT NULL,
  `membership_status` varchar(10) DEFAULT NULL COMMENT 'member_state alani kullanicinin üyeliğinin aktif olup olmadığını  belirtir.\n\n0 -> pasif\n1-> onayda\n2-> aktif',
  `adress` varchar(45) DEFAULT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `date_of_birth` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `activation_string` char(20) NOT NULL,
  `profile_image` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emlakVT`.`user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
