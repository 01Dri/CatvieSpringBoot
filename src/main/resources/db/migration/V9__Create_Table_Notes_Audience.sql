CREATE TABLE notes_audiences (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    user_id INT NOT NULL,
    film_id INT NOT NULL,
    note DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (film_id) REFERENCES films(id)
);


INSERT INTO notes_audiences(film_id, user_id, note)
VALUES
(1, 1, 10.0),
(2, 2, 7.0),
(3, 3, 2.0);
