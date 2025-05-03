CREATE TABLE IF NOT EXISTS payment (
    payment_id SERIAL PRIMARY KEY,
    order_id uuid NOT NULL,
    price float NOT NULL,
    order_type varchar(20) NOT NULL,
    status varchar(20) NOT NULL,
    user_email varchar(50) NOT NULL,
    created_at date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_payment
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;
