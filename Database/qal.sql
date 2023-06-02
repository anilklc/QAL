-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 02 Haz 2023, 13:34:45
-- Sunucu sürümü: 10.4.25-MariaDB
-- PHP Sürümü: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `qal`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `admin`
--

CREATE TABLE `admin` (
  `adminID` int(11) NOT NULL,
  `adminName` varchar(25) NOT NULL,
  `adminSurname` varchar(25) NOT NULL,
  `adminUsername` varchar(25) NOT NULL,
  `adminPassword` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `admin`
--

INSERT INTO `admin` (`adminID`, `adminName`, `adminSurname`, `adminUsername`, `adminPassword`) VALUES
(1, 'test', 'test', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `announcement`
--

CREATE TABLE `announcement` (
  `announcementID` int(11) NOT NULL,
  `announcementName` varchar(25) NOT NULL,
  `announcementText` varchar(250) NOT NULL,
  `announcementUsername` varchar(25) NOT NULL,
  `announcementClass` varchar(3) DEFAULT NULL,
  `announcementDate` date NOT NULL,
  `announcementType` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `announcement`
--

INSERT INTO `announcement` (`announcementID`, `announcementName`, `announcementText`, `announcementUsername`, `announcementClass`, `announcementDate`, `announcementType`) VALUES
(11, 'test2', 'fefefe', 'admin', '9A', '2023-05-04', 0),
(18, 'testogrenci', 'grgr', 'admin', NULL, '2023-05-06', 1),
(19, 'test', 'fefe', 'admin', '9A', '2023-05-06', 2),
(23, 'adgerg', 'rgrgr', 'ogretmen', '9A', '2023-05-06', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `exam`
--

CREATE TABLE `exam` (
  `examID` int(11) NOT NULL,
  `examName` varchar(25) NOT NULL,
  `totalQuestion` int(11) NOT NULL,
  `teacherID` int(11) NOT NULL,
  `lessonID` int(11) NOT NULL,
  `class` varchar(3) DEFAULT NULL,
  `startDate` date NOT NULL,
  `finishDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `exam`
--

INSERT INTO `exam` (`examID`, `examName`, `totalQuestion`, `teacherID`, `lessonID`, `class`, `startDate`, `finishDate`) VALUES
(8, 'test', 3, 2, 1, '9A', '2023-05-10', '2023-06-01');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `lesson`
--

CREATE TABLE `lesson` (
  `lessonID` int(11) NOT NULL,
  `lessonName` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `lesson`
--

INSERT INTO `lesson` (`lessonID`, `lessonName`) VALUES
(1, 'Matematik'),
(2, 'Fizik'),
(19, 'Algoritma');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `privatequestion`
--

CREATE TABLE `privatequestion` (
  `questionID` int(11) NOT NULL,
  `questionName` varchar(25) NOT NULL,
  `question` varchar(200) NOT NULL,
  `answer` varchar(200) DEFAULT NULL,
  `studentClass` varchar(3) NOT NULL,
  `lessonName` varchar(25) NOT NULL,
  `studentName` varchar(50) NOT NULL,
  `teacherName` varchar(50) DEFAULT NULL,
  `questionDate` date NOT NULL,
  `answerDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `privatequestion`
--

INSERT INTO `privatequestion` (`questionID`, `questionName`, `question`, `answer`, `studentClass`, `lessonName`, `studentName`, `teacherName`, `questionDate`, `answerDate`) VALUES
(1, 'tst', '2+2', '4', '9A', 'Matematik', 'ogrenci1 ogrenci1', NULL, '2023-05-08', '2023-05-08');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `question`
--

CREATE TABLE `question` (
  `questionID` int(11) NOT NULL,
  `questionPath` varchar(200) NOT NULL,
  `questionAnswer` varchar(1) NOT NULL,
  `examID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `question`
--

INSERT INTO `question` (`questionID`, `questionPath`, `questionAnswer`, `examID`) VALUES
(1, '/question/test/soru1.png', 'C', 8),
(2, '/question/test/soru2.png', 'D', 8),
(3, '/question/test/soru3.png', 'C', 8);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `result`
--

CREATE TABLE `result` (
  `resultID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `examID` int(11) NOT NULL,
  `correctQuestion` int(11) NOT NULL,
  `wrongQuestion` int(11) NOT NULL,
  `point` int(11) NOT NULL,
  `loginDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `result`
--

INSERT INTO `result` (`resultID`, `studentID`, `examID`, `correctQuestion`, `wrongQuestion`, `point`, `loginDate`) VALUES
(1, 1, 8, 2, 1, 66, '2023-05-12'),
(2, 2, 9, 2, 0, 100, '2023-05-31');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `student`
--

CREATE TABLE `student` (
  `studentID` int(11) NOT NULL,
  `studentName` varchar(25) NOT NULL,
  `studentSurname` varchar(25) NOT NULL,
  `studentClass` varchar(3) NOT NULL,
  `studentUsername` varchar(25) NOT NULL,
  `studentPassword` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `student`
--

INSERT INTO `student` (`studentID`, `studentName`, `studentSurname`, `studentClass`, `studentUsername`, `studentPassword`) VALUES
(1, 'ogrenci1', 'ogrenci1', '9A', 'ogrenci', '12345');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `teacher`
--

CREATE TABLE `teacher` (
  `teacherID` int(11) NOT NULL,
  `teacherName` varchar(25) NOT NULL,
  `teacherSurname` varchar(25) NOT NULL,
  `teacherClass` varchar(3) DEFAULT NULL,
  `teacherLessonID` int(11) NOT NULL,
  `teacherUsername` varchar(25) NOT NULL,
  `teacherPassword` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `teacher`
--

INSERT INTO `teacher` (`teacherID`, `teacherName`, `teacherSurname`, `teacherClass`, `teacherLessonID`, `teacherUsername`, `teacherPassword`) VALUES
(2, 'teacher', 'teacher', '9A', 1, 'ogretmen', '12345');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`);

--
-- Tablo için indeksler `announcement`
--
ALTER TABLE `announcement`
  ADD PRIMARY KEY (`announcementID`);

--
-- Tablo için indeksler `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`examID`);

--
-- Tablo için indeksler `lesson`
--
ALTER TABLE `lesson`
  ADD PRIMARY KEY (`lessonID`);

--
-- Tablo için indeksler `privatequestion`
--
ALTER TABLE `privatequestion`
  ADD PRIMARY KEY (`questionID`);

--
-- Tablo için indeksler `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`questionID`);

--
-- Tablo için indeksler `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`resultID`);

--
-- Tablo için indeksler `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentID`);

--
-- Tablo için indeksler `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherID`),
  ADD KEY `teacherLessonID` (`teacherLessonID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `admin`
--
ALTER TABLE `admin`
  MODIFY `adminID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Tablo için AUTO_INCREMENT değeri `announcement`
--
ALTER TABLE `announcement`
  MODIFY `announcementID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Tablo için AUTO_INCREMENT değeri `exam`
--
ALTER TABLE `exam`
  MODIFY `examID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `lesson`
--
ALTER TABLE `lesson`
  MODIFY `lessonID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Tablo için AUTO_INCREMENT değeri `privatequestion`
--
ALTER TABLE `privatequestion`
  MODIFY `questionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `question`
--
ALTER TABLE `question`
  MODIFY `questionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `result`
--
ALTER TABLE `result`
  MODIFY `resultID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `student`
--
ALTER TABLE `student`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`teacherLessonID`) REFERENCES `lesson` (`lessonID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
