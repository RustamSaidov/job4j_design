create table rank(
    id serial primary key,
    rankname varchar(255)
);

create table serviceman(
    id serial primary key,
    name varchar(255),
    surname varchar(255),
    rank_id int references rank(id)
);

insert into rank(rankname) values ('Soldier');
insert into rank(rankname) values ('Officer');

insert into serviceman(name, surname, rank_id) values ('John', 'Smith', 1);
insert into serviceman(name, surname, rank_id) values ('Bill', 'Johnson', 2);
insert into serviceman(name, surname, rank_id) values ('Paul', 'McCloud', 1);
insert into serviceman(name, surname) values ('Tom', 'Soyer');

select sm.name, sm.surname, r.rankname 
from serviceman as sm inner join rank as r on sm.rank_id = r.id;

select sm.name as Имя, sm.surname as Фамилия, r.rankname as Звание
from serviceman as sm inner join rank as r on sm.rank_id = r.id;


select sm.name as Имя, sm.surname as Фамилия, r.rankname as Звание
from serviceman as sm inner join rank as r on sm.rank_id = r.id
where sm.name like 'John%' OR sm.surname like 'John%';
;