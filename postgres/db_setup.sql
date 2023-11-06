create table users(
	id integer not null primary key generated always as identity,
	first_name varchar(30) not null,
	last_name varchar(30),
	email varchar(50) not null,
	password text not null,
	phone_number varchar(15) not null,
  role varchar(20) not null
);

create table cars(
  id integer not null primary key generated always as identity,
  user_id integer references users(id),
  make varchar(30),
  model varchar(30),
  production_year varchar(10)
)

insert into users (first_name, email, password) values ('snoxe', 'snoxe@gmail.com', 'password')