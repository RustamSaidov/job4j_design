create table type(
    id serial primary key, name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date, 
    price float 
);

INSERT INTO public.type(name)VALUES 
('МОЛОКО'),
('МОРОЖЕННОЕ'),
('СЫР'), 
('ХЛЕБ')
;

INSERT INTO public.product(name, type_id,  expired_date, price)VALUES
('Пискаревское молоко', 1, '2023-01-01', 60),
('Простоквашино', 1, '2022-01-01', 70),
('Лакомка', 2, '2022-12-12', 50),
('Питерское мороженное', 2, '2021-12-12', 130),
('Бонжур', 2, '2023-11-11', 30),
('33 копейки', 2, '2024-01-01', 90),
('Фруктовый лед', 2, '2021-06-01', 85),
('Славмолоко', 2, '2021-12-12', 66),
('Как в СССР', 2, '2023-12-12', 80),
('ГОСТовское мороженное', 2, '2021-12-12', 100),
('Даша', 2, '2023-12-12', 100),
('Смородиновое мороженное', 2, '2023-10-12', 50),
('Экзотик', 2, '2020-01-12', 150),
('Оранжевая корова', 2, '2022-09-10', 110),
('пармезан', 3, '2025-01-01', 1500),
('Российский', 3, '2024-01-01', 600),
('Ржаной', 4, '2022-07-30', 40),
('Французский', 4, '2022-06-30', 100)
;

SELECT p.id, p.name, type_id, expired_date, price
FROM public.product p
inner join type t on p.type_id = t.id
where t.name ='СЫР'
;

SELECT p.id, p.name, type_id, expired_date, price
FROM public.product p
inner join type t on p.type_id = t.id
where p.name LIKE '%мороженное%'
;

SELECT p.id, p.name, type_id, expired_date, price
FROM public.product p
inner join type t on p.type_id = t.id
where expired_date < current_date
;

--Вариант 1:
SELECT p.id, p.name, type_id, expired_date, price
FROM public.product p
inner join type t on p.type_id = t.id
ORDER BY price DESC
LIMIT 1
;

--Вариант 2:
SELECT name, price  
FROM public.product  
WHERE price = (SELECT MAX(price) 
               FROM public.product 
               );

SELECT t.name, COUNT(*) AS "Количество продуктов"
FROM public.product p
inner join type t on p.type_id = t.id
GROUP By t.name
;

SELECT p.id, t.name, p.name, type_id, expired_date, price
FROM public.product p
inner join type t on p.type_id = t.id
WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО'
;

SELECT t.name, COUNT(*) AS "Количество продуктов"
FROM public.product p
inner join type t on p.type_id = t.id
GROUP By t.name
having COUNT(*)<10
;

SELECT p.id, p.name, type_id, t.name, expired_date, price
FROM public.product p
inner join type t on p.type_id = t.id
;