CREATE TABLE films (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    title VARCHAR(255) UNIQUE,
    url VARCHAR(255) UNIQUE,
    original_language VARCHAR(255),
    writer VARCHAR(255),
    director_id INT,
    distributor VARCHAR(255) NOT NULL,
    release_date DATE,
    runtime INT,
    production_co VARCHAR(255),
    AVERAGE_RATING_AUDIENCE DOUBLE PRECISION,
    average_rating_critic DOUBLE PRECISION,
    FOREIGN KEY (director_id) REFERENCES directors(id)
);