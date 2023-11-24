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
);

create table Equipment_info
(
    Equipment_id   int auto_increment
        primary key,
    Name varchar(20) not null,
    IsAvailable boolean not null
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
    Type varchar(20) not null,
);
