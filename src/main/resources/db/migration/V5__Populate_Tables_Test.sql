-- Populando a tabela directors
INSERT INTO directors (id, name) VALUES
(1, 'Diretor 1'),
(2, 'Diretor 2'),
(3, 'Diretor 3');

-- Populando a tabela distributors
INSERT INTO distributors (id, name) VALUES
(1, 'Distribuidora 1'),
(2, 'Distribuidora 2'),
(3, 'Distribuidora 3');

-- Populando a tabela genres
INSERT INTO genres (id, genre_name) VALUES
(1, 'ACTION'),
(2, 'ADVENTURE'),
(3, 'HORROR'),
(4, 'ANIMATION'),
(5, 'COMEDY');

-- Populando a tabela films
INSERT INTO films (id, title, original_language, writer, director_id, distributor_id, genre_id, release_date, runtime, production_co, AVERAGE_RATING_AUDIENCE, average_rating_critic) VALUES
(1, 'Filme 1', 'Inglês', 'Escritor 1', 1, 1, 1, '2023-10-01', 120, 'Empresa de Produção 1', 4.5, 8.0),
(2, 'Filme 2', 'Espanhol', 'Escritor 2', 2, 2, 2, '2023-10-02', 110, 'Empresa de Produção 2', 4.2, 7.5),
(3, 'Filme 3', 'Francês', 'Escritor 3', 3, 3, 3, '2023-10-03', 105, 'Empresa de Produção 3', 4.0, 7.0);

-- Continue adicionando mais filmes conforme necessário
