CREATE TABLE IF NOT EXISTS ticket (
    ticket_id SERIAL PRIMARY KEY,
    price float NOT NULL,
    place varchar(20) NOT NULL,
    tour_id int NOT NULL,
    created_at date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS ticket_order (
    id SERIAL PRIMARY KEY,
    order_id uuid NOT NULL,
    ticket_id int NOT NULL,
    status varchar(20) NOT NULL,
    user_email varchar(50) NOT NULL,
    created_at date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_ticket
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;

CREATE SEQUENCE IF NOT EXISTS seq_ticket_order
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;