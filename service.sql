CREATE TABLE `user_info` (
  `User_id` int PRIMARY KEY AUTO_INCREMENT,
  `First_name` varchar(20) NOT NULL,
  `Last_Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL
);

CREATE TABLE `locker_info` (
  `Locker_id` int PRIMARY KEY AUTO_INCREMENT,
  `Location` varchar(20) NOT NULL,
  `Status` boolean NOT NULL
);

CREATE TABLE `Equipment_info` (
  `Equipment_id` int PRIMARY KEY AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `IsAvailable` boolean NOT NULL
);

CREATE TABLE `Sports` (
  `Sport_id` int PRIMARY KEY AUTO_INCREMENT,
  `Description` varchar(50) NOT NULL
);

CREATE TABLE `Transactions` (
  `Transaction_id` int PRIMARY KEY AUTO_INCREMENT,
  `Date_Time` int NOT NULL,
  `Type` varchar(20) NOT NULL
);

ALTER TABLE `Transactions` ADD FOREIGN KEY (`Transaction_id`) REFERENCES `user_info` (`User_id`);

ALTER TABLE `locker_info` ADD FOREIGN KEY (`Locker_id`) REFERENCES `Transactions` (`Transaction_id`);

ALTER TABLE `Equipment_info` ADD FOREIGN KEY (`Equipment_id`) REFERENCES `Transactions` (`Transaction_id`);

ALTER TABLE `Equipment_info` ADD FOREIGN KEY (`Equipment_id`) REFERENCES `locker_info` (`Locker_id`);

ALTER TABLE `Equipment_info` ADD FOREIGN KEY (`Equipment_id`) REFERENCES `Sports` (`Sport_id`);

