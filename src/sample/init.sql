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

create table demodb.movie
(
	name varchar(256) null,
	description varchar(256) null,
	country varchar(256) null,
	director varchar(256) null,
	year int null,
	imageUrl varchar(256) null,
	author bigint unsigned not null ,
	constraint movie___fk
		foreign key (author) references user (id)
			on delete cascade
);

