
/*read uncommitted, dirty read */
create table products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price) VALUES ('product_1', 3, 50);
insert into products (name, count, price) VALUES ('product_2', 15, 32);
insert into products (name, count, price) VALUES ('product_3', 8, 115);

set session transaction isolation level read uncommitted;

/*read committed */

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

select * from products;

insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';

select * from products;

commit;

select * from products;

/*repeatable read*/

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

begin transaction isolation level repeatable read;

select * from products;

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';

update products set price = 75 where name = 'product_1';

commit;
ROLLBACK

/*serializable*/

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

begin transaction isolation level serializable;

select sum(count) from products;

update products set count = 26 where name = 'product_1';

update products set count = 26 where name = 'product_2';

commit;

/*HOMETASK:*/

create table users (
    id serial primary key,
    name varchar(50),
    email varchar(50),
    money integer
);

insert into users (name, email, money) VALUES ('Alan', 'Alan1980@gmail.com', 100);
insert into users (name, email, money) VALUES ('Bob', 'Bob1990@gmail.com', 200);
insert into users (name, email, money) VALUES ('Caleb', 'Caleb2000@gmail.com', 300);

/*1st transaction:*/
begin transaction isolation level serializable;

SELECT count (*) FROM users;

/*2nd transaction:*/
begin transaction isolation level serializable;

/*1st transaction:*/
insert into users (name, email, money) VALUES ('Dave', 'Dave2010@gmail.com', 400);

/*2nd transaction:*/
SELECT count (*) FROM users;

insert into users (name, email, money) VALUES ('Eve', 'Eve2020@gmail.com', 500);

commit;

/*1st transaction:*/
commit;