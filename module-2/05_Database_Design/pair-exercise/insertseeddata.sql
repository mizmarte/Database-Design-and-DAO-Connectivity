--insertseeddata.sql

--This is where we will insert the actual data
--INSERT INTO
BEGIN TRANSACTION;
ROLLBACK;
INSERT INTO pet_owner
(first_name, last_name,address,city,state,zip,phone)
VALUES
('Sam','Cook','2400 Jackson Street','Gary','IN','34253','2167779311')
,('Terry','Kim','666 Elm Street','Detroit','MI','60075','2168675309');

SELECT*
FROM pet_owner;

INSERT INTO pet
(pet_id,owner_id,name,type,age)
VALUES
('246','1','Rover','Dog','12')
,('298','2','Spot','Dog','2')
,('341','1','Morris','Cat','4')
,('519','2','Tweedy','Bird','2');


SELECT*
FROM pet;

INSERT INTO procedure
(procedure_id,name)
VALUES
('01','Rabies Vaccination')
,('05','Heartworm Test')
,('08','Tetanus Vaccination')
,('10','Examine and Treat Wound')
,('12', 'Eye Wash')
,('20','Annual Check-Up');

--SELECT *
--FROM procedure;

INSERT INTO visit
(visit_id, visit_date, procedure_id, pet_id)
VALUES
('1','2001-01-23','01','341')
,('2','2002-01-13','01','341')
,('3','2002-01-13','01','246')
,('4','2002-03-10','05','298')
,('5','2002-03-27','10','246')
,('6','2002-04-02','05','246')
,('7','2002-04-30','20','519')
,('8','2002-04-30','12','519');

SELECT*
FROM visit;