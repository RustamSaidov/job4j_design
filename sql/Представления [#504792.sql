create table corporations (
    id serial primary key,
    name varchar(50),
    year_of_foundation integer
);

insert into corporations (name, year_of_foundation) values ('Toptool', 1893);
insert into corporations (name, year_of_foundation) values ('Techmonster', 1954);

create table products (
    id serial primary key,
    name varchar(50)
);

insert into products (name) values ('Шампунь');
insert into products (name) values ('Масло');
insert into products (name) values ('Мыло');
insert into products (name) values ('Скотч');
insert into products (name) values ('Гвозди');
insert into products (name) values ('Карамель');
insert into products (name) values ('Шурупы');
insert into products (name) values ('Скрепки');

create table countries (
    id serial primary key,
    name varchar(50)
);

insert into countries (name) values ('США');
insert into countries (name) values ('Россия');
insert into countries (name) values ('Испания');
insert into countries (name) values ('Польша');


create table factories(
    id serial primary key,
    name varchar(200),
    corporation_id integer references corporations(id),
    product_id integer references products(id),
    country_id integer references countries(id)
);
insert into factories (name, corporation_id, product_id, country_id) values ('Toptool-USA', 1, 2, 1);
insert into factories (name, corporation_id, product_id, country_id) values ('Toptool-USA', 1, 5, 1);
insert into factories (name, corporation_id, product_id, country_id) values ('Toptool-Rus1', 1, 5, 2);
insert into factories (name, corporation_id, product_id, country_id) values ('Toptool-Rus1', 1, 7, 2);
insert into factories (name, corporation_id, product_id, country_id) values ('Toptool-Rus2', 1, 8, 2);
insert into factories (name, corporation_id, product_id, country_id) values ('Toptool-Spain3', 1, 4, 3);
insert into factories (name, corporation_id, product_id, country_id) values ('Techmonster-Pol', 2, 1, 1);
insert into factories (name, corporation_id, product_id, country_id) values ('Techmonster-Pol', 2, 3, 1);

create view show_factories_who_produce_2_of_more_products_and_located_in_russia_and_which_corp_found_before_1900
as
select c.name as corporation,  f.name as factory_name, co.name as country, count(f.name) from corporations as c
join factories f on c.id = f.corporation_id
join products p on p.id = f.product_id
join countries co on co.id = f.country_id
where c.year_of_foundation < 1900
and co.name = 'Россия'
group by (c.name, co.name, f.name) having count(p.name) >= 2
;



