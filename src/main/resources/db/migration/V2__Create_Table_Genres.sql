CREATE TABLE genres (
    id INT,
    genre_name VARCHAR(50),
    id_film INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_film) REFERENCES films(id)
);

