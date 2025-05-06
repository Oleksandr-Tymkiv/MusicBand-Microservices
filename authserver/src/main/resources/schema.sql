CREATE TABLE IF NOT EXISTS _user (
                                          user_id SERIAL PRIMARY KEY,
                                          user_name varchar(100) NOT NULL,
    user_dob date NOT NULL,
    user_email varchar(100) NOT NULL,
    user_password varchar(100) NOT NULL,
    user_role varchar(20) NOT NULL,
    created_at date NOT NULL,
    created_by varchar(20) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(20) DEFAULT NULL
    );

CREATE SEQUENCE IF NOT EXISTS seq_user
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1;