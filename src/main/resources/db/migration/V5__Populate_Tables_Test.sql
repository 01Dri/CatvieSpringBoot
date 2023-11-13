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

INSERT INTO films (id, title, url, original_language, writer, director_id, distributor_id, genre_id, release_date, runtime, production_co, AVERAGE_RATING_AUDIENCE, average_rating_critic) VALUES
(1, 'Os Mercenários 4', 'https://capas-p.imagemfilmes.com.br/164093_000_p.jpg', 'Inglês', 'Mario Puzo', 1, 1, 1, '1972-03-24', 175, 'Paramount Pictures', 9.2, 9.2),
(2, 'Uma Fada Veio Me Visitar', 'https://capas-p.imagemfilmes.com.br/164892_000_p.jpg', 'Inglês', 'J.R.R. Tolkien', 2, 2, 2, '2001-12-10', 251, 'New Line Cinema', 8.9, 9.1),
(3, 'Não Abra!', 'https://capas-p.imagemfilmes.com.br/164115_000_p.jpg', 'Inglês', 'Quentin Tarantino', 2, 2, 3, '1994-10-14', 154, 'Miramax Films', 8.9, 8.9),
(4, 'O Porteiro', 'https://capas-p.imagemfilmes.com.br/164076_000_p.jpg', 'Inglês', 'Stephen King', 3, 3, 3, '1980-05-23', 146, 'Warner Bros.', 8.4, 8.4),
(5, 'O Convento', 'https://capas-p.imagemfilmes.com.br/164062_000_p.jpg', 'Inglês', 'Arthur C. Clarke', 1, 2, 3, '1968-04-03', 149, 'Metro-Goldwyn-Mayer', 8.3, 8.3)

