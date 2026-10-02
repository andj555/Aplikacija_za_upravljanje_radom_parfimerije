/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - njt_projekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`njt_projekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `njt_projekat`;

/*Table structure for table `emailstavka` */

DROP TABLE IF EXISTS `emailstavka`;

CREATE TABLE `emailstavka` (
  `idEmailStavka` bigint(20) NOT NULL AUTO_INCREMENT,
  `naslov` varchar(255) DEFAULT NULL,
  `primalac` varchar(255) DEFAULT NULL,
  `sadrzaj` text DEFAULT NULL,
  `poslato` tinyint(1) DEFAULT NULL,
  `vremeKreiranja` datetime DEFAULT NULL,
  `vremeSlanja` datetime DEFAULT NULL,
  PRIMARY KEY (`idEmailStavka`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `emailstavka` */

insert  into `emailstavka`(`idEmailStavka`,`naslov`,`primalac`,`sadrzaj`,`poslato`,`vremeKreiranja`,`vremeSlanja`) values 
(1,'Verfikacija emaila','andjelija125@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=345f562b-2f32-4d0d-9230-6c7db924ee2e\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 21:04:54','2026-09-10 00:45:06'),
(2,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=89030baf-6643-4e63-96f2-1149ef238979\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 21:06:31','2026-09-10 00:45:12'),
(3,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=ffe66843-82b4-48ea-8689-4a5ca86eb706\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 21:49:01','2026-09-10 00:45:15'),
(4,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=5d0017b9-4f34-4de4-895f-b2a9ae9d8426\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 21:55:41','2026-09-10 00:45:18'),
(5,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=0358db49-a8bc-4a83-8c2d-71479b6b27c0\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:06:31','2026-09-10 00:45:22'),
(6,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=5a291a34-1e40-40c3-8828-7a338e97bf5b\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:11:52','2026-09-10 00:45:26'),
(7,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=ccdbf98d-052b-4f71-ab61-555687b03396\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:15:34','2026-09-10 00:45:29'),
(8,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=63772d79-f875-4378-a264-24d3149574de\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:18:59','2026-09-10 00:45:32'),
(9,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=ae6020c9-132a-4fcb-8b58-b98a659ffaf1\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:21:20','2026-09-10 00:45:36'),
(10,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=36059b26-78f8-45d6-aa2d-137edc7b3d52\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:23:22','2026-09-10 00:45:39'),
(11,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=4e8be647-1d13-4950-b2c0-376d7e94a18c\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:25:47','2026-09-10 00:45:41'),
(12,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=2689b355-9a04-412e-bc87-6118c6bcc54c\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:28:16','2026-09-10 00:45:44'),
(13,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:3000/verifikuj?token=9947dea5-098f-4c09-a1e7-53577b0e6da2\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-08 22:34:10','2026-09-10 00:45:47'),
(14,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikuj?token=7be006b5-c43d-4ab6-a3df-85b4bb779332\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 01:00:10','2026-09-10 00:45:50'),
(15,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=97dfbfaf-fa91-467b-af4f-e574b3aeaa01\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 01:50:43','2026-09-10 00:45:52'),
(16,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=6c4a2611-8d31-42d4-8942-b148c9504235\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 02:19:43','2026-09-10 00:45:54'),
(17,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=d5e84173-c7cb-4c93-b5d3-6fa4c6e78928\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 02:22:47','2026-09-10 00:45:57'),
(18,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=ff75c32e-62e6-405a-b107-6f1f611c8625\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 02:31:26','2026-09-10 00:45:59'),
(19,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=f7768c9c-6f0f-4d0b-8490-c32c2ecae929\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 02:38:29','2026-09-10 00:46:02'),
(20,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=5de97019-f9ed-47be-a80a-11ed3a17d570\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 02:42:58','2026-09-10 00:46:04'),
(21,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=96119888-1f5b-47c9-b05d-bcfc2347629d\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 02:43:00','2026-09-10 00:46:07'),
(22,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=792b6927-b2e3-4388-b080-4cde4b4135ba\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 03:11:11','2026-09-10 00:46:11'),
(23,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=537006f9-c77e-452b-8cc0-4bf160c21baf\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 03:21:17','2026-09-10 00:46:14'),
(24,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=97e237a8-23b0-4e17-aaf9-1b1cd7af41f1\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 03:26:49','2026-09-10 00:46:17'),
(25,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=124053df-0069-47c0-9d7e-90256b2fac5b\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 03:30:32','2026-09-10 00:46:20'),
(26,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=5dcbcad5-3a1b-404f-b5a7-16fd8e9191b2\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 03:37:01','2026-09-10 00:46:23'),
(27,'Verfikacija emaila','andjelija125@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=fad253e7-ee49-4cfb-ab89-728ae7af5cf8\n\nAko niste vi zatražili registraciju, ignorišite ovaj mejl.',1,'2026-09-09 17:21:01','2026-09-10 00:46:27'),
(28,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=5c9ad6c1-e601-4c1e-b142-58c8b0f4b03b\n\nUkoliko niste Vi započeli registraciju, ignorišite ovaj mejl.',1,'2026-09-10 01:07:15','2026-09-10 01:07:28'),
(29,'Verfikacija emaila','ljiljana.jovanovic1@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=b037d3c7-476c-4f04-87eb-8ce06b44dfd7\n\nUkoliko niste Vi započeli registraciju, ignorišite ovaj mejl.',1,'2026-09-11 18:35:34','2026-09-11 18:35:47'),
(30,'Verfikacija emaila','ljiljana.jovanovic1@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=77356c4e-cd8b-48aa-8732-f953fce98ba0\n\nUkoliko niste Vi započeli registraciju, ignorišite ovaj mejl.',1,'2026-09-11 18:37:21','2026-09-11 18:37:25'),
(31,'Verfikacija emaila','jovanovicmilija12@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=3a9f0c86-e537-481b-8ce8-d59d3ae0da19\n\nUkoliko niste Vi započeli registraciju, ignorišite ovaj mejl.',1,'2026-09-11 18:39:10','2026-09-11 18:39:15'),
(32,'Verfikacija emaila','juniorke74@gmail.com','Poštovani,\n\nKliknite na link da biste verifikovali svoj email i dovršite registraciju:\n\nhttp://localhost:5173/verifikacija?token=a5f2567c-432d-4d20-b479-5ba96e2bd330\n\nUkoliko niste Vi započeli registraciju, ignorišite ovaj mejl.',1,'2026-09-11 18:40:19','2026-09-11 18:40:26'),
(33,'Potvrda porudžbine #5','ana@gmail.com','Poštovani,\n\nVaša porudžbina #5 je uspešno kreirana\n\nUkupan iznos: 19900.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-13 21:11:50','2026-09-13 21:12:01'),
(34,'Potvrda porudžbine #6','ana@gmail.com','Poštovani,\n\nVaša porudžbina #6 je uspešno kreirana\n\nUkupan iznos: 14280.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-14 00:48:01','2026-09-14 00:48:10'),
(35,'Potvrda porudžbine #7','andjelija125@gmail.com','Poštovani,\n\nVaša porudžbina #7 je uspešno kreirana\n\nUkupan iznos: 26400.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-14 06:30:20','2026-09-14 06:30:30'),
(36,'Otpremnica OTP-2026-001','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-001.\n\n',1,'2026-09-14 17:07:26','2026-09-14 17:07:37'),
(42,'Otpremnica OTP-2026-002','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-002.\n\n',1,'2026-09-14 17:07:27','2026-09-14 17:07:42'),
(47,'Otpremnica OTP-2026-002','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-002.\n\n',1,'2026-09-14 17:07:28','2026-09-14 17:07:46'),
(49,'Otpremnica OTP-2026-003','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-003.\n\n',1,'2026-09-14 17:07:28','2026-09-14 17:07:49'),
(52,'Potvrda porudžbine #8','mika@gmail.com','Poštovani,\n\nVaša porudžbina #8 je uspešno kreirana\n\nUkupan iznos: 44400.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-14 17:32:05','2026-09-14 17:32:15'),
(53,'Otpremnica OTP-2026-005','mika@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-005.\n\n',1,'2026-09-14 17:33:55','2026-09-14 17:34:06'),
(58,'Potvrda porudžbine #9','andjelija125@gmail.com','Poštovani,\n\nVaša porudžbina #9 je uspešno kreirana\n\nUkupan iznos: 77400.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-14 20:51:43','2026-09-14 20:51:55'),
(59,'Otpremnica OTP-2026-006','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-006.\n\n',1,'2026-09-14 20:53:35','2026-09-14 20:53:41'),
(61,'Otpremnica OTP-2026-007','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-007.\n\n',1,'2026-09-14 21:04:04','2026-09-14 21:04:11'),
(65,'Ispravka otpremnice OTP-2026-007','andjelija125@gmail.com','Poštovani,\n\nObaveštavamo vas da je otpremnica OTP-2026-007 koju ste ranije dobili imala grešku u sebi i otkazana je. Uskoro ćete dobiti ispravnu otpremnicu sa tačnim podacima.\n\nIzvinjavamo Vam se na neprijatnosti',1,'2026-09-14 21:16:09','2026-09-14 21:16:17'),
(66,'Potvrda porudžbine #10','andjelija125@gmail.com','Poštovani,\n\nVaša porudžbina #10 je uspešno kreirana\n\nUkupan iznos: 24000.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-14 21:41:30','2026-09-14 21:41:40'),
(67,'Otpremnica OTP-2026-008','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-008.\n\n',1,'2026-09-14 21:50:30','2026-09-14 21:50:38'),
(68,'Otpremnica OTP-2026-009','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-009.\n\n',1,'2026-09-14 21:51:19','2026-09-14 21:51:27'),
(69,'Ispravka otpremnice OTP-2026-009','andjelija125@gmail.com','Poštovani,\n\nObaveštavamo vas da je otpremnica OTP-2026-009 koju ste ranije dobili imala grešku u sebi i otkazana je. Uskoro ćete dobiti ispravnu otpremnicu sa tačnim podacima.\n\nIzvinjavamo Vam se na neprijatnosti',1,'2026-09-14 21:52:33','2026-09-14 21:52:38'),
(70,'Potvrda porudžbine #11','andjelija125@gmail.com','Poštovani,\n\nVaša porudžbina #11 je uspešno kreirana\n\nUkupan iznos: 63000.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-14 22:57:04','2026-09-14 22:57:17'),
(71,'Otpremnica OTP-2026-010','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-010.\n\n',1,'2026-09-14 22:58:21','2026-09-14 22:58:26'),
(72,'Potvrda porudžbine #12','mika@gmail.com','Poštovani,\n\nVaša porudžbina #12 je uspešno kreirana\n\nUkupan iznos: 52920.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-14 23:00:22','2026-09-14 23:00:35'),
(73,'Otpremnica OTP-2026-011','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-011.\n\n',1,'2026-09-14 23:01:32','2026-09-14 23:01:46'),
(74,'Ispravka otpremnice OTP-2026-011','andjelija125@gmail.com','Poštovani,\n\nObaveštavamo vas da je otpremnica OTP-2026-011 koju ste ranije dobili imala grešku u sebi i otkazana je. Uskoro ćete dobiti ispravnu otpremnicu sa tačnim podacima.\n\nIzvinjavamo Vam se na neprijatnosti',1,'2026-09-14 23:01:47','2026-09-14 23:01:55'),
(75,'Otpremnica OTP-2026-012','mika@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-012.\n\n',1,'2026-09-14 23:02:59','2026-09-14 23:03:05'),
(76,'Ispravka otpremnice OTP-2026-012','mika@gmail.com','Poštovani,\n\nObaveštavamo vas da je otpremnica OTP-2026-012 koju ste ranije dobili imala grešku u sebi i otkazana je. Uskoro ćete dobiti ispravnu otpremnicu sa tačnim podacima.\n\nIzvinjavamo Vam se na neprijatnosti',1,'2026-09-14 23:03:17','2026-09-14 23:03:25'),
(77,'Otpremnica OTP-2026-013','mika@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-013.\n\n',1,'2026-09-14 23:03:44','2026-09-14 23:03:54'),
(78,'Otpremnica OTP-2026-014','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! U prilogu se nalazi otpremnica OTP-2026-014.\n\n',1,'2026-09-14 23:04:03','2026-09-14 23:04:14'),
(79,'Potvrda porudžbine #13','juniorke74@gmail.com','Poštovani,\n\nVaša porudžbina #13 je uspešno kreirana\n\nUkupan iznos: 51720.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\n',1,'2026-09-14 23:48:07','2026-09-14 23:48:17'),
(80,'Otpremnica OTP-2026-001','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-001.\n\n',1,'2026-09-14 23:49:52','2026-09-14 23:50:06'),
(81,'Otpremnica OTP-2026-002','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-002.\n\n',1,'2026-09-14 23:50:30','2026-09-14 23:50:37'),
(82,'Ispravka otpremnice OTP-2026-002','juniorke74@gmail.com','Poštovani,\n\nObaveštavamo vas da je otpremnica OTP-2026-002 koju ste ranije dobili imala grešku u sebi i otkazana je. Uskoro ćete dobiti ispravnu otpremnicu sa tačnim podacima.\n\nIzvinjavamo Vam se na neprijatnosti',1,'2026-09-15 00:09:09','2026-09-15 00:09:23'),
(83,'Otpremnica OTP-2026-003','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-003.\n\n',1,'2026-09-15 00:09:20','2026-09-15 00:09:33'),
(84,'Potvrda porudžbine #14','juniorke74@gmail.com','Poštovani,\n\nVaša porudžbina #14 je uspešno kreirana\n\nUkupan iznos: 19200.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\nNaručili ste:\n- Green Tea (x4) — 19200.0 Rsd\n',1,'2026-09-15 01:49:16','2026-09-15 01:49:30'),
(85,'Otpremnica OTP-2026-004','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-004.\n\nProizvodi: - Green Tea , kom: 4\nIznos: 19200.0',1,'2026-09-15 01:51:09','2026-09-15 01:51:19'),
(86,'Potvrda porudžbine #15','juniorke74@gmail.com','Poštovani,\n\nVaša porudžbina #15 je uspešno kreirana\n\nUkupan iznos: 20400.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\nNaručili ste:\n- Green Tea (x2) — 9600.0 Rsd\n- Daisy (x1) — 10800.0 Rsd\n',1,'2026-09-15 03:15:18','2026-09-15 03:15:26'),
(87,'Otpremnica OTP-2026-005','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-005.\n\nProizvodi: - Green Tea , kom: 2\nIznos: 9600.0',1,'2026-09-15 03:17:05','2026-09-15 03:17:13'),
(88,'Otpremnica OTP-2026-006','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-006.\n\nProizvodi: - Daisy , kom: 1\nIznos: 10800.0',1,'2026-09-15 03:17:28','2026-09-15 03:17:33'),
(89,'Potvrda porudžbine #16','juniorke74@gmail.com','Poštovani,\n\nVaša porudžbina #16 je uspešno kreirana\n\nUkupan iznos: 43200.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\nNaručili ste:\n- Spicebomb (x2) — 29760.0 Rsd\n- La Vie Est Belle (x1) — 13440.0 Rsd\n',1,'2026-09-15 07:12:28','2026-09-15 17:59:26'),
(90,'Otpremnica OTP-2026-007','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-007.\n\nProizvodi: - Spicebomb , kom: 1\n- La Vie Est Belle , kom: 1\nIznos: 28320.0',1,'2026-09-15 07:14:44','2026-09-15 17:59:12'),
(91,'Otpremnica OTP-2026-008','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-008.\n\nProizvodi: - Spicebomb , kom: 1\nIznos: 14880.0',1,'2026-09-15 07:15:03','2026-09-15 17:59:01'),
(92,'Potvrda porudžbine #17','andjelija125@gmail.com','Poštovani,\n\nVaša porudžbina #17 je uspešno kreirana\n\nUkupan iznos: 26880.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\nNaručili ste:\n- La Vie Est Belle (x2) — 26880.0 Rsd\n',1,'2026-09-16 23:42:30','2026-09-16 23:42:40'),
(93,'Otpremnica OTP-2026-009','andjelija125@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-009.\n\nProizvodi: - La Vie Est Belle , kom: 2\nIznos: 26880.0',1,'2026-09-16 23:44:02','2026-09-16 23:44:09'),
(94,'Potvrda porudžbine #18','andjelija125@gmail.com','Poštovani,\n\nVaša porudžbina #18 je uspešno kreirana\n\nUkupan iznos: 13440.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\nNaručili ste:\n- La Vie Est Belle (x1) — 13440.0 Rsd\n',1,'2026-09-17 22:03:55','2026-09-17 22:04:02'),
(95,'Potvrda porudžbine #19','juniorke74@gmail.com','Poštovani,\n\nVaša porudžbina #19 je uspešno kreirana\n\nUkupan iznos: 55200.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\nNaručili ste:\n- Black Opium (x2) — 31200.0 Rsd\n- Oud Wood (x1) — 24000.0 Rsd\n',1,'2026-09-17 23:50:00','2026-09-17 23:50:10'),
(96,'Otpremnica OTP-2026-010','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-010.\n\nProizvodi: - Black Opium , kom: 2\n- Oud Wood , kom: 1\nIznos: 55200.0',1,'2026-09-17 23:50:43','2026-09-17 23:50:51'),
(97,'Potvrda porudžbine #20','juniorke74@gmail.com','Poštovani,\n\nVaša porudžbina #20 je uspešno kreirana\n\nUkupan iznos: 9600.0 RSD\n\nBićete obavešteni kada Vaša porudžbina bude poslata\n\nNaručili ste:\n- Green Tea (x2) — 9600.0 Rsd\n',1,'2026-09-17 23:55:15','2026-09-17 23:55:19'),
(98,'Otpremnica OTP-2026-011','juniorke74@gmail.com','Poštovani,\n\nDeo Vaše porudžbine je poslat! Broj otpremnice: OTP-2026-011.\n\nProizvodi: - Green Tea , kom: 2\nIznos: 8160.0',1,'2026-09-17 23:56:23','2026-09-17 23:56:28');

/*Table structure for table `kategorijamirisa` */

DROP TABLE IF EXISTS `kategorijamirisa`;

CREATE TABLE `kategorijamirisa` (
  `idKategorijaMirisa` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idKategorijaMirisa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kategorijamirisa` */

