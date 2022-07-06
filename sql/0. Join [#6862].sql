create table departments(
    id serial primary key, 
    name varchar(255)
);

create table employees(
    id serial primary key, 
    name varchar(255),
    departments_id int references departments(id)
);

INSERT INTO public.departments(name)VALUES 
('Отдел кадров'),
('Производство'),
('Конструкторский отдел'),
('Технологический отдел'),
('Отдел маркетинга'),
('Бухгалтерия')
;

INSERT INTO public.employees(name, departments_id) VALUES 
('Ольга', 1),
('Елена', 1),
('Иван', 2),
('Сергей', 2),
('Петр', 2),
('Дмитрий', 2),
('Марко', null),
('Тим', null),
('Станислав', 3),
('Вячеслав', 3),
('Тимофей', 4),
('Матвей', 4),
(null, 5),
(null, 5),
('Инга', 6),
('Анжела', 6),
('Жанна', 6)
;

--LEFT JOIN:
select * from employees e 
left join departments d on e.departments_id = d.id;

--RIGHT JOIN:
select * from employees e 
right join departments d on e.departments_id = d.id;

--FULL JOIN:
select * from employees e 
full join departments d on e.departments_id = d.id;

--CROSS JOIN:
select * from employees e 
cross join departments;

select DISTINCT d.name from departments d left join employees e on d.id = e.departments_id 
where e.name is null;

select d.name, e.name from employees e 
left join departments d on e.departments_id = d.id;

select d.name, e.name from departments d 
right join employees e on e.departments_id = d.id;

create table teens(
    id serial primary key, 
    name varchar(255),
    gender varchar(1)
);

INSERT INTO public.teens(name, gender)VALUES 
('John', 'M'),
('Tom', 'M'),
('Bill', 'M'),
('Katy', 'F'),
('Mary', 'F'),
('Kelsea', 'F')
;

select DISTINCT t1.name as a, t2.name as b, CONCAT ( t1.name,' and ', t2.name )  as "couple" 
from teens t1  
cross join 
teens t2
where t1.gender = 'M' AND t2.gender = 'F'
;
