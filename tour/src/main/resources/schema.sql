CREATE TABLE IF NOT EXISTS tour (
                                          tour_id SERIAL PRIMARY KEY,
                                          title varchar(100) NOT NULL,
    tour_date date NOT NULL,
    country varchar(20) NOT NULL,
    area varchar(20) NOT NULL,
    created_at date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
    );

CREATE SEQUENCE IF NOT EXISTS seq_tour
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;