insert  into `kategorijamirisa`(`idKategorijaMirisa`,`naziv`) values 
(1,'cvetni'),
(2,'vocni/citrusni'),
(3,'drvenasti'),
(4,'zacinski'),
(5,'svezi');

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `idKorisnik` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `tipKorisnika` enum('ZAPOSLENI','KUPAC') DEFAULT NULL,
  `potvrdjenEmail` tinyint(1) DEFAULT NULL,
  `verifikacioniToken` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idKorisnik`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`idKorisnik`,`email`,`password`,`ime`,`prezime`,`tipKorisnika`,`potvrdjenEmail`,`verifikacioniToken`) values 
(1,'mara@gmail.com','12345','Mara','Maric','ZAPOSLENI',NULL,NULL),
(2,'pera@gmail.com','67890','Pera','Peric','ZAPOSLENI',NULL,NULL),
(3,'ana@gmail.com','11223','Ana','Anic','KUPAC',NULL,NULL),
(4,'mika@gmail.com','44556','Mika','Mikic','KUPAC',NULL,NULL),
(5,'haha@gmail.com','xdxdxd','Vesna','Kostic','KUPAC',NULL,NULL),
(6,'andrija@gmail','andrija','andrija','andrija','ZAPOSLENI',NULL,NULL),
(25,'andjelija125@gmail.com','111111','andja','jo','KUPAC',1,NULL),
(29,'juniorke74@gmail.com','123456','nikci','ender','KUPAC',1,'a5f2567c-432d-4d20-b479-5ba96e2bd330');

/*Table structure for table `otpremnica` */

DROP TABLE IF EXISTS `otpremnica`;

CREATE TABLE `otpremnica` (
  `idOtpremnica` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumIzdavanja` date DEFAULT NULL,
  `brojOtpremnice` varchar(50) DEFAULT NULL,
  `iznos` double DEFAULT NULL,
  `pdv` double DEFAULT NULL,
  `iznosBezPdv` double DEFAULT NULL,
  `idPorudzbina` bigint(20) DEFAULT NULL,
  `status` enum('KREIRANA','IZDATA','OTKAZANA') DEFAULT NULL,
  PRIMARY KEY (`idOtpremnica`),
  KEY `idPorudzbina` (`idPorudzbina`),
  CONSTRAINT `otpremnica_ibfk_1` FOREIGN KEY (`idPorudzbina`) REFERENCES `porudzbina` (`idPorudzbina`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `otpremnica` */

insert  into `otpremnica`(`idOtpremnica`,`datumIzdavanja`,`brojOtpremnice`,`iznos`,`pdv`,`iznosBezPdv`,`idPorudzbina`,`status`) values 
(27,'2026-09-15','OTP-2026-001',29880,4980,24900,13,'IZDATA'),
(28,'2026-09-15','OTP-2026-002',21840,3640,18200,13,'OTKAZANA'),
(29,'2026-09-15','OTP-2026-003',21840,3640,18200,13,'IZDATA'),
(30,'2026-09-15','OTP-2026-004',19200,3200,16000,14,'IZDATA'),
(31,'2026-09-15','OTP-2026-005',9600,1600,8000,15,'IZDATA'),
(32,'2026-09-15','OTP-2026-006',10800,1800,9000,15,'IZDATA'),
(33,'2026-09-15','OTP-2026-007',28320,4720,23600,16,'IZDATA'),
(34,'2026-09-15','OTP-2026-008',14880,2480,12400,16,'IZDATA'),
(35,'2026-09-17','OTP-2026-009',26880,4480,22400,17,'IZDATA'),
(36,'2026-09-18','OTP-2026-010',55200,9200,46000,19,'IZDATA'),
(37,'2026-09-18','OTP-2026-011',8160,1600,8000,20,'IZDATA');

/*Table structure for table `poreskastopa` */

DROP TABLE IF EXISTS `poreskastopa`;

CREATE TABLE `poreskastopa` (
  `idPoreskaStopa` bigint(20) NOT NULL AUTO_INCREMENT,
  `procenat` double DEFAULT NULL,
  `naziv` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPoreskaStopa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `poreskastopa` */

insert  into `poreskastopa`(`idPoreskaStopa`,`procenat`,`naziv`) values 
(1,20,'opsta stopa'),
(2,10,'posebna stopa'),
(3,0,'oslobodjeno poreza');

/*Table structure for table `porudzbina` */

DROP TABLE IF EXISTS `porudzbina`;

CREATE TABLE `porudzbina` (
  `idPorudzbina` bigint(20) NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `ukupanIznos` double DEFAULT NULL,
  `statusPorudzbine` enum('KREIRANA','ISPORUCENA','OTKAZANA') DEFAULT NULL,
  `statusIsporuke` enum('NEISPORUCENO','DELIMICNO_ISPORUCENO','POTPUNO_ISPORUCENO') DEFAULT NULL,
  `adresaIsporuke` varchar(100) DEFAULT NULL,
  `idKorisnik` bigint(20) DEFAULT NULL,
  `popust` double DEFAULT NULL,
  PRIMARY KEY (`idPorudzbina`),
  KEY `idKorisnik` (`idKorisnik`),
  CONSTRAINT `porudzbina_ibfk_1` FOREIGN KEY (`idKorisnik`) REFERENCES `korisnik` (`idKorisnik`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `porudzbina` */

insert  into `porudzbina`(`idPorudzbina`,`datum`,`ukupanIznos`,`statusPorudzbine`,`statusIsporuke`,`adresaIsporuke`,`idKorisnik`,`popust`) values 
(13,'2026-09-14',36204,'ISPORUCENA','POTPUNO_ISPORUCENO','Cacak',29,30),
(14,'2026-09-15',19200,'ISPORUCENA','POTPUNO_ISPORUCENO','rtfyfyxdfk',29,NULL),
(15,'2026-09-15',20400,'ISPORUCENA','POTPUNO_ISPORUCENO','qwertyyuii',29,NULL),
(16,'2026-09-15',38880,'ISPORUCENA','POTPUNO_ISPORUCENO','bgd',29,10),
(17,'2026-09-16',26880,'ISPORUCENA','POTPUNO_ISPORUCENO','po',25,NULL),
(18,'2026-09-17',13440,'OTKAZANA','NEISPORUCENO','ergerg',25,NULL),
(19,'2026-09-17',33120,'ISPORUCENA','POTPUNO_ISPORUCENO','novi sad',29,40),
(20,'2026-09-17',8160,'ISPORUCENA','POTPUNO_ISPORUCENO','valjevo',29,15);

/*Table structure for table `proizvod` */

DROP TABLE IF EXISTS `proizvod`;

CREATE TABLE `proizvod` (
  `idProizvod` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(75) DEFAULT NULL,
  `cena` double DEFAULT NULL,
  `kolicinaNaStanju` bigint(20) DEFAULT NULL,
  `opis` varchar(250) DEFAULT NULL,
  `marka` varchar(50) DEFAULT NULL,
  `slika` varchar(250) DEFAULT NULL,
  `idKategorijaMirisa` bigint(20) DEFAULT NULL,
  `idPoreskaStopa` bigint(20) DEFAULT NULL,
  `kategorijaPol` varchar(50) DEFAULT NULL,
  `rezervisanoStanje` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProizvod`),
  KEY `idKategorijaMirisa` (`idKategorijaMirisa`),
  KEY `idPoreskaStopa` (`idPoreskaStopa`),
  CONSTRAINT `proizvod_ibfk_1` FOREIGN KEY (`idKategorijaMirisa`) REFERENCES `kategorijamirisa` (`idKategorijaMirisa`),
  CONSTRAINT `proizvod_ibfk_2` FOREIGN KEY (`idPoreskaStopa`) REFERENCES `poreskastopa` (`idPoreskaStopa`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `proizvod` */

insert  into `proizvod`(`idProizvod`,`naziv`,`cena`,`kolicinaNaStanju`,`opis`,`marka`,`slika`,`idKategorijaMirisa`,`idPoreskaStopa`,`kategorijaPol`,`rezervisanoStanje`) values 
(1,'Daisy',9000,11,'cvetni parfem sa notama ljubičice i jasmina','Marc Jacobs','https://jasmin.b-cdn.net/media/catalog/product/cache/fd5ba08c7c33545cb9e0a4ad2370c4aa/2/0/200302000338-b54c3082-fb0b-415c-8820-51e77308826e_3.jpg',1,1,'z',0),
(2,'La Vie Est Belle',11200,4,'cvetni parfem sa notama irisa i jasmina','Lancome','https://prodajaparfema.rs/wp-content/uploads/2021/09/lancome-la-vie-est-belle.png.webp',1,1,'z',0),
(3,'Light Blue',7900,18,'voćni i citrusni parfem sa notama sicilijanskog limuna i jabuke','Dolce & Gabbana','https://prodajaparfema.rs/wp-content/uploads/2021/09/dolcegabbana-light-blue.png',2,1,'u',0),
(4,'Lime Basil & Mandarin',10000,8,'voćni i citrusni parfem sa notama mandarine i limete','Jo Malone London','https://fabuspot.rs/upload/catalog/variation/2286/690251084906-1_6a043930ced42.jpg',2,1,'m',0),
(5,'Terre d Hermes',12300,7,'drvenasti parfem sa notama kedra i vetivera','Hermes','https://www.myperfumeshop.qa/cdn/shop/files/hermes-terre-dhermes-parfum-958615.png?v=1741587382&width=2048',3,1,'m',0),
(6,'Oud Wood',20000,4,'drvenasti parfem sa notama agar-drveta i sandalovine','Tom Ford','https://duro-rs.s3.eu-central-1.amazonaws.com/tom-ford/tom-ford-oud-wood-parfum-50ml-001.jpg',3,1,'m',0),
(7,'Black Opium',13000,12,'začinski parfem sa notama vanile i kafe','Yves Saint Laurent','https://prodajaparfema.rs/wp-content/uploads/2021/09/ysl-black-opium-1.png.webp',4,1,'z',0),
(8,'Tobacco Vanille',21000,13,'začinski parfem sa notama duvana i vanile','Tom Ford','https://d3qyz78uf5x6r9.cloudfront.net/public/ACC_11/account/product/7050515/7050515_A1.jpg',4,1,'u',0),
(9,'Cool Water',6500,16,'sveži i vodenasti parfem sa morskim notama i lavandom','Davidoff','https://prodajaparfema.rs/wp-content/uploads/2021/09/davidoff-cool-water-women.png',5,1,'z',0),
(10,'Le Jardin de Monsieur Li',10500,14,'sveži parfem sa notama kumkvata i nane','Hermes','https://prodajaparfema.rs/wp-content/uploads/2021/09/hermes-le-jardin-de-monsieur-li.png',5,1,'u',0),
(11,'Green Tea',4000,20,'sveži parfem sa notama zelenog čaja, mente i citrusa','Elizabeth Arden','https://prodajaparfema.rs/wp-content/uploads/2021/09/elizabeth-arden-green-tea-edp.png',5,1,'z',0),
(12,'Spicebomb',12400,8,'začinski parfem sa notama cimeta, čilija i kože','Viktor & Rolf','https://noar.rs/cdn/shop/files/01_vr_frag_spb_edt_perfect_pdp_premium_packshot_90ml_1x1_0ea8aafc-54ab-4a64-995a-820acfe1ff4a.jpg?v=1743043000&width=1200',4,1,'m',0),
(14,'Bloom ',9100,8,'slatkasti cvetni parfem','Gucci','https://prodajaparfema.rs/wp-content/uploads/2022/06/Gucci-Bloom-min.png',1,1,'z',0);

/*Table structure for table `stavkaotpremnice` */

DROP TABLE IF EXISTS `stavkaotpremnice`;

CREATE TABLE `stavkaotpremnice` (
  `rb` bigint(20) NOT NULL AUTO_INCREMENT,
  `kolicina` int(20) DEFAULT NULL,
  `iznos` double DEFAULT NULL,
  `cenaPoKomadu` double DEFAULT NULL,
  `pdv` double DEFAULT NULL,
  `iznosBezPdv` double DEFAULT NULL,
  `idOtpremnica` bigint(20) NOT NULL,
  `idProizvod` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rb`,`idOtpremnica`),
  KEY `idOtpremnica` (`idOtpremnica`),
  KEY `idProizvod` (`idProizvod`),
  CONSTRAINT `stavkaotpremnice_ibfk_1` FOREIGN KEY (`idOtpremnica`) REFERENCES `otpremnica` (`idOtpremnica`),
  CONSTRAINT `stavkaotpremnice_ibfk_2` FOREIGN KEY (`idProizvod`) REFERENCES `proizvod` (`idProizvod`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkaotpremnice` */

insert  into `stavkaotpremnice`(`rb`,`kolicina`,`iznos`,`cenaPoKomadu`,`pdv`,`iznosBezPdv`,`idOtpremnica`,`idProizvod`) values 
(45,1,10920,9100,1820,9100,27,14),
(46,2,18960,7900,3160,15800,27,3),
(47,2,21840,9100,3640,18200,28,14),
(48,2,21840,9100,3640,18200,29,14),
(49,4,19200,4000,3200,16000,30,11),
(50,2,9600,4000,1600,8000,31,11),
(51,1,10800,9000,1800,9000,32,1),
(52,1,14880,12400,2480,12400,33,12),
(53,1,13440,11200,2240,11200,33,2),
(54,1,14880,12400,2480,12400,34,12),
(55,2,26880,11200,4480,22400,35,2),
(56,2,31200,13000,5200,26000,36,7),
(57,1,24000,20000,4000,20000,36,6),
(58,2,9600,4000,1600,8000,37,11);

/*Table structure for table `stavkaporudzbine` */

DROP TABLE IF EXISTS `stavkaporudzbine`;

CREATE TABLE `stavkaporudzbine` (
  `rb` bigint(20) NOT NULL AUTO_INCREMENT,
  `kolicina` int(11) DEFAULT NULL,
  `iznos` double DEFAULT NULL,
  `cenaPoKomadu` double DEFAULT NULL,
  `pdv` double DEFAULT NULL,
  `iznosBezPdv` double DEFAULT NULL,
  `idPorudzbina` bigint(20) NOT NULL,
  `idProizvod` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rb`,`idPorudzbina`),
  KEY `idPorudzbina` (`idPorudzbina`),
  KEY `idProizvod` (`idProizvod`),
  CONSTRAINT `stavkaporudzbine_ibfk_1` FOREIGN KEY (`idPorudzbina`) REFERENCES `porudzbina` (`idPorudzbina`),
  CONSTRAINT `stavkaporudzbine_ibfk_2` FOREIGN KEY (`idProizvod`) REFERENCES `proizvod` (`idProizvod`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkaporudzbine` */

insert  into `stavkaporudzbine`(`rb`,`kolicina`,`iznos`,`cenaPoKomadu`,`pdv`,`iznosBezPdv`,`idPorudzbina`,`idProizvod`) values 
(15,3,32760,9100,5460,27300,13,14),
(16,2,18960,7900,3160,15800,13,3),
(17,4,19200,4000,3200,16000,14,11),
(18,2,9600,4000,1600,8000,15,11),
(19,1,10800,9000,1800,9000,15,1),
(20,2,29760,12400,4960,24800,16,12),
(21,1,13440,11200,2240,11200,16,2),
(22,2,26880,11200,4480,22400,17,2),
(23,1,13440,11200,2240,11200,18,2),
(24,2,31200,13000,5200,26000,19,7),
(25,1,24000,20000,4000,20000,19,6),
(26,2,9600,4000,1600,8000,20,11);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
