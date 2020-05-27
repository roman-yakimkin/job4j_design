drop table if exists m_meetings, m_users, m_user_states, m_meet_users;

create table m_meetings (
	id serial primary key,
	name varchar(2000) not null
);

create table m_users (
	id serial primary key,
	name varchar(2000) not null
);

create table m_user_states (
	id serial primary key,
	name varchar(2000) not null
);

create table m_meet_users (
	meeting_id integer references m_meetings(id),
	user_id integer references m_users(id),
	state_id integer references m_user_states(id)
);

insert into m_meetings (id, name) values 
	(1, 'Meeting 1'), 
	(2, 'Meeting 2'), 
	(3, 'Meeting 3');

insert into m_users (id, name) values 
	(1, 'User 1'), 
	(2, 'User 2'), 
	(3, 'User 3'), 
	(4, 'User 4'), 
	(5, 'User 5'); 

insert into m_user_states (id, name) values 
	(1, 'Accepted'),
	(2, 'Rejected'),
	(3, 'Doubt');

insert into m_meet_users (meeting_id, user_id, state_id) values 
	(1, 1, 1),
	(1, 2, 2),
	(1, 3, 3),
	(1, 4, 1),
	(2, 1, 2),
	(2, 2, 1),
	(2, 3, 3),
	(2, 4, 1),
	(2, 5, 1);