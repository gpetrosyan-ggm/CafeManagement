
-- Create 'users' table
CREATE TABLE users
(
  id numeric(38) NOT NULL,
  user_name varchar(50) NOT NULL,
  password varchar(100) NOT NULL,
  role varchar(50) NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  age numeric(38) NOT NULL,
  CONSTRAINT user_name_unq UNIQUE (user_name),
  CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE SEQUENCE users_inc_seq;

CREATE FUNCTION users$users() RETURNS TRIGGER
    LANGUAGE plpgsql
as
$$
BEGIN
        SELECT
            nextval('users_inc_seq')
            INTO STRICT NEW.id;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_users
    BEFORE INSERT
    ON users
    FOR EACH ROW
EXECUTE PROCEDURE users$users();

-- Create 'cafe_tables' table
CREATE TABLE cafe_tables
(
  id numeric(38) NOT NULL,
  table_name varchar(50) NOT NULL,
  status varchar(50) NOT NULL,
  user_id numeric(38),
  CONSTRAINT table_name_unq UNIQUE (table_name),
  CONSTRAINT cafe_tables_pk PRIMARY KEY (id),
  CONSTRAINT cafe_tables_users_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE SEQUENCE cafe_tables_inc_seq;

CREATE FUNCTION cafe_tables$cafe_tables() RETURNS TRIGGER
    LANGUAGE plpgsql
as
$$
BEGIN
        SELECT
            nextval('cafe_tables_inc_seq')
            INTO STRICT NEW.id;
    RETURN NEW;
END;
$$;

CREATE TABLE orders
(
    id numeric(38) NOT NULL,
    status varchar(50) NOT NULL,
    comment varchar(4000) NOT NULL,
    user_id numeric(38),
    table_id numeric(38),
    CONSTRAINT orders_pk PRIMARY KEY (id),
    CONSTRAINT orders_users_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT orders_cafe_tables_fk FOREIGN KEY (table_id) REFERENCES cafe_tables (id)
);

-- Create 'orders' table
CREATE TRIGGER trg_cafe_tables
    BEFORE INSERT
    ON cafe_tables
    FOR EACH ROW
EXECUTE PROCEDURE cafe_tables$cafe_tables();

CREATE SEQUENCE orders_inc_seq;

CREATE FUNCTION orders$orders() RETURNS TRIGGER
    LANGUAGE plpgsql
as
$$
BEGIN
        SELECT
            nextval('orders_inc_seq')
            INTO STRICT NEW.id;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_orders
    BEFORE INSERT
    ON orders
    FOR EACH ROW
EXECUTE PROCEDURE orders$orders();

-- Create 'products' table
CREATE TABLE products
(
  id numeric(38) NOT NULL,
  category varchar(50) NOT NULL ,
  product_name varchar(255) NOT NULL,
  description varchar(255),
  price double precision,
  CONSTRAINT report_details_pk PRIMARY KEY (id)
);

CREATE SEQUENCE products_inc_seq;

CREATE FUNCTION products$products() RETURNS TRIGGER
    LANGUAGE plpgsql
as
$$
BEGIN
        SELECT
            nextval('products_inc_seq')
            INTO STRICT NEW.id;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_products
    BEFORE INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE products$products();

-- Create 'product_in_order' table
CREATE TABLE product_in_order
(
  id numeric(38) NOT NULL,
  amount numeric(38) NOT NULL,
  status varchar(50) NOT NULL ,
  comment varchar(2000) NOT NULL,
  order_id numeric(38),
  product_id numeric(38),
  CONSTRAINT product_in_order_pk PRIMARY KEY (id),
  CONSTRAINT product_in_order_orders_fk FOREIGN KEY (order_id) REFERENCES orders (id),
  CONSTRAINT product_in_order_products_fk FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE SEQUENCE product_in_order_inc_seq;

CREATE FUNCTION product_in_order$product_in_order() RETURNS TRIGGER
    LANGUAGE plpgsql
as
$$
BEGIN
        SELECT
            nextval('product_in_order_inc_seq')
            INTO STRICT NEW.id;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_product_in_order
    BEFORE INSERT
    ON product_in_order
    FOR EACH ROW
EXECUTE PROCEDURE product_in_order$product_in_order();
