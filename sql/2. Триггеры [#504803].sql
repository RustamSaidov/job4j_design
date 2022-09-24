create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();




create or replace function price_plus_tax_on_product_statement()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger price_plus_tax_on_product_statement_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure price_plus_tax_on_product_statement();

create or replace function price_plus_tax_on_product_row()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.1
        ;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger price_plus_tax_on_product_trigger
    before insert
    on products
    for each row
    execute procedure price_plus_tax_on_product_row();




create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

CREATE OR REPLACE FUNCTION set_history_of_price()
  returns trigger as
$$
BEGIN
		 INSERT INTO history_of_price(name, price, date)
		 VALUES(new.name, new.price, now());
	RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger set_history_of_price_trigger
    after insert
    on products
    for each row
    execute procedure set_history_of_price();
