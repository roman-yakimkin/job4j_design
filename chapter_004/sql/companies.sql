drop table if exists company, person;

create table company (
	id integer not null,
	name varchar,
	constraint company_pkey primary key(id)
);

create table person (
	id integer not null,
	name varchar,
	company_id integer references company(id),
	constraint person_pkey primary key(id)	
);

insert into company (id, name) values
	(1, 'Microsoft'),
	(2, 'Apple'),
	(3, 'Borland'),
	(4, 'JetBrains'),
	(5, 'Gazprom'),
	(6, 'The horns and hooves inc.');

insert into person (id, name, company_id) values 
	(1, 'Gates', 1),
	(2, 'Johnson', 3),
	(3, 'Peterson', 2),
	(4, 'Sidorov', 4),
	(5, 'Potapov', 5),
	(6, 'Smirnov', 5),
	(7, 'Balaganov', 6);