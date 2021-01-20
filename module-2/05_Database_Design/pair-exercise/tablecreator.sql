--tablecreator.sql
--This is where we will actually do the work of creating the tables
--DROP TABLES IF EXISTS
--ROLLBACK TRANSACTION;
--BEGIN TRANSACTION;


DROP TABLE IF EXISTS pet_owner CASCADE;
DROP TABLE IF EXISTS pet CASCADE;
DROP TABLE IF EXISTS visit CASCADE;
DROP TABLE IF EXISTS procedure CASCADE;
--CREATE TABLE
CREATE TABLE pet_owner
(
        owner_id SERIAL NOT NULL PRIMARY KEY
        ,first_name VARCHAR(50) NOT NULL
        ,last_name VARCHAR(50) NOT NULL
        ,address VARCHAR(50) NOT NULL
        ,city VARCHAR(50) NOT NULL
        ,state CHAR(2) NOT NULL
        ,zip VARCHAR(10) NOT NULL
        ,phone VARCHAR(15) NOT NULL
);

CREATE TABLE pet
(
        pet_id SERIAL NOT NULL PRIMARY KEY
        ,owner_id INTEGER NOT NULL
        ,name VARCHAR(50)
        ,type VARCHAR(50)
        ,age CHAR(2)
);

CREATE TABLE visit
(
        visit_id SERIAL NOT NULL PRIMARY KEY
        ,visit_date DATE NOT NULL
        ,procedure_id INTEGER NOT NULL
        ,pet_id INTEGER NOT NULL
);

CREATE TABLE procedure
(
        procedure_id SERIAL NOT NULL PRIMARY KEY
        ,visit_id INTEGER 
        ,name VARCHAR(50) NOT NULL
);

--ALTER TABLE

ALTER TABLE pet
ADD CONSTRAINT fk_pet_owner_pet
FOREIGN KEY (owner_id)
REFERENCES pet_owner (owner_id);


ALTER TABLE visit
ADD CONSTRAINT fk_pet_visit
FOREIGN KEY (pet_id)
REFERENCES pet (pet_id);


ALTER TABLE procedure
ADD CONSTRAINT fk_visit_procedure
FOREIGN KEY (visit_id)
REFERENCES visit (visit_id);
