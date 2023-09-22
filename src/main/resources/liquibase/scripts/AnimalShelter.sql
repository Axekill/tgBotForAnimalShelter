-- liquibase formatted sql

-- changeset akostin:1
CREATE TABLE dog_shelter(
 id SERIAL,
 nameShelter TEXT,
 address TEXT,
 workingHours TIME
)
-- changeset akostin:2
ALTER TABLE dog_shelter add primary key (id)

-- changeset akostin:3
CREATE TABLE shelter_for_cats(
    id SERIAL,
    nameShelter TEXT,
    address TEXT,
    workingHours TIME
)
-- changeset akostin:4
ALTER TABLE shelter_for_cats add primary key (id)

-- changeset akostin:5
CREATE TABLE cats (
    id SERIAL,
    name TEXT,
    age smallint,
    breed TEXT
)
-- changeset akostin:6
AlTER TABLE cats add primary key (id)

-- changeset akostin:7
CREATE TABLE dogs (
    id SERIAL primary key,
    name TEXT,
    age smallint,
    breed TEXT
)