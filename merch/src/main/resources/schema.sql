CREATE TABLE IF NOT EXISTS merch (
                                          merch_id SERIAL PRIMARY KEY,
                                          title varchar(100) NOT NULL,
    price float NOT NULL,
    category varchar(20) NOT NULL,
    description varchar(100) NOT NULL,
    image varchar(100) NOT NULL,
    created_at date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS merch_order (
    id SERIAL PRIMARY KEY,
    order_id uuid NOT NULL,
    merch_id int NOT NULL,
    status varchar(20) NOT NULL,
    user_email varchar(50) NOT NULL,
    created_at date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL,
    FOREIGN KEY (merch_id) REFERENCES merch(merch_id)
);

CREATE SEQUENCE IF NOT EXISTS seq_merch
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;

CREATE SEQUENCE IF NOT EXISTS seq_merch_order
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;