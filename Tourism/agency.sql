create database if not exists agency;

drop table if exists history;
drop table if exists reservation;
drop table if exists `client`;
drop table if exists user;


create table if not exists `client`
(
id_client int not null unique auto_increment primary key,
name varchar(30) not null,
card_no varchar(2) not null,
cnp varchar(13) not null unique,
address varchar(20) not null,
phone varchar(10) not null,
email varchar(30)
);

create table if not exists user
(
id_user int not null unique auto_increment primary key,
username varchar(20) not null unique,
password varchar(20) not null,
name varchar(30) not null,
type enum('AGENT', 'ADMIN') not null
);

create table if not exists reservation
(
id_reserv int not null unique auto_increment primary key,
id_client int not null,
destination varchar(30) not null,
hotel varchar(20) not null,
no_persons int not null,
details varchar(100),
tot_price float not null,
final_payment_day  date not null,
canceled boolean not null DEFAULT false,
foreign key (id_client) references client(id_client)
);

drop table if exists history;
create table if not exists history
(
id_history int not null unique auto_increment primary key,
id_user int not null,
`change` varchar(20) not null,
`date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null, -- bc you allways insert current time in the field
foreign key (id_user) references `user`(id_user)
);



