delete from items;

alter table items add column ids serial;
alter table items drop column id;
alter table items rename column ids to id;