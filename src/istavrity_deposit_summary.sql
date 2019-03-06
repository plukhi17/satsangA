-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2019 at 03:00 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onlinesa`
--

-- --------------------------------------------------------

--
-- Table structure for table `istavrity_deposit_summary`
--

CREATE TABLE `istavrity_deposit_summary` (
  `SL_NO` int(11) NOT NULL,
  `SA_FAMILY_CODE` varchar(50) NOT NULL,
  `SA_MEM_CODE` varchar(50) NOT NULL,
  `IND_FAMILY_CODE` varchar(30) DEFAULT NULL,
  `MONTH_YEAR` varchar(20) NOT NULL,
  `SWASTYAYANI_AMT` double NOT NULL,
  `ISTAVRITY_AMT` double NOT NULL,
  `ACHARYAVRITY_AMT` double NOT NULL,
  `DAKSHINA_AMT` double NOT NULL,
  `SANGATHANI_AMT` double NOT NULL,
  `PRONAMI_AMT` double NOT NULL,
  `THAKUR_BHOG_AMT` double NOT NULL,
  `SWASTYAYANI_SURPLUS_AMT` double NOT NULL,
  `PARIVRITY_AMT` double NOT NULL,
  `RITWIKI_AMT` double NOT NULL,
  `TOTAL_AMT` double NOT NULL,
  `COLLECTED_ON` datetime DEFAULT CURRENT_TIMESTAMP,
  `SUBMITTED_ON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `APPROVED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `COLLECTED_BY` varchar(250) NOT NULL,
  `PAYMENT_METHOD` varchar(30) NOT NULL,
  `CHEQUE_NO` varchar(10) DEFAULT NULL,
  `CHEQUE_ISSUE_BANK` varchar(50) DEFAULT NULL,
  `CHEQUE_DATE` datetime DEFAULT NULL,
  `ARGHYA_PRASWASTI_ISSUE` varchar(10) NOT NULL,
  `ARGHYA_PRASWASTI_NO` varchar(30) NOT NULL,
  `ARGHYA_PRASWASTI_ISSUE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `istavrity_deposit_summary`
--
ALTER TABLE `istavrity_deposit_summary`
  ADD PRIMARY KEY (`SL_NO`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `istavrity_deposit_summary`
--
ALTER TABLE `istavrity_deposit_summary`
  MODIFY `SL_NO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
