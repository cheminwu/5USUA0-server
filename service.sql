create table user_info
(
    User_id   int auto_increment
        primary key,
    First_name varchar(20) not null,
    Last_Name varchar(20) not null,
    Email varchar(50) not null,
);

create table locker_info
(
    Locker_id   int auto_increment
        primary key,
    Location varchar(20) not null,
    Status boolean not null
    Transaction_id int unique 
    Foreign key (Transaction_id) references Transactions(Transaction_id)
);

create table Equipment_info
(
    Equipment_id   int auto_increment
        primary key,
    Name varchar(20) not null,
    IsAvailable boolean not null
    Transaction_id int unique 
    Foreign key (Transaction_id) references Transactions(Transaction_id)
    Foreign key (Equipment_id) references Equipment_info(Equipment_id) 
    Foreign key (Sport_id) references Sports(Sport_id) 
);

create table Sports
(
    Sport_id  int auto_increment
        primary key,
    Description varchar(50) not null,
);

create table Transactions
(
    Transaction_id    int auto_increment
        primary key,
    Date_Time int not null,
    Type varchar(20) not null 
    Foreign key (User_id) references user_info(User_id) 
);


//TO MAKE DIAGRAM IN https://dbdiagram.io/ USE THE CODE BELOW
*/
Table "user_info" {
  "User_id" int [pk, increment]
  "First_name" varchar(20) [not null]
  "Last_Name" varchar(20) [not null]
  "Email" varchar(50) [not null]
}

Table "locker_info" {
  "Locker_id" int [pk, increment]
  "Location" varchar(20) [not null]
  "Status" boolean [not null]
}

Table "Equipment_info" {
  "Equipment_id" int [pk, increment]
  "Name" varchar(20) [not null]
  "IsAvailable" boolean [not null]
}

Table "Sports" {
  "Sport_id" int [pk, increment]
  "Description" varchar(50) [not null]
}

Table "Transactions" {
  "Transaction_id" int [pk, increment]
  "Date_Time" int [not null]
  "Type" varchar(20) [not null]
}

Ref: Transactions.Transaction_id > user_info.User_id 
Ref: Transactions.Transaction_id - locker_info.Locker_id
Ref: Transactions.Transaction_id - Equipment_info.Equipment_id
Ref: locker_info.Locker_id < Equipment_info.Equipment_id
Ref: Equipment_info.Equipment_id > Sports.Sport_id
/*
