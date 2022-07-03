create table parents(
    id serial primary key,
    name varchar(255),
    surname varchar(255)

);

create table children(
    id serial primary key,
    name varchar(255),
    surname varchar(255),
    parents_id int references parents(id)
);





 create table books(
     id serial primary key,
     name varchar(255)
 );
 
 create table readers(
     id serial primary key,
     name varchar(255),
     surname varchar(255)
 );
 
 create table books_readers(
     id serial primary key,
     books_id int references books(id),
     readers_id int references readers(id)
 );




create table car_passport(
    id serial primary key,
    car_number varchar(255)
);

create table vehicle(
    manufactiring_id serial primary key,
    manufactiring_company varchar(255),
    model varchar(255),
    car_passport_id int references car_passport(id) unique
);