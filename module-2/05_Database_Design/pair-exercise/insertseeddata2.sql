--insertseeddata2.sql


--This is where we will insert the actual data
--BEGIN TRANSACTION;
--ROLLBACK;

--INSERT INTO
INSERT INTO pet_owner
(
        first_name, last_name, address, city, state, zip, phone
)
VALUES
(       
        'Richard','Cook','123 This Street','My City','ON','Z5Z6G6','5437779311'
);

--SELECT*
--FROM pet_owner;

INSERT INTO pet
(
        pet_id, owner_id, name, type, age
)
VALUES
('1','1','Rover','Dog','12')
,('2','1','Morris','Cat','4');

--SELECT*
--FROM pet;

INSERT INTO visit
(
        visit_id, visit_date,pet_id
)
VALUES
('1','2002-01-13','1')
,('2','2002-01-13','2');

INSERT INTO line_item
(
        line_item_id, visit_id,name,amount
)
VALUES
('1','1','Rabies Vaccination (canine)','30.00')
,('2','2','Rabie Vaccination(feline)','24.00');

INSERT INTO invoice
(
        invoice_id, owner_id, line_item_id,total_due,payment_date
)
VALUES
('987', '1','1','30.00','2002-01-13')
,('988','1','2','24.00','2002-01-13');

--SELECT*
--FROM invoice;