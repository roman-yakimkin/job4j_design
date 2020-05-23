drop table if exists items;

create table items (
   id varchar(200) primary key,
   name varchar(2000) not null
);