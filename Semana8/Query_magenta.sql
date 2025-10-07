CREATE DATABASE IF NOT EXISTS CINE_DB CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE CINE_DB;

CREATE TABLE IF NOT EXISTS cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(100) NOT NULL,
    anio INT NOT NULL CHECK (anio >= 1888 AND anio <= 2100), -- cine empieza 1888 aprox
    duracion INT NOT NULL CHECK (duracion > 0),
    genero ENUM('Comedia','Drama','Accion','Terror','Romance','Documental') NOT NULL,
    UNIQUE KEY ux_titulo_director (titulo, director)
);

-- Índices para búsquedas frecuentes
CREATE INDEX idx_titulo ON cartelera(titulo);
CREATE INDEX idx_genero ON cartelera(genero);

-- Datos de prueba
INSERT INTO cartelera (titulo, director, anio, duracion, genero) VALUES
('El Padrino', 'Francis Ford Coppola', 1972, 175, 'Drama'),
('La La Land', 'Damien Chazelle', 2016, 128, 'Romance');

SELECT * FROM cartelera;
