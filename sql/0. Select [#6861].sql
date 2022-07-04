create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
 
insert into fauna(name, avg_age, discovery_date)
values ('swordfish', 6000, '1830-01-01');
 
insert into fauna(name, avg_age, discovery_date)
values ('human', 20000, null);
 
insert into fauna(name, avg_age, discovery_date)
values ('lunafish', 15000, '1760-01-01');
 
insert into fauna(name, avg_age, discovery_date)
values ('black tiger', 8000, '1985-01-01');
 
insert into fauna(name, avg_age, discovery_date)
values ('megaturtle', 36500, null);
 
 
select * from fauna where name LIKE '%fish%';
 
select * from fauna where avg_age >10000 AND avg_age <21000;
 
select * from fauna where discovery_date is null;
 
select * from fauna where discovery_date < '1950-01-01';