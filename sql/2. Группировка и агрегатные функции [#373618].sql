create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO public.devices(name, price) VALUES
('flashcard', 1000), 
('MP3-player', 3000), 
('Smartphone', 10000), 
('Tablet PC', 30000), 
('Notebook', 150000), 
('Photocamera', 75000);

INSERT INTO public.people(name)VALUES 
('John'),
('Tom'),
('Bill');

INSERT INTO public.devices_people(device_id, people_id)VALUES 
(1, 1),
(2, 1),
(3, 1),
(5, 2),
(4, 3),
(6, 3)
;

select avg(price) from public.devices;

select dp.people_id, avg(d.price) 
from devices d 
join devices_people dp 
on dp.device_id = d.id 
group by dp.people_id;



select dp.people_id, avg(d.price) 
from devices d 
join devices_people dp 
on dp.device_id = d.id 
group by dp.people_id
having avg(d.price) > 5000;