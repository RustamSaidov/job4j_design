create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

begin transaction;

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);

commit transaction;

select * from products;

begin transaction;

delete from products;

drop table products;

rollback transaction;

select * from products;

begin transaction;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);

savepoint first_savepoint;

delete from products where price = 115;
update products set price = 75 where name = 'product_1';

select * from products;

rollback to first_savepoint;

select * from products;

commit transaction;

/*HOMEWORK*/

create table nature_events(
    id serial primary key,
    event_type varchar(255),
    event_date date,
    number_of_witnesses integer
);

INSERT INTO nature_events(event_type, event_date,  number_of_witnesses)VALUES('Извержение вулкана','2001-01-01', 100000);
INSERT INTO nature_events(event_type, event_date,  number_of_witnesses)VALUES('Крупный град','2002-02-02', 20000);
INSERT INTO nature_events(event_type, event_date,  number_of_witnesses)VALUES('Смерч','2003-03-03', 3000);

select * from nature_events;

begin transaction;

INSERT INTO nature_events(event_type, event_date,  number_of_witnesses)VALUES('Двойная радуга','2004-04-04', 400);

savepoint savepoint_after_fourth_record;

select * from nature_events;

INSERT INTO nature_events(event_type, event_date,  number_of_witnesses)VALUES('Зыбучие пески','2005-05-05', 50);

savepoint savepoint_after_fifth_record;

INSERT INTO nature_events(event_type, event_date,  number_of_witnesses)VALUES('Мираж','2006-06-06', 6);

select * from nature_events;

rollback to savepoint_after_fifth_record;

select * from nature_events;

INSERT INTO nature_events(event_type, event_date,  number_of_witnesses)VALUES('Мираж','2006-06-06', 6);

select * from nature_events;

rollback to savepoint_after_fourth_record;

select * from nature_events;