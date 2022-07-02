CREATE TABLE public.mecenates
(
   id serial primary key,   	
   name character varying(255), 
   surname character varying(255), 
   age integer, 
   total_donation bigint 
);

INSERT INTO public.mecenates(
            name, surname, age, total_donation)
    VALUES ('John', 'Smith', 50, 50000);

UPDATE public.mecenates
   set name = 'Иван', surname='Иванов', age=40, total_donation=100000;

DELETE FROM public.mecenates;
