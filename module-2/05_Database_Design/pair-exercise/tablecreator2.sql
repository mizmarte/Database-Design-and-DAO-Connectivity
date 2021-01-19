--tablecreator2.sql



--This is where we will actually do the work of creating the tables
--DROP TABLE IF EXISTS
--DROP TABLES IF EXISTS
DROP TABLE IF EXISTS pet_owner;
DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS visit;
DROP TABLE IF EXISTS line_item;
DROP TABLE IF EXISTS invoice;
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

CREATE TABLE line_item
(
        line_item_id SERIAL NOT NULL PRIMARY KEY
        ,visit_id INTEGER NOT NULL
        ,name VARCHAR(50) NOT NULL
        ,amount DECIMAL(10,2) NOT NULL
);

CREATE TABLE invoice
(
        invoice_id SERIAL NOT NULL PRIMARY KEY
        ,owner_id INTEGER NOT NULL
        ,line_item_id INTEGER NOT NULL
        ,total_due DECIMAL(10,2) NOT NULL
        ,payment_date DATE NOT NULL
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


ALTER TABLE line_item
ADD CONSTRAINT fk_visit_line_item
FOREIGN KEY (visit_id)
REFERENCES visit (visit_id);

ALTER TABLE invoice
ADD CONSTRAINT fk_line_item_invoice
FOREIGN KEY (line_item_id)
REFERENCES line_item (line_item_id);

ALTER TABLE pet_owner
ADD CONSTRAINT fk_invoice_pet_owner
FOREIGN KEY (owner_id)
REFERENCES pet_owner (owner_id);
