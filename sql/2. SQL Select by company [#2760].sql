CREATE TABLE company
(
    id serial NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id serial NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO public.company(name)VALUES 
('Alibaba'),
('Burgerking'),
('Caterpillar'),
('Doshirak'),
('Ecco')
;

INSERT INTO public.person(name, company_id) VALUES
('Anders', 1), 
('Albert', 1), 
('Avi', 1), 
('Arci', 1), 
('Arthur', 1), 
('Bill', 2), 
('Bob', 2),
('Boris', 2), 
('Bartolomew', 2), 
('Bridgitte', 2),  
('Clyve', 3), 
('Cindy', 3),
('Collin', 3),
('Dyllan', 4), 
('David', 4), 
('Eric', 5) 
;

select person.name, company.name 
from person inner join company on person.company_id = company.id
where company.id != 5;


SELECT company.name, COUNT(*) AS "Количество сотрудников"
FROM public.person
inner join company on person.company_id = company.id
GROUP By company.name
HAVING COUNT(*) = 
(SELECT COUNT(*) FROM public.person
GROUP By company_id
ORDER  BY count(*) DESC
LIMIT 1
);








