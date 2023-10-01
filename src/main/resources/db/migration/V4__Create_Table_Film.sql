CREATE TABLE films (
    id INT NOT NULL,
    title VARCHAR(255),
    original_language VARCHAR(255),
    writer VARCHAR(255),
    director_id INT,
    distributor_id INT,
    genre_id INT,
    release_date DATE,
    runtime INT,
    production_co VARCHAR(255),
    AVERAGE_RATING_AUDIENCE DOUBLE,
    average_rating_critic DOUBLE,
    PRIMARY KEY (id),
    FOREIGN KEY (director_id) REFERENCES directors(id),
    FOREIGN KEY (distributor_id) REFERENCES distributors(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id)
);