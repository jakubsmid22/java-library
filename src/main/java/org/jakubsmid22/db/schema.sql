create database library;

use library;

create table book(
	id int primary key auto_increment,
    title varchar(100) not null,
    author varchar(55) not null,
    year int not null,
    genre varchar(55) not null
);