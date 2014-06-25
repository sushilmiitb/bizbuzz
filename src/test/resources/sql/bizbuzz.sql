-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 25, 2014 at 10:20 AM
-- Server version: 5.6.17-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bizbuzz`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `address_type` int(11) DEFAULT NULL,
  `attendant_name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `landmark` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `state_geo_id` varchar(255) DEFAULT NULL,
  `party_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ru96g8lbys2lxyrpldylw9k03` (`party_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `category_property_metadata`
--

CREATE TABLE IF NOT EXISTS `category_property_metadata` (
  `category_id` bigint(20) NOT NULL,
  `metadata_id` bigint(20) NOT NULL,
  KEY `FK_gxqmy0m3hlqi99cyc0v7k7qn8` (`metadata_id`),
  KEY `FK_or2h12a7jihol4gh2owy1by1r` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category_tree`
--

CREATE TABLE IF NOT EXISTS `category_tree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `parent_category` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7jnjmh3pfdrd0jm1rwlubkx3r` (`parent_category`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE IF NOT EXISTS `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `chat_room_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m34p2qatj2b5o0bnc4obowq3k` (`chat_room_id`),
  KEY `FK_s4h77hvdvrx91vnf9mpw60gwt` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `chatroom_recipeint`
--

CREATE TABLE IF NOT EXISTS `chatroom_recipeint` (
  `chatroom_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  KEY `FK_gdrx4m02x5ai5c84n2r8hc4cu` (`recipient_id`),
  KEY `FK_8sr1xquxnljdefg2xgspy1f8u` (`chatroom_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `chat_room`
--

CREATE TABLE IF NOT EXISTS `chat_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sender_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_erpdpphwlqhp25i963002orn0` (`sender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `company_name` varchar(255) DEFAULT NULL,
  `company_registration_type` int(11) DEFAULT NULL,
  `company_role` varchar(255) DEFAULT NULL,
  `registration_id` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`company_name`, `company_registration_type`, `company_role`, `registration_id`, `id`) VALUES
('bizbuzz', NULL, 'Buyer', NULL, 1),
('bizbuzz', NULL, 'Seller', NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `connection`
--

CREATE TABLE IF NOT EXISTS `connection` (
  `from_party_id` bigint(20) NOT NULL,
  `to_party_id` bigint(20) NOT NULL,
  `connection_type` int(11) DEFAULT NULL,
  `from_party` bigint(20) DEFAULT NULL,
  `to_party` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`from_party_id`,`to_party_id`),
  KEY `FK_j5aqdw2mo39gs631uw4iig12k` (`from_party`),
  KEY `FK_e5s459fuc701b22mv0bjofk42` (`to_party`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `connection`
--

INSERT INTO `connection` (`from_party_id`, `to_party_id`, `connection_type`, `from_party`, `to_party`) VALUES
(1, 2, 1, 1, 2),
(3, 4, 1, 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_number` varchar(255) DEFAULT NULL,
  `style_number` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o4nr676s6g7eokrjsmt7ujs2p` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `item_property_value`
--

CREATE TABLE IF NOT EXISTS `item_property_value` (
  `item_id` bigint(20) NOT NULL,
  `property_metadata_id` bigint(20) NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `item` bigint(20) DEFAULT NULL,
  `property_metadata` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`,`property_metadata_id`),
  KEY `FK_49mbyxhrrf0rcns4dca6kdarf` (`item`),
  KEY `FK_4bcr7qemgct8m9ao9hs25wd5v` (`property_metadata`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `party`
--

CREATE TABLE IF NOT EXISTS `party` (
  `party_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `category_root` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_77s5qglac2e3y9krak4ko6t6x` (`category_root`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `party`
--

INSERT INTO `party` (`party_type`, `id`, `email`, `category_root`) VALUES
('company', 1, NULL, NULL),
('person', 2, 'sushil@gmail.com', NULL),
('company', 3, NULL, NULL),
('person', 4, 'sushil@gmail.com', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `first_name` varchar(255) DEFAULT NULL,
  `gender` char(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `person_role` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_77e12pjkm9v423j9hd3u10bk1` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`first_name`, `gender`, `last_name`, `middle_name`, `person_role`, `id`, `user_id`) VALUES
('sushil', '\0', 'sushil', 'sushil', NULL, 2, 'sushil'),
('virender', '\0', 'virender', '', NULL, 4, 'virender');

-- --------------------------------------------------------

--
-- Table structure for table `phone_number`
--

CREATE TABLE IF NOT EXISTS `phone_number` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `area_code` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `phone_type` int(11) DEFAULT NULL,
  `party_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rbivueliuv4a4y06jtd1bj5bg` (`party_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `phone_number`
--

INSERT INTO `phone_number` (`id`, `area_code`, `contact_number`, `country_code`, `phone_type`, `party_id`) VALUES
(1, NULL, '9099043920', NULL, NULL, NULL),
(2, NULL, '9099043920', NULL, NULL, NULL),
(3, NULL, '9099043920', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `private_group`
--

CREATE TABLE IF NOT EXISTS `private_group` (
  `private_group_name` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `property_metadata`
--

CREATE TABLE IF NOT EXISTS `property_metadata` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `possible_units` varchar(255) DEFAULT NULL,
  `possible_values` varchar(255) DEFAULT NULL,
  `property_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m8s4l6jec183uqgvpbkg80ijd` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `property_meta_data_grouping`
--

CREATE TABLE IF NOT EXISTS `property_meta_data_grouping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grouping_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3q5rym38a7j1dihkbsj6go8gr` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `security_group`
--

CREATE TABLE IF NOT EXISTS `security_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `security_group`
--

INSERT INTO `security_group` (`id`, `name`) VALUES
(1, 'buyer'),
(2, 'seller');

-- --------------------------------------------------------

--
-- Table structure for table `security_group_authority`
--

CREATE TABLE IF NOT EXISTS `security_group_authority` (
  `security_group_id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_7w5h2tfsle4ssws4c7qidv1ck` (`security_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `security_group_authority`
--

INSERT INTO `security_group_authority` (`security_group_id`, `authority`) VALUES
(1, 'ROLE_BUYER'),
(2, 'ROLE_SELLER');

-- --------------------------------------------------------

--
-- Table structure for table `share`
--

CREATE TABLE IF NOT EXISTS `share` (
  `party_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  KEY `FK_isdr89ke03u76ylkft81757b0` (`item_id`),
  KEY `FK_jtkro6er11qi15ou1wy20hd4i` (`party_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_login`
--

CREATE TABLE IF NOT EXISTS `user_login` (
  `id` varchar(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_login`
--

INSERT INTO `user_login` (`id`, `created_at`, `enabled`, `password_hash`, `updated_at`) VALUES
('sushil', '2014-06-25 09:15:19', b'0', 'sushil', '2014-06-25 09:15:19'),
('virender', '2014-06-25 09:18:17', b'0', 'virender', '2014-06-25 09:18:17');

-- --------------------------------------------------------

--
-- Table structure for table `user_login_security_group`
--

CREATE TABLE IF NOT EXISTS `user_login_security_group` (
  `user_login_id` varchar(20) NOT NULL,
  `security_group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_login_id`,`security_group_id`),
  KEY `FK_djxf4uqprp0syy6pcfp7srtmv` (`security_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_login_security_group`
--

INSERT INTO `user_login_security_group` (`user_login_id`, `security_group_id`) VALUES
('sushil', 1),
('virender', 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FK_ru96g8lbys2lxyrpldylw9k03` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`);

--
-- Constraints for table `category_property_metadata`
--
ALTER TABLE `category_property_metadata`
  ADD CONSTRAINT `FK_or2h12a7jihol4gh2owy1by1r` FOREIGN KEY (`category_id`) REFERENCES `category_tree` (`id`),
  ADD CONSTRAINT `FK_gxqmy0m3hlqi99cyc0v7k7qn8` FOREIGN KEY (`metadata_id`) REFERENCES `property_metadata` (`id`);

--
-- Constraints for table `category_tree`
--
ALTER TABLE `category_tree`
  ADD CONSTRAINT `FK_7jnjmh3pfdrd0jm1rwlubkx3r` FOREIGN KEY (`parent_category`) REFERENCES `category_tree` (`id`);

--
-- Constraints for table `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `FK_s4h77hvdvrx91vnf9mpw60gwt` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  ADD CONSTRAINT `FK_m34p2qatj2b5o0bnc4obowq3k` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`);

--
-- Constraints for table `chatroom_recipeint`
--
ALTER TABLE `chatroom_recipeint`
  ADD CONSTRAINT `FK_8sr1xquxnljdefg2xgspy1f8u` FOREIGN KEY (`chatroom_id`) REFERENCES `chat_room` (`id`),
  ADD CONSTRAINT `FK_gdrx4m02x5ai5c84n2r8hc4cu` FOREIGN KEY (`recipient_id`) REFERENCES `party` (`id`);

--
-- Constraints for table `chat_room`
--
ALTER TABLE `chat_room`
  ADD CONSTRAINT `FK_erpdpphwlqhp25i963002orn0` FOREIGN KEY (`sender_id`) REFERENCES `party` (`id`);

--
-- Constraints for table `company`
--
ALTER TABLE `company`
  ADD CONSTRAINT `FK_qdo38t35lgbgluwtbfej64u3d` FOREIGN KEY (`id`) REFERENCES `party` (`id`);

--
-- Constraints for table `connection`
--
ALTER TABLE `connection`
  ADD CONSTRAINT `FK_e5s459fuc701b22mv0bjofk42` FOREIGN KEY (`to_party`) REFERENCES `party` (`id`),
  ADD CONSTRAINT `FK_j5aqdw2mo39gs631uw4iig12k` FOREIGN KEY (`from_party`) REFERENCES `party` (`id`);

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FK_o4nr676s6g7eokrjsmt7ujs2p` FOREIGN KEY (`category_id`) REFERENCES `category_tree` (`id`);

--
-- Constraints for table `item_property_value`
--
ALTER TABLE `item_property_value`
  ADD CONSTRAINT `FK_4bcr7qemgct8m9ao9hs25wd5v` FOREIGN KEY (`property_metadata`) REFERENCES `property_metadata` (`id`),
  ADD CONSTRAINT `FK_49mbyxhrrf0rcns4dca6kdarf` FOREIGN KEY (`item`) REFERENCES `item` (`id`);

--
-- Constraints for table `party`
--
ALTER TABLE `party`
  ADD CONSTRAINT `FK_77s5qglac2e3y9krak4ko6t6x` FOREIGN KEY (`category_root`) REFERENCES `category_tree` (`id`);

--
-- Constraints for table `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `FK_4ht90pj9rj91hj3dqhx5c40ut` FOREIGN KEY (`id`) REFERENCES `party` (`id`),
  ADD CONSTRAINT `FK_77e12pjkm9v423j9hd3u10bk1` FOREIGN KEY (`user_id`) REFERENCES `user_login` (`id`);

--
-- Constraints for table `phone_number`
--
ALTER TABLE `phone_number`
  ADD CONSTRAINT `FK_rbivueliuv4a4y06jtd1bj5bg` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`);

--
-- Constraints for table `private_group`
--
ALTER TABLE `private_group`
  ADD CONSTRAINT `FK_itvdm2cutkt2odrmi25pl48gk` FOREIGN KEY (`id`) REFERENCES `party` (`id`);

--
-- Constraints for table `property_metadata`
--
ALTER TABLE `property_metadata`
  ADD CONSTRAINT `FK_m8s4l6jec183uqgvpbkg80ijd` FOREIGN KEY (`parent_id`) REFERENCES `property_meta_data_grouping` (`id`);

--
-- Constraints for table `property_meta_data_grouping`
--
ALTER TABLE `property_meta_data_grouping`
  ADD CONSTRAINT `FK_3q5rym38a7j1dihkbsj6go8gr` FOREIGN KEY (`parent_id`) REFERENCES `property_meta_data_grouping` (`id`);

--
-- Constraints for table `security_group_authority`
--
ALTER TABLE `security_group_authority`
  ADD CONSTRAINT `FK_7w5h2tfsle4ssws4c7qidv1ck` FOREIGN KEY (`security_group_id`) REFERENCES `security_group` (`id`);

--
-- Constraints for table `share`
--
ALTER TABLE `share`
  ADD CONSTRAINT `FK_jtkro6er11qi15ou1wy20hd4i` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`),
  ADD CONSTRAINT `FK_isdr89ke03u76ylkft81757b0` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);

--
-- Constraints for table `user_login_security_group`
--
ALTER TABLE `user_login_security_group`
  ADD CONSTRAINT `FK_tokikorfgavfw110i0dabt5wy` FOREIGN KEY (`user_login_id`) REFERENCES `user_login` (`id`),
  ADD CONSTRAINT `FK_djxf4uqprp0syy6pcfp7srtmv` FOREIGN KEY (`security_group_id`) REFERENCES `security_group` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
