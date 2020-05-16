drop table if exists cars, car_bodies, car_transmissions, car_engines;

create table car_bodies (
	id serial primary key,
	name varchar(2000) not null
); 

create table car_transmissions (
	id serial primary key,
	name varchar(2000) not null
);

create table car_engines (
	id serial primary key,
	name varchar(2000) not null
);

create table cars (
	id serial primary key,
	body_id int references car_bodies(id),
	transmission_id int references car_transmissions(id),
	engine_id int references car_engines(id),
	name varchar(2000) not null
);

insert into car_bodies (id, name) values 
	(1, 'Body 1'),
	(2, 'Body 2'),
	(3, 'Body 3'),
	(4, 'Body 4');

insert into car_transmissions (id, name) values 
	(1, 'Transmission 1'),
	(2, 'Transmission 2'),
	(3, 'Transmission 3'),
	(4, 'Transmission 4');

insert into car_engines (id, name) values 
	(1, 'Engine 1'),
	(2, 'Engine 2'),
	(3, 'Engine 3'),
	(4, 'Engine 4'),
	(5, 'Engine 5'),
	(6, 'Engine 6');

insert into cars (body_id, transmission_id, engine_id, name) values
	(1, 1, 1, 'Car 111'),
	(2, 2, 2, 'Car 222'),
	(3, 4, 4, 'Car 344'),
	(1, 2, 1, 'Car 121'),
	(1, 1, 2, 'Car 112');
	