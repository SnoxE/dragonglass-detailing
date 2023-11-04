create table users(
  user_id integer not null primary key generated always as identity,
  first_name varchar(30) not null,
  last_name varchar(30),
  email varchar(50) not null,
  password text not null
);

insert into users (first_name, email, password) values ('snoxe', 'snoxe@gmail.com', 'password')