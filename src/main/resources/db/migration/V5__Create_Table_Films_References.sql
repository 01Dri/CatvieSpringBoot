CREATE TABLE film_references (
    film_id INT,
    genre_id INT,
    director_id INT,
    distributor_id INT,
    FOREIGN KEY (film_id) REFERENCES films(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id),
    FOREIGN KEY (director_id) REFERENCES directors(id),
    FOREIGN KEY (distributor_id) REFERENCES distributors(id)
);