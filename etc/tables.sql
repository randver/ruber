CREATE TABLE ru_trips
(
  
id int Identity (1, 1) primary key NOT NULL,
  
start_time int,  
request_time int,
 
 distance float,
 
 product_id int,
 
 end_time int,
  
uuid int,

status nvarchar(10)
)





CREATE TABLE ru_drivers
(
  
id int Identity (1, 1) primary key NOT NULL,
  
user_id int
)




CREATE TABLE ru_driverinfo (
id int Identity (1,1) primary key NOT NULL,
driver_id int,
comment TEXT,
rating int,
user_id int
)

INSERT INTO ru_users(username, firstname, lastname, password, email) VALUES ('travis', 'Travis', 'Bickle', 'pass', 'travis@gmail.com')
