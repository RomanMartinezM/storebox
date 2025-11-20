    CREATE TABLE files
    (
        id             BIGSERIAL PRIMARY KEY,
        type           VARCHAR(20) NOT NULL,
        name           VARCHAR(255) NOT NULL,
        path           VARCHAR(255) NOT NULL,
        user_id        BIGINT NOT NULL,
        CONSTRAINT fk_files_user_id FOREIGN KEY (user_id) REFERENCES users(id)
    );