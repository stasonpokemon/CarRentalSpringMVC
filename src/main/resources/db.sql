DROP DATABASE IF EXISTS car_rental_spring;
CREATE DATABASE IF NOT EXISTS car_rental_spring;
USE car_rental_spring;

INSERT INTO users(id, username, password, active)  VALUES (1,'admin',1,0x01);
INSERT INTO user_role(user_id, roles)  VALUES (1,'ADMIN');

INSERT INTO cars(id, producer, model, release_date)
VALUES (1,'Audi','A7','2015'),
       (2,'BMW','m3','2017'),
       (3,'Mercedes','600','2003');