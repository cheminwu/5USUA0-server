create table user_info
(
    id   int auto_increment
        primary key,
    name varchar(20) not null,
    birthday varchar(20) not null,
    gender int not null
);

create table locker_info
(
    id   int auto_increment
        primary key,
    cabinet_id int not null,
    item varchar(20) not null
);



create table locker_user_map
(
    id   int auto_increment
        primary key,
    locker_id int not null,
    user_id int not null
);


