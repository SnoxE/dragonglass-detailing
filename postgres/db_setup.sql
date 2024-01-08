CREATE TYPE public.car_size AS ENUM ('MAŁE', 'ŚREDNIE', 'DUŻE');

create table users(
	id integer NOT NULL primary key generated always as identity,
	first_name varchar(30) NOT NULL,
	last_name varchar(30),
	email varchar(50) NOT NULL,
	password text NOT NULL,
	phone_number varchar(15) NOT NULL,
  role varchar(20) NOT NULL
);

create table cars(
  id integer NOT NULL primary key generated always as identity,
  user_id integer NOT NULL references users(id),
  make varchar(30) NOT NULL,
  model varchar(30) NOT NULL,
  production_year varchar(10) NOT NULL,
  size car_size NOT NULL,
  colour varchar(30) NOT NULL
);

-- SERVICES TABLE --

create table services(
  id integer NOT NULL primary key generated always as identity,
  name varchar(50) NOT NULL,
  price integer NOT NULL,
  length TIME NOT NULL,
  size car_size NOT NULL
);

insert into services(name, price, length, size) values ('Mycie Ręczne', '100', '01:00:00', 'MAŁE');
insert into services(name, price, length, size) values ('Mycie Ręczne', '120', '01:15:00', 'ŚREDNIE');
insert into services(name, price, length, size) values ('Mycie Ręczne', '140', '01:30:00', 'DUŻE');

insert into services(name, price, length, size) values ('Woskowanie', '400', '03:00:00', 'MAŁE');
insert into services(name, price, length, size) values ('Woskowanie', '500', '03:15:00', 'ŚREDNIE');
insert into services(name, price, length, size) values ('Woskowanie', '600', '03:30:00', 'DUŻE');

insert into services(name, price, length, size) values ('One Polish', '1000', '04:00:00', 'MAŁE');
insert into services(name, price, length, size) values ('One Polish', '1100', '04:30:00', 'ŚREDNIE');
insert into services(name, price, length, size) values ('One Polish', '1200', '05:00:00', 'DUŻE');

insert into services(name, price, length, size) values ('Powłoka Ceramiczna', '1800', '06:00:00', 'MAŁE');
insert into services(name, price, length, size) values ('Powłoka Ceramiczna', '2100', '06:30:00', 'ŚREDNIE');
insert into services(name, price, length, size) values ('Powłoka Ceramiczna', '2400', '07:00:00', 'DUŻE');

insert into services(name, price, length, size) values ('Korekta Lakieru', '1500', '08:00:00', 'MAŁE');
insert into services(name, price, length, size) values ('Korekta Lakieru', '1800', '09:00:00', 'ŚREDNIE');
insert into services(name, price, length, size) values ('Korekta Lakieru', '2100', '10:00:00', 'DUŻE');

insert into services(name, price, length, size) values ('Pranie Podstawowe', '300', '03:00:00', 'MAŁE');
insert into services(name, price, length, size) values ('Pranie Podstawowe', '375', '03:30:00', 'ŚREDNIE');
insert into services(name, price, length, size) values ('Pranie Podstawowe', '450', '04:00:00', 'DUŻE');

insert into services(name, price, length, size) values ('Detailing Wnętrza', '600', '06:00:00', 'MAŁE');
insert into services(name, price, length, size) values ('Detailing Wnętrza', '675', '06:30:00', 'ŚREDNIE');
insert into services(name, price, length, size) values ('Detailing Wnętrza', '750', '07:00:00', 'DUŻE');

insert into services(name, price, length, size) values ('Niewidzialna Wycieraczka', '100', '00:40:00', 'MAŁE');
insert into services(name, price, length, size) values ('Niewidzialna Wycieraczka', '120', '00:50:00', 'ŚREDNIE');
insert into services(name, price, length, size) values ('Niewidzialna Wycieraczka', '140', '01:00:00', 'DUŻE');

-- RESERVATIONS TABLE

create table reservations(
  id integer NOT NULL primary key generated always as identity,
  user_id integer NOT NULL references users(id) ,
  service_id integer NOT NULL references services(id),
  car_id integer NOT NULL references cars(id),
  start_at timestamp NOT NULL,
  end_at timestamp NOT NULL
);

insert into users (first_name, email, password) values ('snoxe', 'snoxe@gmail.com', 'password')


SELECT
	r.id AS res_id,
	s.name AS services_name,
	s.price AS services_price,
	c.make AS cars_make,
	c.model AS cars_model,
	c.production_year AS cars_year,
	c.colour AS cars_year,
	r.start_at AS res_start_at
FROM
	reservations AS r
	JOIN services AS s ON r.service_id = s.id
	JOIN cars AS c ON r.car_id = c.id
-- WHERE
--     r.user_id = ;




-- SETUP

CREATE TYPE public.car_size AS ENUM ('MAŁE', 'ŚREDNIE', 'DUŻE');

create table users(
	id integer NOT NULL primary key generated always as identity,
	first_name varchar(30) NOT NULL,
	last_name varchar(30),
	email varchar(50) NOT NULL,
	password text NOT NULL,
	phone_number varchar(15) NOT NULL,
  role varchar(20) NOT NULL
);

create table cars(
  id integer NOT NULL primary key generated always as identity,
  user_id integer NOT NULL references users(id),
  make varchar(30) NOT NULL,
  model varchar(30) NOT NULL,
  production_year varchar(10) NOT NULL,
  size car_size NOT NULL,
  colour varchar(30) NOT NULL
);

-- SERVICES TABLE --

create table services(
  id integer NOT NULL primary key generated always as identity,
  name varchar(50) NOT NULL,
  price integer NOT NULL,
  length TIME NOT NULL,
  car_size car_size NOT NULL
);

-- RESERVATIONS TABLE

create table reservations(
  id integer NOT NULL primary key generated always as identity,
  user_id integer NOT NULL references users(id) ,
  service_id integer NOT NULL references services(id),
  car_id integer NOT NULL references cars(id),
  start_at timestamp NOT NULL,
  end_at timestamp NOT NULL
);