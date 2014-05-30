-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 30, 2014 at 10:13 PM
-- Server version: 5.5.37
-- PHP Version: 5.4.4-14+deb7u9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kathford`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `fname`, `lname`, `password`) VALUES
(1, 'yubaraj', 'yub', 'yub');

-- --------------------------------------------------------

--
-- Table structure for table `directory`
--

CREATE TABLE IF NOT EXISTS `directory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `faculty` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `directory`
--

INSERT INTO `directory` (`id`, `fname`, `lname`, `faculty`, `phone`) VALUES
(1, 'yubaraj', 'poudel', 'physics', '9842583634'),
(2, 'dhurbe', 'purbe', 'jot', '98420');

-- --------------------------------------------------------

--
-- Table structure for table `download`
--

CREATE TABLE IF NOT EXISTS `download` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(50) NOT NULL,
  `faculty` varchar(50) NOT NULL,
  `semester` varchar(5) NOT NULL,
  `files` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `download`
--

INSERT INTO `download` (`id`, `category`, `faculty`, `semester`, `files`) VALUES
(18, 'IOE RESULT', 'B.E-Computer', 'I', '20131010_113711.jpg'),
(19, 'IOE RESULT', 'B.E-Computer', 'I', '20131010_113817.jpg'),
(20, 'IOE RESULT', 'B.E-Computer', 'I', '20131213_124511.jpg'),
(21, 'IOE RESULT', 'B.E-Computer', 'I', '20140225_113712.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `newsfeed`
--

CREATE TABLE IF NOT EXISTS `newsfeed` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `news` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `newsfeed`
--

INSERT INTO `newsfeed` (`id`, `news`, `date`) VALUES
(2, 'i love programming', '2014-05-25 02:52:45'),
(3, 'today is sunday', '2014-05-25 03:54:09'),
(4, 'sfkljsaajsdfkjsad', '2014-05-25 22:37:22'),
(5, 'college will open from asadh', '2014-05-26 16:26:35'),
(6, 'college will close tomarrow', '2014-05-27 09:51:53');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE IF NOT EXISTS `register` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `faculty` varchar(100) NOT NULL,
  `isactive` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `name`, `lname`, `password`, `faculty`, `isactive`, `created`) VALUES
(1, 'yubaraj', 'poudel', 'poudel', 'computer', 1, '2014-05-27 01:32:44'),
(2, 'prakash', 'prakash', 'p', 'BScCSIT', 1, '2014-05-26 16:26:58'),
(3, 'y', 'y', 'y', 'BBA', 1, '2014-05-26 16:27:54'),
(4, 'roshan', 'roshan', 'r', 'B.E-Computer', 1, '2014-05-27 09:51:23'),
(5, 'Rupak', 'Rupak', 'il0veunanu', 'B.E-Computer', 1, '2014-05-27 12:29:53'),
(7, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:04'),
(8, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:19'),
(9, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:30'),
(10, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:34'),
(11, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:41'),
(12, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:47'),
(13, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:50'),
(14, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:55'),
(15, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:07:59'),
(16, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:08:01'),
(17, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:08:04'),
(18, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:08:05'),
(19, 'Suman', 'Niroula', 'willbeer', 'B.E-Computer', 0, '2014-05-29 05:08:07');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `rno` varchar(5) NOT NULL,
  `course` varchar(20) NOT NULL,
  `semester` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `fname`, `lname`, `rno`, `course`, `semester`) VALUES
(2, 'pravakar', 'luitel', '12', 'B.E-Civil', 'VIII'),
(4, 'yubaraj', 'poudel', '46', 'B.E-Computer', 'II'),
(5, 'suraj', 'ghimire', '23', 'BScCSIT', 'I'),
(6, 'shiva', 'gairhe', '38', 'B.E-Computer', 'II'),
(7, 'roshan', 'pos', '32', 'B.E-Computer', 'II');

-- --------------------------------------------------------

--
-- Table structure for table `tblRegistration`
--

CREATE TABLE IF NOT EXISTS `tblRegistration` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `registration_id` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
