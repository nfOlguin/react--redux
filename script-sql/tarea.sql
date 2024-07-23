-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql-server
-- Generation Time: jul 6, 2024 at 07:53 AM
-- Server version: 8.0.19
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `task_coopeuch`
--

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `tarea` (
  `identificador` bigint NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `vigente` BOOLEAN NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `task`
--

INSERT INTO `tarea` (`identificador`, `descripcion`, `fecha_creacion`, `vigente`) VALUES
(1, 'crear backend con spring', '2020-10-16 02:33:46', TRUE),
(2, 'crear frontend con react', '2020-10-16 02:34:02', TRUE),
(3, 'modificar lista de tareas', '2020-10-16 02:34:58', FALSE);

--
-- Indexes for table `task`
--
ALTER TABLE `tarea`
  ADD PRIMARY KEY (`identificador`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `tarea`
  MODIFY `identificador` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
