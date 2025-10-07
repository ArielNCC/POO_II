CREATE DATABASE IF NOT EXISTS CINE_DB CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE CINE_DB;

-- Tabla de géneros
CREATE TABLE IF NOT EXISTS genero (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL UNIQUE
);

-- Tabla de directores
CREATE TABLE IF NOT EXISTS director (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

-- Tabla de películas (cartelera)
CREATE TABLE IF NOT EXISTS cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director_id INT NOT NULL,
    anio INT NOT NULL CHECK (anio >= 1888 AND anio <= 2100),
    duracion INT NOT NULL CHECK (duracion > 0),
    genero_id INT NOT NULL,
    UNIQUE KEY ux_titulo_director (titulo, director_id),
    FOREIGN KEY (genero_id) REFERENCES genero(id),
    FOREIGN KEY (director_id) REFERENCES director(id)
);

-- Ejemplo de datos
INSERT INTO genero (nombre) VALUES ('Comedia');
INSERT INTO genero (nombre) VALUES ('Drama');
INSERT INTO genero (nombre) VALUES ('Accion');
INSERT INTO genero (nombre) VALUES ('Terror');
INSERT INTO genero (nombre) VALUES ('Romance');
INSERT INTO genero (nombre) VALUES ('Documental');

INSERT INTO director (nombre) VALUES ('Francis Ford Coppola');
INSERT INTO director (nombre) VALUES ('Damien Chazelle');
INSERT INTO director (nombre) VALUES ('Christopher Nolan');
INSERT INTO director (nombre) VALUES ('Stanley Kubrick');
INSERT INTO director (nombre) VALUES ('Jean-Pierre Jeunet');
INSERT INTO director (nombre) VALUES ('Juan José Campanella');
INSERT INTO director (nombre) VALUES ('Morgan Spurlock');
INSERT INTO director (nombre) VALUES ('James Cameron');
INSERT INTO director (nombre) VALUES ('Bong Joon-ho');
INSERT INTO director (nombre) VALUES ('Jaume Balagueró');

INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('El Padrino', 1, 1972, 175, 2);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('La La Land', 2, 2016, 128, 5);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('Inception', 3, 2010, 148, 3);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('El Resplandor', 4, 1980, 146, 4);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('Amélie', 5, 2001, 122, 1);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('El Secreto de sus Ojos', 6, 2009, 129, 2);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('Super Size Me', 7, 2004, 100, 6);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('Titanic', 8, 1997, 195, 5);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('Parásitos', 9, 2019, 132, 2);
INSERT INTO cartelera (titulo, director_id, anio, duracion, genero_id) VALUES ('REC', 10, 2007, 78, 4);
