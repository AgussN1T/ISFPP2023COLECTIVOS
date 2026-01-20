-- Tabla de líneas
CREATE TABLE linea (
    id VARCHAR(50) PRIMARY KEY,
    comienza INT NOT NULL,
    finaliza INT NOT NULL,
    frecuencia INT NOT NULL
);

-- Tabla de paradas
CREATE TABLE parada (
    id INT PRIMARY KEY,
    direccion VARCHAR(255) NOT NULL
);

-- Relación muchos a muchos entre línea y parada
CREATE TABLE linea_parada (
    linea_id VARCHAR(50),
    parada_id INT,
    orden INT, --para saber en qué orden aparecen las paradas en una línea
    PRIMARY KEY (linea_id, parada_id),
    FOREIGN KEY (linea_id) REFERENCES linea(id) ON DELETE CASCADE,
    FOREIGN KEY (parada_id) REFERENCES parada(id) ON DELETE CASCADE
);

-- Tabla de tramos
CREATE TABLE tramo (
    id SERIAL PRIMARY KEY,
    inicio_id INT NOT NULL,
    fin_id INT NOT NULL,
    tiempo INT NOT NULL,
    tipo INT NOT NULL,
    FOREIGN KEY (inicio_id) REFERENCES parada(id) ON DELETE CASCADE,
    FOREIGN KEY (fin_id) REFERENCES parada(id) ON DELETE CASCADE
);
