CREATE TABLE ru_trips
(
  
id int Identity (1, 1) primary key NOT NULL,
  
start_time bigint,  
request_time bigint,
 
 distance double,
 
 product_id int,
 
 end_time bigint,
  
uuid int,

status nvarchar(10)
)





CREATE TABLE ru_drivers
(
  
id int Identity (1, 1) primary key NOT NULL,
  
user_id int,
product_id int
)




CREATE TABLE ru_driverinfo (
id int Identity (1,1) primary key NOT NULL,
driver_id int,
comment TEXT,
rating int,
user_id int
)
CREATE TABLE ru_products {
id int Identity (1,1) primary key NOT NULL,
description nvarchar(50),
display_name nvarchar(50),
capacity int
}


insert into ru_products(description, display_name, capacity) values ('The original uber', 'UbertBLACK', 4)
insert into ru_products(description, display_name, capacity) values ('Room for everyone', 'UbertSUV', 6)
insert into ru_products(description, display_name, capacity) values ('Taxi without the hassle', 'UbertTAXI', 4)
insert into ru_products(description, display_name, capacity) values ('The low-cost uber', 'UbertX', 4)

INSERT INTO ru_users(username, firstname, lastname, password, email, registered) VALUES ('Steve', 'Steve', 'Jobs', 'pass', 'ceo@apple.com', '2008-10-23 08:13:22')

INSERT INTO ru_users(username, firstname, lastname, password, email, registered) VALUES ('Billy', 'Bill', 'Gates', 'pass', 'ceo@microsoft.com', '2008-10-23 22:13:22')

INSERT INTO ru_users(username, firstname, lastname, password, email, registered) VALUES ('travis', 'Travis', 'Bickle', 'pass', 'travis@gmail.com', '2014-10-23 17:13:22')

INSERT INTO ru_users(username, firstname, lastname, password, email, registered) VALUES ('CR7', 'Cristiano', 'Ronaldo', 'pass', 'cr7@united.com', '2009-10-23 12:13:22')

INSERT INTO ru_users(username, firstname, lastname, password, email, registered) VALUES ('Barry', 'Barrack', 'Obama', 'pass', 'nr1@usa.gov', '2014-06-11 06:13:22')

INSERT INTO ru_drivers (user_id, product_id) VALUES (1, 1)
INSERT INTO ru_drivers (user_id, product_id) VALUES (2, 3)
INSERT INTO ru_drivers (user_id, product_id) VALUES (3, 4)
INSERT INTO ru_driverinfo (driver_id, comment, rating, user_id) VALUES (1, 'Solid Ride', 5, 1)
INSERT INTO ru_driverinfo (driver_id, comment, rating, user_id) VALUES (1, 'Had better', 2, 2)
INSERT INTO ru_driverinfo (driver_id, comment, rating, user_id) VALUES (2, 'You rule', 5, 1)
INSERT INTO ru_driverinfo (driver_id, comment, rating, user_id) VALUES (2, 'Splendid ride, I am very happy', 5, 2)
INSERT INTO ru_driverinfo (driver_id, comment, rating, user_id) VALUES (4, 'Average ride', 1, 3)
INSERT INTO ru_driverinfo (driver_id, comment, rating, user_id) VALUES (3, 'Awful ride, your car smells ! ', 2, 4)
DELETE FROM ru_users WHERE username='travis'

select ru_drivers.car, ru_driverinfo.comment 
from ru_users u
inner join ru_drivers d on u.id = d.user_id
inner join ru_driverinfo di on di.driver_id = d.id
where u.username = username


select di.comment 
from ru_driverinfo on di.driver_id = d.id


select u.username, d.car, di.comment 
                from ru_users u
                inner join ru_drivers d on u.id = d.user_id
                inner join ru_driverinfo di on di.driver_id = d.id 
                where u.username = 'travis'


select ru_users.username, ru_drivers.car, ru_driverinfo.comment
from ru_users
  inner join ru_drivers on ru_users.id = ru_drivers.user_id
  inner join ru_driverinfo on ru_driverinfo.driver_id = ru_drivers.id
where ru_users.username = 'travis'