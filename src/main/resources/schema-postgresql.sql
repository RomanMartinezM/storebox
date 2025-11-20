CREATE TABLE profiles
(
    id             BIGSERIAL PRIMARY KEY,
    bio            TEXT,
    phone_number   VARCHAR(15),
    date_of_birth  DATE,
    loyalty_points INTEGER DEFAULT 0
);

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT uk_users_email UNIQUE (email)
);


