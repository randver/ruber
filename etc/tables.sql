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
car nvarchar(50)
)




CREATE TABLE ru_driverinfo (
id int Identity (1,1) primary key NOT NULL,
driver_id int,
comment TEXT,
rating int,
user_id int
)

INSERT INTO ru_users(username, firstname, lastname, password, email, registered) VALUES ('travis', 'Travis', 'Bickle', 'pass', 'travis@gmail.com', '2014-23-10 17:13:22')

INSERT INTO ru_drivers (user_id, car) VALUES (1, 'uberTAXI')
INSERT INTO ru_driverinfo (driver_id, comment, rating, user_id) VALUES (1, 'Solid Ride', 5, 1)
INSERT INTO ru_driverinfo (driver_id, comment, rating, user_id) VALUES (1, 'Had better', 2, 2)
DELETE FROM ru_users WHERE username='travis'

select ru_drivers.car, ru_driverinfo.comment 
from ru_users u
inner join ru_drivers d on u.id = d.user_id
inner join ru_driverinfo di on di.driver_id = d.id
where u.username = username


select di.comment 
from ru_driverinfo on di.driver_id = d.id