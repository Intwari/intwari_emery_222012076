-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 08:32 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `job_connect_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `applications`
--

CREATE TABLE `applications` (
  `application_id` int(11) NOT NULL,
  `job_seeker_id` int(11) DEFAULT NULL,
  `entrepreneur_id` int(11) DEFAULT NULL,
  `job_title` varchar(100) DEFAULT NULL,
  `proposal` text DEFAULT NULL,
  `labor_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `applications`
--

INSERT INTO `applications` (`application_id`, `job_seeker_id`, `entrepreneur_id`, `job_title`, `proposal`, `labor_name`) VALUES
(11, NULL, NULL, 'Marketing Manager', 'i want the job', 'Intwari emery'),
(12, NULL, NULL, 'ACCOUNTANT', 'i can do it', 'mugabo'),
(13, NULL, NULL, 'HR MANAGER', 'i need job', 'Ntare guy'),
(14, NULL, NULL, '5 Lectures', 'please i need the job', 'yvone');

-- --------------------------------------------------------

--
-- Table structure for table `entrepreneurs`
--

CREATE TABLE `entrepreneurs` (
  `entrepreneur_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `job_title` varchar(100) DEFAULT NULL,
  `job_description` text DEFAULT NULL,
  `job_salary` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `entrepreneurs`
--

INSERT INTO `entrepreneurs` (`entrepreneur_id`, `user_id`, `company_name`, `job_title`, `job_description`, `job_salary`) VALUES
(1, 2, 'Tech Solutions', 'Software Engineer', 'Seeking a skilled software engineer to join our team', 60000.00),
(2, 4, 'Marketing Co.', 'Marketing Manager', 'Looking for an experienced marketing manager to lead our team', 80000.00),
(3, NULL, 'MTN', 'CALL CENTER MANAGER', 'reach out to clients', 300000.00),
(4, NULL, 'BRD', 'HR MANAGER', 'human resourse management', 500000.00),
(5, NULL, 'BK', 'ACCOUNTANT', 'money man', 1000000.00),
(6, NULL, 'CIMERWA', 'Product Manager', 'product innovation', 100000.00),
(7, NULL, 'ABS Ltd', 'general director of ABS Ltd', 'coardinating and managing a company', 100000.00),
(8, NULL, 'Univertity of Rwanda', '5 Lectures', 'we want people who can teach', 500000.00);

-- --------------------------------------------------------

--
-- Table structure for table `job_seekers`
--

CREATE TABLE `job_seekers` (
  `job_seeker_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `experience` text DEFAULT NULL,
  `education` text DEFAULT NULL,
  `contact_information` text DEFAULT NULL,
  `languages` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `job_seekers`
--

INSERT INTO `job_seekers` (`job_seeker_id`, `user_id`, `experience`, `education`, `contact_information`, `languages`) VALUES
(2, 3, '5 years of marketing and sales experience', 'Bachelor\'s in Marketing', '0783333333', 'English, Spanish na kinyarwanda'),
(13, 17, 'Software Engineer with 5 years of experience in web development.', 'Bachelor\'s degree in Computer Science.', 'example1@domain.com, Phone: +1234567890.', 'English, Spanish'),
(14, 21, 'Marketing specialist with 3 years of experience in digital marketing.', 'Master\'s degree in Marketing.', 'example2@domain.com, Phone: +1987654321.', 'English, French, German'),
(15, 29, 'Graphic Designer with 7 years of experience in branding and illustration.', 'Bachelor\'s degree in Fine Arts.', 'example3@domain.com, Phone: +1122334455.', 'English');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `email`, `full_name`) VALUES
(2, 'jane_doe', 'password2', 'UR', 'UR'),
(3, 'jsmith', 'password3', 'jsmith@example.com', 'John Smith'),
(4, 'hagenimana', 'pass123', 'com', 'Billy'),
(17, 'Rugamba', '1234', 'rugamba@gmail.com', 'Rugamba'),
(21, 'intwari emery', '1212', 'intwari@gmail.com', 'intwari emery'),
(29, 'Gahunde dan', '9090', 'gahunde@gmail.com', 'Gahunde dan'),
(30, 'Ntare Guy', '1212', 'ntare@gmail.com', 'Ntare Guy'),
(31, 'jackson', '12345', 'j@gamail.com', 'jackson'),
(32, 'semugisha jeanpaul', 'Humtum@1', 'jeanpaulsemugisha@gmail.com', 'semugisha jeanpaul'),
(33, 'moshion', 'momo', 'moshion@gmail.com', 'moshion'),
(34, 'kk', '123', 'kk@gmail.com', 'kk'),
(35, 'niyogitangaza yvette', '1234567890', 'niyoyvette@gmail.com', 'niyogitangaza yvette');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`application_id`),
  ADD KEY `job_seeker_id` (`job_seeker_id`),
  ADD KEY `entrepreneur_id` (`entrepreneur_id`);

--
-- Indexes for table `entrepreneurs`
--
ALTER TABLE `entrepreneurs`
  ADD PRIMARY KEY (`entrepreneur_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `job_seekers`
--
ALTER TABLE `job_seekers`
  ADD PRIMARY KEY (`job_seeker_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `applications`
--
ALTER TABLE `applications`
  MODIFY `application_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `entrepreneurs`
--
ALTER TABLE `entrepreneurs`
  MODIFY `entrepreneur_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `job_seekers`
--
ALTER TABLE `job_seekers`
  MODIFY `job_seeker_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `applications`
--
ALTER TABLE `applications`
  ADD CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`job_seeker_id`) REFERENCES `job_seekers` (`job_seeker_id`),
  ADD CONSTRAINT `applications_ibfk_2` FOREIGN KEY (`entrepreneur_id`) REFERENCES `entrepreneurs` (`entrepreneur_id`);

--
-- Constraints for table `entrepreneurs`
--
ALTER TABLE `entrepreneurs`
  ADD CONSTRAINT `entrepreneurs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `job_seekers`
--
ALTER TABLE `job_seekers`
  ADD CONSTRAINT `job_seekers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
