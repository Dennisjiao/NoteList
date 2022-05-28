create table Spittle (
	id int auto_increment primary key,
	message varchar(140) not null,
	created_at timestamp not null,
	latitude decimal(8,3) null,
	longitude decimal(8,3) null
);

create table Spitter (
	id int auto_increment primary key,
	username varchar(20) unique not null,
	password varchar(20) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null
);