create table site_user (
    id serial primary key not null,
    login varchar(2000) unique,
    password varchar(2000)
);

create table url (
    id serial primary key not null,
    url varchar(2000),
    short_url varchar(2000)
);

create table site (
    id serial primary key not null,
    name varchar(2000) unique,
    site_user_id int references site_user(id) not null,
    url_id int references url(id)
);