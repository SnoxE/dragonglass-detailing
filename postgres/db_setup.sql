CREATE TYPE public.car_size AS ENUM ('SMALL', 'MEDIUM', 'LARGE');

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

insert into services(name, price, length, car_size) values ('Mycie Ręczne', '100', '00:01:00', 'SMALL');
insert into services(name, price, length, car_size) values ('Mycie Ręczne', '120', '00:01:15', 'MEDIUM');
insert into services(name, price, length, car_size) values ('Mycie Ręczne', '140', '00:01:30', 'LARGE');

insert into services(name, price, length, car_size) values ('Woskowanie', '400', '00:03:00', 'SMALL');
insert into services(name, price, length, car_size) values ('Woskowanie', '500', '00:03:15', 'MEDIUM');
insert into services(name, price, length, car_size) values ('Woskowanie', '600', '00:03:30', 'LARGE');

insert into services(name, price, length, car_size) values ('One Polish', '1000', '00:04:00', 'SMALL');
insert into services(name, price, length, car_size) values ('One Polish', '1100', '00:04:30', 'MEDIUM');
insert into services(name, price, length, car_size) values ('One Polish', '1200', '00:05:00', 'LARGE');

insert into services(name, price, length, car_size) values ('Powłoka Ceramiczna', '1800', '00:06:00', 'SMALL');
insert into services(name, price, length, car_size) values ('Powłoka Ceramiczna', '2100', '00:06:30', 'MEDIUM');
insert into services(name, price, length, car_size) values ('Powłoka Ceramiczna', '2400', '00:07:00', 'LARGE');

insert into services(name, price, length, car_size) values ('Korekta Lakieru', '1500', '00:08:00', 'SMALL');
insert into services(name, price, length, car_size) values ('Korekta Lakieru', '1800', '00:09:00', 'MEDIUM');
insert into services(name, price, length, car_size) values ('Korekta Lakieru', '2100', '00:10:00', 'LARGE');

insert into services(name, price, length, car_size) values ('Pranie Podstawowe', '300', '00:03:00', 'SMALL');
insert into services(name, price, length, car_size) values ('Pranie Podstawowe', '375', '00:03:30', 'MEDIUM');
insert into services(name, price, length, car_size) values ('Pranie Podstawowe', '450', '00:04:00', 'LARGE');

insert into services(name, price, length, car_size) values ('Detailing Wnętrza', '600', '00:06:00', 'SMALL');
insert into services(name, price, length, car_size) values ('Detailing Wnętrza', '675', '00:06:30', 'MEDIUM');
insert into services(name, price, length, car_size) values ('Detailing Wnętrza', '750', '00:07:00', 'LARGE');

insert into services(name, price, length, car_size) values ('Niewidzialna Wycieraczka', '100', '00:00:40', 'SMALL');
insert into services(name, price, length, car_size) values ('Niewidzialna Wycieraczka', '120', '00:00:50', 'MEDIUM');
insert into services(name, price, length, car_size) values ('Niewidzialna Wycieraczka', '140', '00:01:00', 'LARGE');

-- RESERVATIONS TABLE

create table reservations(
  id integer NOT NULL primary key generated always as identity,
  user_id integer NOT NULL references users(id) ,
  service_id integer NOT NULL references services(id),
  car_id integer NOT NULL references cars(id),
  reservation_term date  NOT NULL
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