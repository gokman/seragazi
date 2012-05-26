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




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
