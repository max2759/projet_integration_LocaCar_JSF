-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 03 août 2020 à 12:48
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_bac_info2`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresses`
--

DROP TABLE IF EXISTS `adresses`;
CREATE TABLE IF NOT EXISTS `adresses` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Cities` int(11) UNSIGNED NOT NULL,
  `ID_Users` int(11) UNSIGNED NOT NULL,
  `Street` varchar(255) COLLATE utf8_bin NOT NULL,
  `Number` int(11) UNSIGNED NOT NULL,
  `Box` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Cities` (`ID_Cities`),
  KEY `ID_Users` (`ID_Users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `ads`
--

DROP TABLE IF EXISTS `ads`;
CREATE TABLE IF NOT EXISTS `ads` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Cars` int(11) UNSIGNED NOT NULL,
  `Price` double UNSIGNED NOT NULL,
  `Date_Start` date NOT NULL,
  `Date_End` date NOT NULL,
  `Types_Ads` enum('VENTE','LOCATIONLD','LOCATIONCD','LEASING') COLLATE utf8_bin NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Cars` (`ID_Cars`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `ads`
--

INSERT INTO `ads` (`ID`, `ID_Cars`, `Price`, `Date_Start`, `Date_End`, `Types_Ads`, `Label`, `IsActive`) VALUES
(5, 5, 22990, '2020-06-30', '2020-10-31', 'LOCATIONCD', 'Location', 1),
(6, 6, 23755, '2020-07-31', '2020-09-30', 'VENTE', 'Vente', 1),
(7, 4, 17500.99, '2020-08-01', '2020-09-30', 'VENTE', 'Vente', 1);

-- --------------------------------------------------------

--
-- Structure de la table `brands`
--

DROP TABLE IF EXISTS `brands`;
CREATE TABLE IF NOT EXISTS `brands` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `brands`
--

INSERT INTO `brands` (`ID`, `Label`) VALUES
(1, 'Toyota'),
(2, 'Mercedes');

-- --------------------------------------------------------

--
-- Structure de la table `cars`
--

DROP TABLE IF EXISTS `cars`;
CREATE TABLE IF NOT EXISTS `cars` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Models` int(11) UNSIGNED NOT NULL,
  `Id_Car_Types` int(11) UNSIGNED NOT NULL,
  `Color` varchar(255) COLLATE utf8_bin NOT NULL,
  `Release_Year` date NOT NULL,
  `Kilometer` int(11) UNSIGNED NOT NULL,
  `HorsePower` int(11) UNSIGNED NOT NULL,
  `Fuel` enum('GAZ','ELECTRIQUE','DIESEL','ESSENCE','HYBRID','PIHYBRID') COLLATE utf8_bin NOT NULL,
  `Picture` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Models` (`ID_Models`),
  KEY `Id_Car_Types` (`Id_Car_Types`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `cars`
--

INSERT INTO `cars` (`ID`, `ID_Models`, `Id_Car_Types`, `Color`, `Release_Year`, `Kilometer`, `HorsePower`, `Fuel`, `Picture`, `isActive`) VALUES
(4, 2, 1, 'White', '2018-10-17', 14444, 66, 'HYBRID', NULL, 1),
(5, 4, 2, 'Black', '2019-12-31', 1345, 155, 'ELECTRIQUE', NULL, 1),
(6, 2, 1, 'Yellow', '2020-01-21', 1000, 100, 'DIESEL', NULL, 1),
(7, 4, 2, 'Black', '2019-12-31', 2111, 136, 'ESSENCE', NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `car_types`
--

DROP TABLE IF EXISTS `car_types`;
CREATE TABLE IF NOT EXISTS `car_types` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `car_types`
--

INSERT INTO `car_types` (`ID`, `Label`) VALUES
(1, 'Berline'),
(2, 'Break');

-- --------------------------------------------------------

--
-- Structure de la table `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Countries` int(11) UNSIGNED NOT NULL,
  `Region` varchar(255) COLLATE utf8_bin NOT NULL,
  `Postal_Code` int(11) UNSIGNED NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Countries` (`ID_Countries`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `contracts`
--

DROP TABLE IF EXISTS `contracts`;
CREATE TABLE IF NOT EXISTS `contracts` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Orders` int(11) UNSIGNED NOT NULL,
  `ID_Cars` int(11) UNSIGNED NOT NULL,
  `ID_Contract_Types` int(11) UNSIGNED NOT NULL,
  `Date_Start` date NOT NULL,
  `Date_End` date NOT NULL,
  `Final_Price` double UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Orders` (`ID_Orders`),
  KEY `ID_Cars` (`ID_Cars`),
  KEY `ID_Type_Contract` (`ID_Contract_Types`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `contract_insurances`
--

DROP TABLE IF EXISTS `contract_insurances`;
CREATE TABLE IF NOT EXISTS `contract_insurances` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Insurance` int(11) UNSIGNED NOT NULL,
  `ID_Contract` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Insurance` (`ID_Insurance`),
  KEY `ID_Contract` (`ID_Contract`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `contract_types`
--

DROP TABLE IF EXISTS `contract_types`;
CREATE TABLE IF NOT EXISTS `contract_types` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `countries`
--

DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `Label` (`Label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `insurances`
--

DROP TABLE IF EXISTS `insurances`;
CREATE TABLE IF NOT EXISTS `insurances` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  `Description` varchar(255) COLLATE utf8_bin NOT NULL,
  `Price` double UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
CREATE TABLE IF NOT EXISTS `invoices` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Orders` int(11) UNSIGNED NOT NULL,
  `Invoice_Date` date NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Orders` (`ID_Orders`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `models`
--

DROP TABLE IF EXISTS `models`;
CREATE TABLE IF NOT EXISTS `models` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Brands` int(11) UNSIGNED NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Brands` (`ID_Brands`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `models`
--

INSERT INTO `models` (`ID`, `ID_Brands`, `Label`) VALUES
(1, 1, 'Rav4'),
(2, 2, 'A'),
(3, 1, 'Prius'),
(4, 2, 'C');

-- --------------------------------------------------------

--
-- Structure de la table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Users` int(11) UNSIGNED NOT NULL,
  `Order_Date` date NOT NULL,
  `Order_Statut` enum('PENDING','VALIDATED','CANCELED') COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Users` (`ID_Users`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE IF NOT EXISTS `permissions` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `roles_permissions`
--

DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE IF NOT EXISTS `roles_permissions` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Roles` int(11) UNSIGNED NOT NULL,
  `ID_Permissions` int(11) UNSIGNED NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Roles` (`ID_Roles`),
  KEY `ID_Permissions` (`ID_Permissions`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
CREATE TABLE IF NOT EXISTS `subscriptions` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  `Price` double NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Roles` int(11) UNSIGNED NOT NULL,
  `Surname` varchar(255) COLLATE utf8_bin NOT NULL,
  `Name` varchar(255) COLLATE utf8_bin NOT NULL,
  `Username` varchar(255) COLLATE utf8_bin NOT NULL,
  `Password` varchar(255) COLLATE utf8_bin NOT NULL,
  `Date_Birth` date NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT 1,
  `VATNumber` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Username` (`Username`),
  UNIQUE KEY `ID` (`ID`),
  UNIQUE KEY `VATNumber` (`VATNumber`),
  KEY `ID_Roles` (`ID_Roles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `users_ads`
--

DROP TABLE IF EXISTS `users_ads`;
CREATE TABLE IF NOT EXISTS `users_ads` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Users` int(11) UNSIGNED NOT NULL,
  `ID_Ads` int(11) UNSIGNED NOT NULL,
  `IsFavorite` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Users` (`ID_Users`),
  KEY `ID_Ads` (`ID_Ads`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `users_subscriptions`
--

DROP TABLE IF EXISTS `users_subscriptions`;
CREATE TABLE IF NOT EXISTS `users_subscriptions` (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Users` int(11) UNSIGNED NOT NULL,
  `ID_Subscriptions` int(11) UNSIGNED NOT NULL,
  `Final_Price` double NOT NULL,
  `Start_Date` date NOT NULL,
  `End_Date` date NOT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `ID_Users` (`ID_Users`),
  KEY `ID_Subscriptions` (`ID_Subscriptions`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adresses`
--
ALTER TABLE `adresses`
  ADD CONSTRAINT `adresses_ibfk_1` FOREIGN KEY (`ID_Cities`) REFERENCES `cities` (`ID`),
  ADD CONSTRAINT `adresses_ibfk_2` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);

--
-- Contraintes pour la table `ads`
--
ALTER TABLE `ads`
  ADD CONSTRAINT `ads_ibfk_1` FOREIGN KEY (`ID_Cars`) REFERENCES `cars` (`ID`);

--
-- Contraintes pour la table `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`Id_Car_Types`) REFERENCES `car_types` (`ID`),
  ADD CONSTRAINT `cars_ibfk_2` FOREIGN KEY (`ID_Models`) REFERENCES `models` (`ID`);

--
-- Contraintes pour la table `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`ID_Countries`) REFERENCES `countries` (`ID`);

--
-- Contraintes pour la table `contracts`
--
ALTER TABLE `contracts`
  ADD CONSTRAINT `contracts_ibfk_1` FOREIGN KEY (`ID_Cars`) REFERENCES `cars` (`ID`),
  ADD CONSTRAINT `contracts_ibfk_2` FOREIGN KEY (`ID_Orders`) REFERENCES `orders` (`ID`),
  ADD CONSTRAINT `contracts_ibfk_3` FOREIGN KEY (`ID_Contract_Types`) REFERENCES `contract_types` (`ID`);

--
-- Contraintes pour la table `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `invoices_ibfk_1` FOREIGN KEY (`ID_Orders`) REFERENCES `orders` (`ID`);

--
-- Contraintes pour la table `models`
--
ALTER TABLE `models`
  ADD CONSTRAINT `models_ibfk_1` FOREIGN KEY (`ID_Brands`) REFERENCES `brands` (`ID`);

--
-- Contraintes pour la table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);

--
-- Contraintes pour la table `roles_permissions`
--
ALTER TABLE `roles_permissions`
  ADD CONSTRAINT `roles_permissions_ibfk_1` FOREIGN KEY (`ID_Roles`) REFERENCES `roles` (`ID`),
  ADD CONSTRAINT `roles_permissions_ibfk_2` FOREIGN KEY (`ID_Permissions`) REFERENCES `permissions` (`ID`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`ID_Roles`) REFERENCES `roles` (`ID`);

--
-- Contraintes pour la table `users_subscriptions`
--
ALTER TABLE `users_subscriptions`
  ADD CONSTRAINT `users_subscriptions_ibfk_1` FOREIGN KEY (`ID_Subscriptions`) REFERENCES `subscriptions` (`ID`),
  ADD CONSTRAINT `users_subscriptions_ibfk_2` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
