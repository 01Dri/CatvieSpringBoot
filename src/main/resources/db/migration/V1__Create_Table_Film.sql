CREATE TABLE films (
    id INT NOT NULL,
    title VARCHAR(255),
    original_language VARCHAR(255),
    writer VARCHAR(255),
    release_date DATE,
    runtime INT,
    distributor VARCHAR(255),
    production_co VARCHAR(255),
    average_rating_critic DOUBLE,
    PRIMARY KEY (id)

);