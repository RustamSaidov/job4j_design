create table car_bodies(
    id serial primary key, 
    name varchar(255)
);

create table car_engines(
    id serial primary key, 
    name varchar(255)
);

create table car_transmissions(
    id serial primary key, 
    name varchar(255)
);

create table cars(
    id serial primary key, 
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

INSERT INTO car_bodies(name) VALUES 
('седан'),
('хэтчбек'),
('пикап'),
('купе')
;

INSERT INTO car_engines(name) VALUES 
('1.6 карбюратор'),
('2 карбюратор'),
('1.6 инжектор'),
('2 инжектор')
;

INSERT INTO car_transmissions(name) VALUES 
('ручная'),
('автомат'),
('робот'),
('вариатор')
;

INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES 
('Mitsubishi', null, 1, 1),
('Mitsubishi', 2,null, 2),
('Mitsubishi', 3, 3, null),
('BMW', 3, 3, 3),
('BMW', 2, 2, 2),
('BMW', 1, 1, 1),
('BMW', 2, 1, 2),
('Lada', 1, 3, 2),
('Lada', 2, 1, 3),
('Lada', 3, 2, 3),
('Lada', 2, 3, 1),
('Lada', null, null, null)
;

select c.id, c.name, cb.name, ce.name, ct.name from cars c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

select DISTINCT cb.name from car_bodies cb left join cars c on cb.id = c.body_id 
where body_id is null;

select DISTINCT ce.name from car_engines ce left join cars c on ce.id = c.engine_id 
where engine_id is null;

select DISTINCT ct.name from car_transmissions ct left join cars c on ct.id = c.transmission_id 
where transmission_id is null;









