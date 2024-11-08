CREATE TABLE actors
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)          NOT NULL,
    briefly    VARCHAR(255)          NULL,
    image      VARCHAR(255)          NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    CONSTRAINT pk_actors PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE directors
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)          NULL,
    briefly    VARCHAR(255)          NULL,
    image      VARCHAR(255)          NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    CONSTRAINT pk_directors PRIMARY KEY (id)
);

CREATE TABLE movies
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    title        VARCHAR(255)          NOT NULL,
    synopsis     VARCHAR(255)          NULL,
    url          VARCHAR(255)          NULL,
    image        VARCHAR(255)          NULL,
    release_date date                  NULL,
    rating       FLOAT                 NULL,
    runtime      SMALLINT              NULL,
    created_at   datetime              NULL,
    updated_at   datetime              NULL,
    CONSTRAINT pk_movies PRIMARY KEY (id)
);

CREATE TABLE series
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)          NULL,
    synopsis   VARCHAR(255)          NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    CONSTRAINT pk_series PRIMARY KEY (id)
);