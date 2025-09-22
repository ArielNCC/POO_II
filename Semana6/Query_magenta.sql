CREATE DATABASE magenta_db;
USE magenta_db;

CREATE TABLE cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    duracion INT NOT NULL,
    genero ENUM('Comedia','Drama','Accion','Terror','Romance','Documental')
);

-- Datos de prueba
INSERT INTO cartelera (titulo, director, anio, duracion, genero) VALUES
('El Padrino', 'Francis Ford Coppola', 1972, 175, 'Drama'),
('La La Land', 'Damien Chazelle', 2016, 128, 'Romance');

SELECT * FROM cartelera;
