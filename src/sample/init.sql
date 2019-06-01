create table demodb.user(
	login varchar(256) not null,
	password varchar(256) not null,
	name varchar(256),
	surname varchar(256),
	email varchar(256),
	country varchar(256),
	contact varchar(256),
	id serial primary key
);

