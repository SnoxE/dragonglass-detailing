create table users(
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(30) not null,
  last_name varchar(30),
  email varchar(50) not null,
  password text not null
);