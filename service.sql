CREATE TABLE `user_info` (
  `User_id` int PRIMARY KEY AUTO_INCREMENT,
  `First_name` varchar(20) NOT NULL,
  `Last_Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Serial_number` varchar(20) NOT NULL,
  `password_SHA` varchar(200) NOT NULL
);

CREATE TABLE `locker_info` (
  `Locker_id` int PRIMARY KEY AUTO_INCREMENT,
  `Location` varchar(20) NOT NULL,
  `Status` boolean NOT NULL
);

CREATE TABLE `Equipment_info` (
  `Equipment_id` int PRIMARY KEY AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `IsAvailable` boolean NOT NULL,
  `Locker_id` int,
  `Sport_id` int
);

CREATE TABLE `Sports` (
  `Sport_id` int PRIMARY KEY AUTO_INCREMENT,
  `Description` varchar(50) NOT NULL
);

CREATE TABLE `Transactions` (
  `Transaction_id` int PRIMARY KEY AUTO_INCREMENT,
  `Date_Time` DATETIME NOT NULL,
  `Type` varchar(20) NOT NULL,
  `User_id` int,
  `Equipment_id` int,
  `Locker_id` int
);

ALTER TABLE `Transactions` ADD FOREIGN KEY (`User_id`) REFERENCES `user_info` (`User_id`);

ALTER TABLE `Transactions` ADD FOREIGN KEY (`Locker_id`) REFERENCES `locker_info` (`Locker_id`);

ALTER TABLE `Transactions` ADD FOREIGN KEY (`Equipment_id`) REFERENCES `Equipment_info` (`Equipment_id`);

ALTER TABLE `Equipment_info` ADD FOREIGN KEY (`Locker_id`) REFERENCES `locker_info` (`Locker_id`);

ALTER TABLE `Equipment_info` ADD FOREIGN KEY (`Sport_id`) REFERENCES `Sports` (`Sport_id`);

