CREATE TABLE moive_genre
(
    genre_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    CONSTRAINT pk_moive_genre PRIMARY KEY (genre_id, movie_id)
);

ALTER TABLE movies
    ADD director_id BIGINT NULL;

ALTER TABLE movies
    ADD CONSTRAINT FK_MOVIES_ON_DIRECTOR FOREIGN KEY (director_id) REFERENCES directors (id);

ALTER TABLE moive_genre
    ADD CONSTRAINT fk_moigen_on_genre FOREIGN KEY (genre_id) REFERENCES genres (id);

ALTER TABLE moive_genre
    ADD CONSTRAINT fk_moigen_on_movie FOREIGN KEY (movie_id) REFERENCES movies (id);