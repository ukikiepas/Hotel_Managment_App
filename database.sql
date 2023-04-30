-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 14 Cze 2022, 02:47
-- Wersja serwera: 10.4.24-MariaDB
-- Wersja PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Baza danych: `relaxmsdb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roomslist`
--

CREATE TABLE `roomslist` (
  `roomID` int(3) NOT NULL,
  `RoomNumber` int(4) NOT NULL,
  `price_PN` int(4) NOT NULL,
  `bedQuantity` int(2) NOT NULL DEFAULT 1,
  `Standard` varchar(10) NOT NULL COMMENT '0 - low standard\r\n1 - top standard',
  `Available` varchar(10) NOT NULL COMMENT '1 IF FREE\r\n2 IF TAKEN',
  `startDate` date NOT NULL,
  `finishDate` date NOT NULL,
  `hotelName` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `roomslist`
--

INSERT INTO `roomslist` (`roomID`, `RoomNumber`, `price_PN`, `bedQuantity`, `Standard`, `Available`, `startDate`, `finishDate`, `hotelName`) VALUES
(1, 1, 90, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(2, 2, 90, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(3, 3, 90, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(4, 4, 90, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(5, 5, 90, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(6, 6, 90, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(7, 7, 90, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(8, 8, 250, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(9, 9, 250, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(10, 10, 250, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(11, 11, 250, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(12, 12, 250, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'Spectra'),
(13, 13, 60, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(14, 14, 60, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(15, 15, 60, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(16, 16, 60, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(17, 17, 60, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(18, 18, 60, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(19, 19, 60, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(20, 20, 60, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(21, 21, 300, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(22, 22, 300, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(23, 23, 300, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(24, 24, 300, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'LJH'),
(25, 25, 50, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Colony'),
(26, 26, 50, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Colony'),
(27, 27, 50, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Colony'),
(28, 28, 50, 1, 'LOW', 'FREE', '3000-12-12', '3000-12-12', 'Colony'),
(29, 29, 150, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'Colony'),
(30, 30, 150, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'Colony'),
(31, 31, 150, 1, 'HIGH', 'FREE', '3000-12-12', '3000-12-12', 'Colony');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `roomslist`
--
ALTER TABLE `roomslist`
  ADD PRIMARY KEY (`roomID`),
  ADD UNIQUE KEY `RoomNumber` (`RoomNumber`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `roomslist`
--
ALTER TABLE `roomslist`
  MODIFY `roomID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=192;
COMMIT;