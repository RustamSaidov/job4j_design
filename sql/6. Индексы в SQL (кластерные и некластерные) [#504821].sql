create table people(
    id serial primary key,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(50)
);

\d people

create index people_last_name on people(last_name desc);

\d people

alter index people_last_name rename to people_last_name_desc;

\d people

drop index people_last_name_desc;

\d people