CREATE TABLE IF NOT EXISTS ticket (
                                          ticket_id SERIAL PRIMARY KEY,
                                          price float NOT NULL,
    place varchar(20) NOT NULL,
    is_purchase boolean NOT NULL,
    tour_id int NOT NULL,
    created_at date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
    );

CREATE SEQUENCE IF NOT EXISTS seq_ticket
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;