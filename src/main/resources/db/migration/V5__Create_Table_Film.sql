CREATE TABLE films (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    title VARCHAR(255) UNIQUE,
    url VARCHAR(255) UNIQUE,
    original_language VARCHAR(255),
    writer VARCHAR(255),
    director_id INT NOT NULL,
    distributor VARCHAR(255) NOT NULL,
    release_date DATE,
    runtime INT NOT NULL,
    production_co VARCHAR(255),
    average_rating_audience DOUBLE PRECISION,
    average_rating_critic DOUBLE PRECISION,
    poster_url VARCHAR(255) UNIQUE,
    user_id INT NOT NULL,
    FOREIGN KEY (director_id) REFERENCES directors(id),
    FOREIGN KEY (user_id) REFERENCES users(id)

);