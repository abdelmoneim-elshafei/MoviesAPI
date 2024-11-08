ALTER TABLE movies
    ADD director_id BIGINT NULL;

ALTER TABLE movies
    ADD CONSTRAINT FK_MOVIES_ON_DIRECTOR FOREIGN KEY (director_id) REFERENCES directors (id);

ALTER TABLE directors
    MODIFY briefly VARCHAR(255);

ALTER TABLE genres
    MODIFY name VARCHAR(255);

ALTER TABLE movies
    MODIFY synopsis VARCHAR(255);

ALTER TABLE series
    MODIFY synopsis VARCHAR(255);