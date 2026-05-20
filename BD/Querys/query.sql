
CREATE DATABASE gestion_taller;

USE gestion_taller;

CREATE TABLE usuarios(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('PROFESOR','ADMINISTRADOR')
);

CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE subcategorias (
    id_subcategoria INT AUTO_INCREMENT PRIMARY KEY,
    id_categoria INT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria) ON DELETE CASCADE
);

CREATE TABLE estado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre ENUM('Operativo','Averiado', 'En reparacion', 'Obsoleto')
);

CREATE TABLE ubicaciones (
    codigo_armario INT AUTO_INCREMENT PRIMARY KEY,
    espacio VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO ubicaciones(espacio) VALUES ("A1"), ("A2"), ("A3"), ("A4"), ("B1"), ("B2"), ("B3"), ("B4"), ("TALLER"), ("C1"), ("C2"), ("C3"), 
("D1"), ("D2"), ("D3"), ("E1"), ("E2"), ("E3"), ("DTD"), ("F1"), ("F2"), ("F3");

INSERT INTO estado (nombre) VALUES 
('Operativo'), ('Averiado'), ('En reparacion'), ('Obsoleto');

INSERT INTO categorias (nombre) VALUES
("PC’s para prácticas"), ("Componentes hardware"), ("Equipos de red"), ("Cableado estructurado"), ("Herramientas de soldadura y generales"), ("Material fungible");

INSERT INTO subcategorias (id_categoria, nombre) VALUES
(1, "Portatiles"), (1, "PC"), (2, "Placas base"), (2, "Memorias RAM"), (2, "Procesadores"), (2, "Discos duros"), (2, "Torres"), 
(3, "Switches"), (3, "Routers"), (3, "Puntos de acceso"), (3, "Patch panels"), (4, "Rosetas"), (4, "Cable UTP/FTP"), (4, "Fibra óptica"),
(5, "Multímetros"), (5, "Crimpadoras"), (5, "Destornilladores"), (6, "Cable UTP"), (6, "Alcohol isopropílico"), (6, "Pasta térmica"), (6, "Consumibles");

CREATE TABLE material (
    id_material INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(300),
	id_categoria INT,
    id_subcategoria INT,
    id_estado INT,
    cantidad INT NOT NULL DEFAULT 0,
    id_ubicacion INT,
    fecha_alta DATE,
    observaciones VARCHAR(300),
    
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria),
    FOREIGN KEY (id_subcategoria) REFERENCES subcategorias(id_subcategoria),
    FOREIGN KEY (id_estado) REFERENCES estado(id_estado),
    FOREIGN KEY (id_ubicacion) REFERENCES ubicaciones(codigo_armario)

);
