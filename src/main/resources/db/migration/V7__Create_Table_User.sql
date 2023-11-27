CREATE TABLE users (

    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    firstname TEXT NOT NULL,
    lastname TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    token TEXT UNIQUE ,
    role TEXT NOT NULL
);