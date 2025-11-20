CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(20) NOT NULL DEFAULT 'USER',
    CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE TABLE profiles
(
    id             BIGSERIAL PRIMARY KEY,
    phone_number   VARCHAR(15),
    date_of_birth  DATE,
    username       VARCHAR(255) NOT NULL,
    user_id        BIGINT NOT NULL,
    CONSTRAINT uk_profiles_username UNIQUE (username),
    CONSTRAINT fk_profiles_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);







