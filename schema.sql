CREATE DATABASE IF NOT EXISTS salon_belleza;
USE salon_belleza;

CREATE TABLE IF NOT EXISTS servicios (
    id_servicio       INT AUTO_INCREMENT PRIMARY KEY,
    nombre_servicio   VARCHAR(100) NOT NULL,
    descripcion       VARCHAR(255),
    duracion_minutos  INT NOT NULL,
    precio            DECIMAL(8,2) NOT NULL,
    especialista      VARCHAR(100) NOT NULL,
    estado            VARCHAR(20) NOT NULL DEFAULT 'Activo'
);

-- Datos de prueba
INSERT INTO servicios (nombre_servicio, descripcion, duracion_minutos, precio, especialista, estado) VALUES
('Corte de cabello', 'Corte para dama o caballero', 45, 30.00, 'María López', 'Activo'),
('Manicure', 'Limpieza y esmaltado de uñas', 60, 25.00, 'Ana Torres', 'Activo'),
('Tinte completo', 'Coloración de todo el cabello', 120, 90.00, 'Lucía Ramos', 'Activo'),
('Maquillaje social', 'Maquillaje para eventos', 50, 60.00, 'Carla Díaz', 'Inactivo'),
('Pedicure', 'Cuidado y esmaltado de pies', 70, 35.00, 'Ana Torres', 'Activo');
