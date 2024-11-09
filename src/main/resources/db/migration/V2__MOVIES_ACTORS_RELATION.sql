CREATE TABLE moive_actor
(
    actor_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    CONSTRAINT pk_moive_actor PRIMARY KEY (actor_id, movie_id)
);

ALTER TABLE moive_actor
    ADD CONSTRAINT fk_moiact_on_actor FOREIGN KEY (actor_id) REFERENCES actors (id);

ALTER TABLE moive_actor
    ADD CONSTRAINT fk_moiact_on_movie FOREIGN KEY (movie_id) REFERENCES movies (id);