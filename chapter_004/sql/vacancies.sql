﻿DROP SCHEMA IF EXISTS post;

CREATE SCHEMA post;

CREATE TABLE post.vacancies (
	id serial primary key,
	name varchar(2000) not null,
	text text not null,
	link varchar(500) not null,
	created timestamp not null	
);