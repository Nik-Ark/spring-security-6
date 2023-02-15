USE custom_auth_db;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users_authorities;

CREATE TABLE users
(
    id              INT NOT NULL    AUTO_INCREMENT,
    username        VARCHAR(45)     NOT NULL UNIQUE,
    access_key      VARCHAR(100)    NOT NULL,
    time_insert     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY     (id)
) COLLATE utf8_bin;

INSERT INTO users (id, username, access_key)
VALUES (1, 'user1', 'user1_secret_access_key'),
       (2, 'user2', 'user2_secret_access_key'),
       (3, 'user3', 'user3_secret_access_key');

CREATE TABLE authorities
(
    id              INT NOT NULL    AUTO_INCREMENT,
    name            VARCHAR(45)     NOT NULL,
    PRIMARY KEY     (id)
) COLLATE utf8_bin;

INSERT INTO authorities
VALUES (1, 'read'),
       (2, 'write'),
       (3, 'update'),
       (4, 'delete');

CREATE TABLE users_authorities
(
    user_id         INT NOT NULL,
    authority_id    INT NOT NULL,
    PRIMARY KEY     (user_id, authority_id)
) COLLATE utf8_bin;

INSERT INTO users_authorities
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 2),
       (3, 1);