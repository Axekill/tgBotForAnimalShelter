-- liquibase formatted sql

-- changeset akostin:1
create table dog_shelter(
 id serial,
 nameShelter text,
 address text,
 workingHours time
)
-- changeset akostin:2
alter TABLE dog_shelter add primary key (id)

-- changeset akostin:3
create table shelter_for_cats(
    id serial,
    nameShelter text,
    address text,
    workingHours time
)
-- changeset akostin:4
alter TABLE shelter_for_cats add primary key (id)

-- changeset akostin:5
create table cats (
    id serial,
    name TEXT,
    age smallint,
    breed text
)
-- changeset akostin:6
alter TABLE cats add primary key (id)

-- changeset akostin:7
create table dogs (
    id serial primary key,
    name TEXT,
    age smallint,
    breed text
)
-- changeset akostin:8
create table volunteer (
     id serial primary key,
     name  TEXT,
     lastName text,
     email  text
)
--changeset Andrey:9
create table users(
id serial primary key,
fist_name text,
last_name text,
age int,
number_phone int,
email text,
address text,
adopted_cat int,
adopted_dog int
)
alter table cats add shelter_cats_id int
alter table cats add owner int
alter table dogs add shelter_dog_id int
alter table dogs add owner int
